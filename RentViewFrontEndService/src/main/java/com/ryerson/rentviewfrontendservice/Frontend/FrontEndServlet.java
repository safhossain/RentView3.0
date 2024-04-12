package com.ryerson.rentviewfrontendservice.Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.ws.rs.core.NewCookie;

public class FrontEndServlet extends HttpServlet {
    private final String authenticationCookieName = "login_token";
    Authenticate autho;
    
    public FrontEndServlet(){
        autho = new Authenticate();
    }
    
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";

        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (!token.isEmpty()){ //*********************************** missing brackets? ***************************************
            try {
                if (this.autho.verify(token).getKey()) {
                    Map.Entry entry= new AbstractMap.SimpleEntry<String, String>(token,this.autho.verify(token).getValue());
                    return entry;
                } else {
                    Map.Entry entry= new AbstractMap.SimpleEntry<String, String>("","");
                    return entry;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FrontEndServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
        return entry;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}