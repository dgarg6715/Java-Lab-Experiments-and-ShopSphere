package com.shopsphere.controller;
import com.shopsphere.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService auth=new AuthService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {req.getRequestDispatcher("/login.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            var user=auth.login(req.getParameter("email"),req.getParameter("password"));
            if(user.isPresent()){
                HttpSession old=req.getSession(false); if(old!=null)old.invalidate();
                HttpSession session=req.getSession(true); session.setAttribute("user",user.get()); session.setMaxInactiveInterval(30*60);
                resp.sendRedirect(req.getContextPath()+"/");
            } else {req.setAttribute("error","Email or password is incorrect.");req.getRequestDispatcher("/login.jsp").forward(req,resp);}
        } catch(Exception e){throw new ServletException("Login failed. Check database setup.",e);}
    }
}
