package com.ryerson.rentviewfrontendservice.Frontend;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.ws.rs.core.NewCookie;

public class LoginServlet extends HttpServlet 
{
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        MemberInfo memberInfo = MemberManager.authenticateMember(email, password);
        
        if (memberInfo != null) {
            //HttpSession session = request.getSession();
            //session.setAttribute("memberInfo", memberInfo);
            
            try{
                // Create JWT token
                Authenticate auth = new Authenticate();
                String token = auth.createJWT("RentViewFrontEndService", email, 100000); // Adjust issuer and ttlMillis as needed

                // Set JWT token as a cookie
                Cookie cookie = new Cookie("login_token", token);
                cookie.setPath("/");
                response.addCookie(cookie);

                String redirect = request.getParameter("redirect");
                String movieID = request.getParameter("movieID");
                if ("manager".equals(memberInfo.getMemberType())) {
                    response.sendRedirect("ManagerServlet");
                } else if (redirect != null && !redirect.isEmpty()) {
                    if (movieID != null && !movieID.isEmpty()) {
                        response.sendRedirect(redirect + "?movieID=" + movieID);
                    } else {
                        response.sendRedirect(redirect);
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error during token creation or cookie setting", e);
                response.sendRedirect("login.jsp?error=Server error");
            }
        }
        else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }

    @Override
    public String getServletInfo() {
        return "Login servlet";
    }
}