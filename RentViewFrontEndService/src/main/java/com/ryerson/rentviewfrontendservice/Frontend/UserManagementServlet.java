package com.ryerson.rentviewfrontendservice.Frontend;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;
import com.ryerson.rentviewfrontendservice.Helper.EncryptionUtil;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

public class UserManagementServlet extends HttpServlet 
{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberInfo memberInfo = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("login_token".equals(cookie.getName())) {
                    memberInfo = MemberManager.verifyTokenAndGetMemberInfo(cookie.getValue());
                    if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
                        break;
                    }
                }
            }
        }

        if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
            List<MemberInfo> users = MemberManager.getAllMembers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("manager.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberInfo memberInfo = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("login_token".equals(cookie.getName())) {
                    memberInfo = MemberManager.verifyTokenAndGetMemberInfo(cookie.getValue());
                    if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
                        break;
                    }
                }
            }
        }
        
        String action = request.getParameter("action");    

        if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
            if ("delete".equals(action)) {
                String email = request.getParameter("email");
                MemberManager.deleteMember(email);
            } else if ("add".equals(action)) {
                // Retrieve user details from the request
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String dob = request.getParameter("dob");
                String memberType = request.getParameter("memberType");

                // Hash the password before storing it
                String hashedPassword = EncryptionUtil.hashPassword(email, password);

                // Add the new user
                MemberManager.createMember(email, hashedPassword, firstName, lastName, dob, memberType);
            }
            // Redirect back to the manager page to see the updated user list
            response.sendRedirect("UserManagementServlet");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}