package com.shopsphere.controller;
import com.shopsphere.service.ProductService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductService service=new ProductService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        try { req.setAttribute("products",service.search(req.getParameter("q"))); req.getRequestDispatcher("/products.jsp").forward(req,resp); }
        catch(Exception e){ throw new ServletException("Unable to load products. Check database connection and schema.",e); }
    }
}
