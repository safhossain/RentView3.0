package com.ryerson.rentviewfrontendservice.Frontend;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String memberType = request.getParameter("memberType");

        // Optional card fields
        String cardType = request.getParameter("cardType");
        String lastFourDigits = request.getParameter("lastFourDigits");
        String expirationMonth = request.getParameter("expirationMonth");
        String expirationYear = request.getParameter("expirationYear");
        
        // Constructing the expiration date in the format YYYY-MM-DD
        // Defaulting day to '01' as we only need month and year for expiration
        String expirationDate = "";
        if (!expirationMonth.isEmpty() && !expirationYear.isEmpty()) {
            expirationDate = expirationYear + "-" + expirationMonth + "-01";
        }
        
        try {
            if (!cardType.isEmpty() && !lastFourDigits.isEmpty() && !expirationDate.isEmpty()) {
                MemberManager.createMember(email, password, firstName, lastName, dob, memberType, lastFourDigits, cardType, expirationDate);
            } else {
                MemberManager.createMember(email, password, firstName, lastName, dob, memberType);
            }
            response.sendRedirect("login.jsp");
        } catch (ClassNotFoundException | SQLException | ServerAddressNotSuppliedException | InterruptedException e) {            
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your registration.");            
            e.printStackTrace();
        }
    }
}