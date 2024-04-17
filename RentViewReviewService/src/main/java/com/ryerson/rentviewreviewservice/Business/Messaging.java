package com.ryerson.rentviewreviewservice.Business;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;

import com.ryerson.rentviewreviewservice.Endpoint.NewMemberServletContextListener;
import com.ryerson.rentviewreviewservice.Business.MemberManager;

public class Messaging {
    public static void Receiving_Events_Store(String cname) throws SSLException, ServerAddressNotSuppliedException {
        String ChannelName = cname;
        String ClientID = "new_member_subscribers";
        String kubeMQAddress = System.getenv("kubeMQAddress");
        
        Subscriber subscriber = new Subscriber(kubeMQAddress);
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setChannel(ChannelName);
        subscribeRequest.setClientID(ClientID);
        subscribeRequest.setSubscribeType(SubscribeType.EventsStore);
        subscribeRequest.setEventsStoreType(EventsStoreType.StartAtSequence);
        subscribeRequest.setEventsStoreTypeValue(1);
        
        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {
            @Override
            public void onNext(EventReceive value) {
                try {
                    String val=(String) Converter.FromByteArray(value.getBody());
                    System.out.printf("Event Received: EventID: %s, Channel: %s, Metadata: %s, Body: %s",
                            value.getEventId(), value.getChannel(), value.getMetadata(),
                            Converter.FromByteArray(value.getBody()));
                    String[] msgParts = val.split(":");
                    if(msgParts.length==10){
                        if(msgParts[0].equals("CREATE")){
                            String email = msgParts[1];
                            String password = msgParts[2];
                            String firstName = msgParts[3];
                            String lastName = msgParts[4];
                            String dob = msgParts[5];
                            String memberType = msgParts[6];
                            String lastFourDigits = msgParts[7].equals("null") ? null : msgParts[7];
                            String cardType = msgParts[8].equals("null") ? null : msgParts[8];
                            String expirationDate = msgParts[9].equals("null") ? null : msgParts[9];
                            
                            MemberManager.createMember(email, password, firstName, lastName, dob, memberType, lastFourDigits, cardType, expirationDate);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    System.out.printf("ClassNotFoundException: %s", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.printf("IOException: %s", e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(NewMemberServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }

            @Override
            public void onError(Throwable t) {
                System.out.printf("onError:  %s", t.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        };
        subscriber.SubscribeToEvents(subscribeRequest, streamObserver);
    }
}
