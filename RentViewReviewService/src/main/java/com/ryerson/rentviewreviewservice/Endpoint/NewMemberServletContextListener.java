package com.ryerson.rentviewreviewservice.Endpoint;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ryerson.rentviewreviewservice.Business.Messaging;

public class NewMemberServletContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }
    
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Runnable r = new Runnable() {
            public void run(){
                try {
                    Messaging.Receiving_Events_Store("new_member_channel");
                } catch (SSLException ex) {
                    Logger.getLogger(NewMemberServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ServerAddressNotSuppliedException ex) {
                    Logger.getLogger(NewMemberServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        new Thread(r).start();
    }
}