package com.ryerson.rentviewreviewservice.Business;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.ryerson.rentviewreviewservice.Helper.ReviewInfo;
import com.ryerson.rentviewreviewservice.Helper.ReviewsXML;
import com.ryerson.rentviewreviewservice.Persistence.Review_CRUD;

public class ReviewManager 
{
    public static void createReview(String review, int rating, int memberID, int movieID) {
        Review_CRUD.createReview(review, rating, memberID, movieID);
    }
    
    public static void createReviews(ReviewsXML reviewsXML) {
        for (ReviewInfo review : reviewsXML.getReviews()) {
            Review_CRUD.createReview(review.getReviewText(), review.getRating(), review.getMemberID(), review.getMovieID());
        }
    }

    public static List<ReviewInfo> getAllReviews() {
        return Review_CRUD.getAllReviews();
    }

    public static List<ReviewInfo> getReviewsByMovieID_nonXML(int movieID) {
        return Review_CRUD.getReviewsByMovieID(movieID);
    }
    
    public ReviewsXML getReviewsByMovieID(int movieID) {
        ArrayList<ReviewInfo> reviewList = Review_CRUD.getReviewsByMovieID(movieID);
        ReviewsXML reviews = new ReviewsXML();
        reviews.setReviews(reviewList);
        return reviews;
    }

    public static void main(String[] args) {
        int movieID = 5;
        List<ReviewInfo> reviews = getReviewsByMovieID_nonXML(movieID);
        System.out.println("Reviews for movie ID " + movieID + ":");
        for (ReviewInfo review : reviews) {
            System.out.println(review);
        }
        System.out.println("------------------------------");
        System.out.println("All Reviews in DB:");
        List<ReviewInfo> allReviews = getAllReviews();
        for (ReviewInfo aReview:allReviews){
            System.out.println(aReview);
        }
        ReviewManager manager = new ReviewManager();
        ReviewsXML books = manager.getReviewsByMovieID(5);
        System.out.println(books);
    }
}
