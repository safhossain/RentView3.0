package com.ryerson.rentviewfrontendservice.Frontend;

import com.ryerson.rentviewfrontendservice.Helper.MovieInfo;
import com.ryerson.rentviewfrontendservice.Persistence.Movie_CRUD;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MovieInfo> movies = Movie_CRUD.getAllMovies();
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Index servlet";
    }
}