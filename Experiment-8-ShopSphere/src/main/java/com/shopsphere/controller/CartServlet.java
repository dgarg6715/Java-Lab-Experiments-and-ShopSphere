package com.shopsphere.controller;
import com.shopsphere.model.*;
import com.shopsphere.service.ProductService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.*;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final ProductService products=new ProductService();
    @SuppressWarnings("unchecked") private Map<Integer,CartItem> cart(HttpSession s){
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)s.getAttribute("cart");
        if(cart==null){cart=new LinkedHashMap<>();s.setAttribute("cart",cart);}return cart;
    }
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        Map<Integer,CartItem> cart=cart(req.getSession());req.setAttribute("cart",cart.values());
        req.setAttribute("total",cart.values().stream().map(CartItem::getSubtotal).reduce(java.math.BigDecimal.ZERO,java.math.BigDecimal::add));
        req.getRequestDispatcher("/cart.jsp").forward(req,resp);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            String action=req.getParameter("action");Map<Integer,CartItem> cart=cart(req.getSession());
            int id=Integer.parseInt(req.getParameter("id"));
            if("remove".equals(action))cart.remove(id);
            else if("update".equals(action)){int q=Integer.parseInt(req.getParameter("quantity"));if(q<=0)cart.remove(id);else if(cart.containsKey(id))cart.get(id).setQuantity(Math.min(q,99));}
            else {var p=products.find(id);if(p.isPresent()){CartItem item=cart.get(id);if(item==null)cart.put(id,new CartItem(p.get(),1));else item.setQuantity(Math.min(item.getQuantity()+1,99));}}
            resp.sendRedirect(req.getContextPath()+"/cart");
        }catch(NumberFormatException e){resp.sendError(400,"Invalid product or quantity");}
        catch(Exception e){throw new ServletException("Unable to update cart",e);}
    }
}
