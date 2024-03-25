package com.ryerson.rentviewfrontendservice.Frontend;

import com.ryerson.rentviewfrontendservice.Business.MemberManager;
import com.ryerson.rentviewfrontendservice.Business.MovieManagement;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;
import com.ryerson.rentviewfrontendservice.Helper.MovieInfo;
import com.ryerson.rentviewfrontendservice.Persistence.Movie_CRUD;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

public class ManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession();
        //MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

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
            List<MovieInfo> movies = MovieManagement.getAllMovies();
            request.setAttribute("users", users);
            request.setAttribute("movies", movies);
            request.getRequestDispatcher("manager.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle any POST requests if necessary
    }    
}
