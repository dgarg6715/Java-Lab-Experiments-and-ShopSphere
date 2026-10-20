package com.shopsphere.controller;
import com.shopsphere.model.*;
import com.shopsphere.dao.OrderDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.*;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final OrderDAO orders=new OrderDAO();
    @SuppressWarnings("unchecked") private Map<Integer,CartItem> cart(HttpSession s){return (Map<Integer,CartItem>)s.getAttribute("cart");}
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);if(s==null||s.getAttribute("user")==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        Map<Integer,CartItem> c=cart(s);if(c==null||c.isEmpty()){resp.sendRedirect(req.getContextPath()+"/cart");return;}
        req.getRequestDispatcher("/checkout.jsp").forward(req,resp);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);if(s==null||s.getAttribute("user")==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        Map<Integer,CartItem> c=cart(s);if(c==null||c.isEmpty()){resp.sendRedirect(req.getContextPath()+"/cart");return;}
        try{
            int userId=((com.shopsphere.model.User)s.getAttribute("user")).getUserId();
            Map<Integer,Integer> qty=new LinkedHashMap<>();c.forEach((id,item)->qty.put(id,item.getQuantity()));
            int id=orders.createOrder(userId,qty);c.clear();s.setAttribute("lastOrderId",id);
            resp.sendRedirect(req.getContextPath()+"/orders?placed="+id);
        }catch(Exception e){req.setAttribute("error","Order could not be placed: "+e.getMessage());req.getRequestDispatcher("/checkout.jsp").forward(req,resp);}
    }
}
