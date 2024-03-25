package com.ryerson.rentviewfrontendservice.Frontend;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;
import com.ryerson.rentviewfrontendservice.Business.RentalManager;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;

public class RentMovieServlet extends HttpServlet {    

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
            int movieId = Integer.parseInt(request.getParameter("movieID"));
            
            LocalDate rentalDate = LocalDate.now();
            LocalDate returnDate = rentalDate.plusDays(12);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            RentalManager.createRental(memberId, movieId, rentalDate.format(formatter), returnDate.format(formatter));
            response.sendRedirect("movie.jsp?movieID=" + movieId + "&rentalStatus=Success");
        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("login.jsp");
        }
    }
}
