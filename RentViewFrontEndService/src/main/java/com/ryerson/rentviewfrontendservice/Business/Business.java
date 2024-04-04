package com.ryerson.rentviewfrontendservice.Business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import com.ryerson.rentviewfrontendservice.Helper.ReviewInfo;
import com.ryerson.rentviewfrontendservice.Helper.ReviewsXML;

public class Business {
    public static ReviewsXML getReviewsFromReviewService(String movieID, String token) throws IOException {

        Client searchclient = ClientBuilder.newClient();
        
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/RentViewReviewService/webresources/reviews");
        
        InputStream is = searchwebTarget.path(movieID).request(MediaType.APPLICATION_XML).get(InputStream.class);
        
        String xml = IOUtils.toString(is, "utf-8");
        ReviewsXML reviews = xmlStringToReviewsXML(xml);
        return (reviews);
    }
    
    private static ReviewsXML xmlStringToReviewsXML(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ReviewsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ReviewsXML books = (ReviewsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return books;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
