package com.ryerson.rentviewfrontendservice.Business;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import com.ryerson.rentviewfrontendservice.Helper.EncryptionUtil;
import com.ryerson.rentviewfrontendservice.Helper.MemberInfo;
import com.ryerson.rentviewfrontendservice.Persistence.Member_CRUD;
import com.ryerson.rentviewfrontendservice.Frontend.Authenticate;

public class MemberManager 
{
    private static final Logger LOGGER = Logger.getLogger(MemberManager.class.getName());
    
    public static MemberInfo authenticateMember(String email, String password) {
        String storedPassword = Member_CRUD.getHashedPassword(email);
        String receivedPasswordReHashed = EncryptionUtil.hashPassword(email, password);
        if (storedPassword != null && storedPassword.equals(receivedPasswordReHashed)) {
            return Member_CRUD.readMember(email);
        }
        else{
            System.out.println("storedPassword= " + storedPassword);
            System.out.println("receivedPasswordReHashed= " + receivedPasswordReHashed);
        }
        return null;
    }
    
    public static List<MemberInfo> getAllMembers() {
        return Member_CRUD.readAllMembers();
    }
    
    public static void createMember(String email, String password, String firstName, String lastName, String dob, String memberType){
        String hashedPassword = EncryptionUtil.hashPassword(email, password);
        Member_CRUD.createMember(email, hashedPassword, firstName, lastName, dob, memberType);
        notifyReviewService(email, hashedPassword, firstName, lastName, dob, memberType, null, null, null);
    }
    
    public static void createMember(String email, String password, String firstName, String lastName, String dob, String memberType, String lastFourDigits, String cardType, String expirationDate) {        
        String hashedPassword = EncryptionUtil.hashPassword(email, password);
        Member_CRUD.createMember(email, hashedPassword, firstName, lastName, dob, memberType, lastFourDigits, cardType, expirationDate);
        notifyReviewService(email, hashedPassword, firstName, lastName, dob, memberType, lastFourDigits, cardType, expirationDate);
    }
    
    private static void notifyReviewService(String email, String hashedPassword, String firstName, String lastName, String dob, String memberType, String lastFourDigits, String cardType, String expirationDate) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RentViewReviewService/webresources/reviews/members")
            .queryParam("email", email)
            .queryParam("password", hashedPassword)
            .queryParam("firstName", firstName)
            .queryParam("lastName", lastName)
            .queryParam("dob", dob)
            .queryParam("memberType", memberType)
            .queryParam("lastFourDigits", lastFourDigits)
            .queryParam("cardType", cardType)
            .queryParam("expirationDate", expirationDate);

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.post(Entity.entity(null, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            LOGGER.warning("Failed to update ReviewService: " + response.readEntity(String.class));
        }

        client.close();
    }
    
    public static void deleteMember(String emailAddress){
        Member_CRUD.deleteMember(emailAddress);
    }
    
    public static MemberInfo verifyTokenAndGetMemberInfo(String token) {
        Authenticate auth = new Authenticate();
        try {
            Entry<Boolean, String> verificationResult = auth.verify(token);
            if (verificationResult.getKey()) {
                String email = verificationResult.getValue();
                LOGGER.info("Token verified successfully for email: " + email);
                return Member_CRUD.readMember(email);
            }
            else{
                LOGGER.warning("Token verification failed.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error verifying token", e);
        }
        return null;
    }    
    
    public static void main(String[] args) {
        MemberInfo testEntity = authenticateMember("safhossain338@gmail.com", "helloWorld!");
        if (testEntity != null) {
            System.out.println(testEntity.toString());
        } else {
            System.out.println("Object is null");
        }
        MemberInfo testEntity2 = authenticateMember("BobJohn@example.com", "thebuilder85");
        if (testEntity2 != null) {
            System.out.println(testEntity2.toString());
        } else {
            System.out.println("Object is null");
        }
        MemberInfo testEntity3 = authenticateMember("safhossain338@gmail.com", "helloWorld!");
        if (testEntity3 != null) {
            System.out.println(testEntity3.toString());
        } else {
            System.out.println("Object is null");
        }
        
        List<MemberInfo> members = getAllMembers();
        for (MemberInfo element : members) {
            System.out.println(element);
        }
    }
}