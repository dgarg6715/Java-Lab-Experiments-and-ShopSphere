package com.shopsphere.controller;
import com.shopsphere.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final AuthService auth=new AuthService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {req.getRequestDispatcher("/register.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            boolean ok=auth.register(req.getParameter("name"),req.getParameter("email"),req.getParameter("password"));
            if(ok){resp.sendRedirect(req.getContextPath()+"/login?registered=1");}
            else {req.setAttribute("error","Registration failed. Check your details; password must be at least 8 characters and email must be unique.");req.getRequestDispatcher("/register.jsp").forward(req,resp);}
        } catch(Exception e){throw new ServletException("Registration failed. Check database setup.",e);}
    }
}
