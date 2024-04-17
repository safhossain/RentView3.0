package com.ryerson.rentviewfrontendservice.Business;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.tools.Converter;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Messaging 
{
    public static void sendMessage(String message) throws IOException{
        String channelName = "new_member_channel";
        String clientID = "new_member_subscriber";
        String kubeMQAddress = System.getenv("kubeMQAddress");
        
        io.kubemq.sdk.event.Channel channel = new io.kubemq.sdk.event.Channel(channelName, clientID, false, kubeMQAddress);
        channel.setStore(true);
        
        Event event = new Event();
        event.setBody(Converter.ToByteArray(message));
        event.setEventId("event-Store-");
        
        try{
            channel.SendEvent(event);
        } 
        catch (ServerAddressNotSuppliedException e) {
            System.out.printf("ServerAddressNotSuppliedException: %s", e.getMessage());
            e.printStackTrace();
        } 
        catch (SSLException e) {
            System.out.printf("SSLException: %s", e.getMessage());
            e.printStackTrace();
        }    
    }
}