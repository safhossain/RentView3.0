package com.ryerson.rentviewreviewservice.endpoint;

import com.ryerson.rentviewreviewservice.Business.ReviewManager;
import com.ryerson.rentviewreviewservice.Helper.ReviewsXML;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("reviews")
public class ReviewsResource 
{
    @GET
    @Path("{movieID}")
    @Produces(MediaType.APPLICATION_XML)
    public String getReviewsByMovieID(@PathParam("movieID") int movieID) {
        ReviewsXML reviews = ReviewManager.getReviewsByMovieID(movieID);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ReviewsXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(reviews, sw);

            return sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(ReviewsResource.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR!!!!!!!!!!!!! safwan was here lol";
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createReview(ReviewsXML reviews) {
        ReviewManager.createReviews(reviews);
        return Response.status(Response.Status.CREATED).build();
    }    
}
