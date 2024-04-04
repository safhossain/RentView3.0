package com.ryerson.rentviewfrontendservice.Helper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.ryerson.rentviewfrontendservice.Helper.ReviewInfo;

@XmlRootElement(name = "reviews")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReviewsXML 
{
    @XmlElement(name = "review")
    private ArrayList<ReviewInfo> reviews;

    public ReviewsXML() {
        // Default constructor needed for JAXB
    }

    public ArrayList<ReviewInfo> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewInfo> reviews) {
        this.reviews = reviews;
    }
}
