package com.shopsphere.controller;
import com.shopsphere.dao.OrderDAO;
import com.shopsphere.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private final OrderDAO dao=new OrderDAO();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);if(s==null||s.getAttribute("user")==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        try{req.setAttribute("orders",dao.findByUser(((User)s.getAttribute("user")).getUserId()));req.getRequestDispatcher("/orders.jsp").forward(req,resp);}
        catch(Exception e){throw new ServletException("Unable to load orders",e);}
    }
}
