package com.ryerson.rentviewfrontendservice.Business;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.ryerson.rentviewfrontendservice.Persistence.Movie_CRUD;
import com.ryerson.rentviewfrontendservice.Persistence.Director_CRUD;
import com.ryerson.rentviewfrontendservice.Persistence.Genre_CRUD;
import com.ryerson.rentviewfrontendservice.Persistence.Movie_Director_CRUD;
import com.ryerson.rentviewfrontendservice.Persistence.Movie_Genre_CRUD;
import com.ryerson.rentviewfrontendservice.Helper.DirectorInfo;
import com.ryerson.rentviewfrontendservice.Helper.GenreInfo;
import com.ryerson.rentviewfrontendservice.Helper.MovieInfo;

public class MovieManagement 
{
    private static final Logger LOGGER = Logger.getLogger(MemberManager.class.getName());

    public static void createMovie(String movieName, int releaseYear, double rentalCost, String movieImagePath,
                                   boolean isMovieFeatured, String directorFirstName, String directorLastName,
                                   String genre1, String genre2) {
        // Create the movie
        int movieID = Movie_CRUD.createMovie(movieName, releaseYear, rentalCost, movieImagePath, isMovieFeatured);

        // Get the ID of the newly created movie
        System.out.println("movie id = " + movieID);

        // Check if the director exists, if not, create a new director
        int directorID = Director_CRUD.getDirectorID(directorFirstName, directorLastName);
        if (directorID == -1) {
            Director_CRUD.createDirector(directorFirstName, directorLastName);
            directorID = Director_CRUD.getDirectorID(directorFirstName, directorLastName);
        }
        System.out.println("directorID = " + directorID);        

        // Create the movie-director association
        Movie_Director_CRUD.createMovieDirector(movieID, directorID);

        // Check if the genres exist, if not, create new genres
        int genreID1 = Genre_CRUD.getGenreID(genre1);
        if (genreID1 == -1) {
            Genre_CRUD.createGenre(genre1);
            genreID1 = Genre_CRUD.getGenreID(genre1);
        }
        System.out.println("genre 1 ID = " + genreID1);
        
        Movie_Genre_CRUD.createMovieGenre(movieID, genreID1);

        int genreID2 = Genre_CRUD.getGenreID(genre2);
        if (genreID2 == -1) {
            Genre_CRUD.createGenre(genre2);
            genreID2 = Genre_CRUD.getGenreID(genre2);
        }
        System.out.println("genre 2 ID = " + genreID2);
        
        Movie_Genre_CRUD.createMovieGenre(movieID, genreID2);
    }
    
    public static MovieInfo readMovie(int movieID){
        return Movie_CRUD.readMovie(movieID);
    }
    
    public static List<MovieInfo> getAllMovies(){
        return Movie_CRUD.getAllMovies();
    }
    
    public static void main(String[] args) {
        
//        String movieName = "Dune Part 2";
//        int releaseYear = 2024;
//        double rentalCost = 6.99;
//        String movieImagePath = "/resources/movie_posters/dune-part-2.jpg";
//        boolean isMovieFeatured = true;
//        String directorFirstName = "Denis";
//        String directorLastName = "Villeneuve";
//        String genre1 = "Sci-Fi";
//        String genre2 = "Political";
//        
//        createMovie(movieName, releaseYear, rentalCost, movieImagePath, isMovieFeatured,
//                    directorFirstName, directorLastName, genre1, genre2);        
        
        MovieInfo aMovie = readMovie(2);
        System.out.println(aMovie);
        System.out.println("-------------");
        
        List<MovieInfo> movies = getAllMovies();
        for (MovieInfo element : movies) {
            System.out.println(element);
        }
    }
}
