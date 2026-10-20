package com.shopsphere.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebFilter(urlPatterns={"/cart","/checkout","/orders","/profile.jsp","/wishlist.jsp"})
public class AuthenticationFilter implements Filter {
    @Override public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
        HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;
        HttpSession s=req.getSession(false);
        if(s==null||s.getAttribute("user")==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        chain.doFilter(request,response);
    }
}
