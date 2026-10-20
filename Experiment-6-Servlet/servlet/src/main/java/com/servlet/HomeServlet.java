package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        PrintWriter out = response.getWriter();

        if (session != null && session.getAttribute("username") != null) {

            String username =
                    (String) session.getAttribute("username");

            out.println("<h1>Student Home</h1>");
            out.println("<h2>Welcome " + username + "</h2>");

        } else {

            out.println("<h1>Please Login First</h1>");
            out.println("<a href='login.html'>Go to Login</a>");
        }
    }
}