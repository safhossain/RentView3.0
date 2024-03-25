package com.ryerson.rentviewreviewservice.Business;

import java.util.List;
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

//    public static List<ReviewInfo> getReviewsByMovieID(int movieID) {
//        return Review_CRUD.getReviewsByMovieID(movieID);
//    }
    
    public static ReviewsXML getReviewsByMovieID(int movieID) {
        List<ReviewInfo> reviewList = Review_CRUD.getReviewsByMovieID(movieID);
        ReviewsXML reviews = new ReviewsXML();
        reviews.setReviews(reviewList);
        return reviews;
    }

    public static void main(String[] args) {
//        int movieID = 1;
//        List<ReviewInfo> reviews = getReviewsByMovieID(movieID);
//        System.out.println("Reviews for movie ID " + movieID + ":");
//        for (ReviewInfo review : reviews) {
//            System.out.println(review);
//        }
    }
}
