package com.shopsphere.controller;
import com.shopsphere.service.ProductService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {
    private final ProductService service=new ProductService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        try {
            int id=Integer.parseInt(req.getParameter("id"));
            var product=service.find(id);
            if(product.isEmpty()){resp.sendError(404);return;}
            req.setAttribute("product",product.get());
            req.getRequestDispatcher("/product-details.jsp").forward(req,resp);
        } catch(NumberFormatException e){resp.sendError(400,"Invalid product id");}
        catch(Exception e){throw new ServletException("Unable to load product.",e);}
    }
}
