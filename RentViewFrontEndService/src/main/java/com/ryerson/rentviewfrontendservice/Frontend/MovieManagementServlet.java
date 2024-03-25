package com.ryerson.rentviewfrontendservice.Frontend;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;
import com.ryerson.rentviewfrontendservice.Business.MovieManagement;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;
import com.ryerson.rentviewfrontendservice.Helper.MovieInfo;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

public class MovieManagementServlet extends HttpServlet 
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
            List<MovieInfo> movies = MovieManagement.getAllMovies();
            request.setAttribute("movies", movies);
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
                        System.out.println("manager");
                        break;
                    }
                }
            }
        }
        
        String action = request.getParameter("action");

        if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
            if ("add".equals(action)) {
                // Retrieve movie details from the request
                String movieName = request.getParameter("movieName");
                int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
                double rentalCost = Double.parseDouble(request.getParameter("rentalCost"));
                String movieImagePath = request.getParameter("movieImagePath");
                boolean isMovieFeatured = Boolean.parseBoolean(request.getParameter("isMovieFeatured"));
                String directorFirstName = request.getParameter("directorFirstName");
                String directorLastName = request.getParameter("directorLastName");
                String genre1 = request.getParameter("genre1");
                String genre2 = request.getParameter("genre2");

                // Add the new movie
                MovieManagement.createMovie(movieName, releaseYear, rentalCost, movieImagePath, isMovieFeatured,
                                            directorFirstName, directorLastName, genre1, genre2);
            }
            // Redirect back to the manager page to see the updated movie list
            response.sendRedirect("MovieManagementServlet");
        } else {
            response.sendRedirect("index.jsp");
        }
    }    
}
