package com.ryerson.rentviewreviewservice.Helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "review")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReviewInfo 
{
    private int reviewID;
    private int memberID;
    private int movieID;
    private String reviewText;
    private int rating;
    
    public ReviewInfo() {
        // Default constructor needed for JAXB
    }
    
    public ReviewInfo(int reviewID, int memberID, int movieID, String reviewText, int rating) {
        this.reviewID = reviewID;
        this.memberID = memberID;
        this.movieID = movieID;
        this.reviewText = reviewText;
        this.rating = rating;
    }
    
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewInfo{" +
                "reviewID=" + reviewID +
                ", memberID=" + memberID +
                ", movieID=" + movieID +
                ", reviewText='" + reviewText + '\'' +
                ", rating=" + rating +
                '}';
    }
}
