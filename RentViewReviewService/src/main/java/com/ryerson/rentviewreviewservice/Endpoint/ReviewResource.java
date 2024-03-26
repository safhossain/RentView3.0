package com.ryerson.rentviewreviewservice.Endpoint;

import java.io.StringWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.ryerson.rentviewreviewservice.Business.ReviewManager;
import com.ryerson.rentviewreviewservice.Helper.ReviewsXML;
import com.ryerson.rentviewreviewservice.Helper.ReviewInfo;

@Path("reviews/{query}")
public class ReviewResource 
{
    @Context
    private UriInfo context;
    
    public ReviewResource(){
        
    }
    
    @GET    
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") int movieID) {
        ReviewManager ReviewSearcher = new ReviewManager();
        ReviewsXML reviewsForThisMovie = ReviewSearcher.getReviewsByMovieID(movieID);
        System.out.println(">>>" + reviewsForThisMovie);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ReviewsXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(reviewsForThisMovie, sw);

            return (sw.toString());
        } catch (JAXBException ex) {
            Logger.getLogger(ReviewResource.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR!!!!!!!!!!!!! safwan was here lol";
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
//    public Response createReview(ReviewsXML reviews) {
//        ReviewManager.createReviews(reviews);
//        return Response.status(Response.Status.CREATED).build();
//    }
}
