package com.shopsphere.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebFilter(urlPatterns={"/admin/*"})
public class AdminFilter implements Filter {
    @Override public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
        HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;
        HttpSession s=req.getSession(false);
        if(s==null||s.getAttribute("user")==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        com.shopsphere.model.User user=(com.shopsphere.model.User)s.getAttribute("user");
        if(!"ADMIN".equals(user.getRole())){resp.sendError(403,"Administrator access required");return;}
        chain.doFilter(request,response);
    }
}
