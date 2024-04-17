package com.ryerson.rentviewreviewservice.Endpoint;

import java.io.StringWriter;
import java.io.StringReader;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ryerson.rentviewreviewservice.Business.ReviewManager;
import com.ryerson.rentviewreviewservice.Business.MemberManager;
import com.ryerson.rentviewreviewservice.Helper.ReviewsXML;
import com.ryerson.rentviewreviewservice.Helper.ReviewInfo;
import com.ryerson.rentviewreviewservice.Helper.MemberInfo;

@Path("reviews")
public class ReviewResource 
{
    @Context
    private UriInfo context;
    
    public ReviewResource(){
        
    }
    
    @GET
    @Path("{query}")
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
    public Response postXml(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ReviewInfo.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ReviewInfo review = (ReviewInfo) jaxbUnmarshaller.unmarshal(new StringReader(xml));

            ReviewManager.createReview(review.getReviewText(), review.getRating(), review.getMemberID(), review.getMovieID());

            return Response.status(Response.Status.CREATED).build();
        } catch (JAXBException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid XML format").build();
        }
    }    
    // No longer using this API endpoint for syncing the MEMBER entity syncing; replaced with KubeMQ
//    @POST
//    @Path("/members")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response create(
//            @QueryParam("email") String email,
//            @QueryParam("password") String password,
//            @QueryParam("firstName") String firstName,
//            @QueryParam("lastName") String lastName,
//            @QueryParam("dob") String dob,
//            @QueryParam("memberType") String memberType,
//            @QueryParam("lastFourDigits") String lastFourDigits,
//            @QueryParam("cardType") String cardType,
//            @QueryParam("expirationDate") String expirationDate) {
//        try {
//            System.out.println("Found in /members: " + email);
//            MemberManager.createMember(email, password, firstName, lastName, dob, memberType, lastFourDigits, cardType, expirationDate);
//            return Response.status(Response.Status.OK).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating member").build();
//        }
//    }
}