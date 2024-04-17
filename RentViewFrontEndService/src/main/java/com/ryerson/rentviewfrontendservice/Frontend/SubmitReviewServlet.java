package com.ryerson.rentviewfrontendservice.Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.Cookie;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;

public class SubmitReviewServlet extends HttpServlet 
{       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberInfo memberInfo = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("login_token".equals(cookie.getName())) {
                    // Assuming you have a method in MemberManager to verify the token and get MemberInfo
                    memberInfo = MemberManager.verifyTokenAndGetMemberInfo(cookie.getValue());
                    if (memberInfo != null) {
                        break;
                    }
                }
            }
        }
        
        if (memberInfo != null) {
            int memberId = memberInfo.getMemberID();
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            
            int rating = Integer.parseInt(request.getParameter("rating"));
            String reviewText = request.getParameter("review");

            String xml = "<review>" +
                        "<memberID>" + memberId + "</memberID>" +
                        "<movieID>" + movieID + "</movieID>" +
                        "<rating>" + rating + "</rating>" +
                        "<reviewText>" + reviewText + "</reviewText>" +
                        "</review>";
            System.out.println(xml);
            
            Client searchClient = ClientBuilder.newClient();
            
            String reviewServiceAddr = System.getenv("reviewServiceAddr");
            Response postResponse = searchClient.target("http://" + reviewServiceAddr + "/RentViewReviewService/webresources/reviews")
                                     .request(MediaType.APPLICATION_XML)
                                     .post(Entity.entity(xml, MediaType.APPLICATION_XML));

            if (postResponse.getStatus() == Response.Status.CREATED.getStatusCode()) {
                response.sendRedirect("movie.jsp?movieID=" + movieID); // Redirect back to the movie page
            } else {
                // Handle error
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to submit review.");
            }
            
        } 
        else {            
            response.sendRedirect("login.jsp"); // Redirect to login page if not logged in
        }
    }
}