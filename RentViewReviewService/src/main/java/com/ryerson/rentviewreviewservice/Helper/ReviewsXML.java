package com.ryerson.rentviewreviewservice.Helper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reviews")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReviewsXML 
{
    @XmlElement(name = "review")
    private List<ReviewInfo> reviews;

    public ReviewsXML() {
        // Default constructor needed for JAXB
    }

    public List<ReviewInfo> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewInfo> reviews) {
        this.reviews = reviews;
    }
}

