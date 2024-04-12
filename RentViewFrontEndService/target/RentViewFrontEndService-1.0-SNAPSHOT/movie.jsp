<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@page import="com.ryerson.rentviewfrontendservice.Business.MovieManagement"%>
<%@page import="com.ryerson.rentviewfrontendservice.Business.RentalManager"%>
<%@page import="com.ryerson.rentviewfrontendservice.Business.MemberManager"%>
<%@page import="com.ryerson.rentviewfrontendservice.Business.MemberManager"%>
<%@page import="com.ryerson.rentviewfrontendservice.Business.Business"%>
<%@page import="com.ryerson.rentviewfrontendservice.Business.XMLParser"%>
<%@page import="com.ryerson.rentviewfrontendservice.Helper.MemberInfo"%>
<%@page import="com.ryerson.rentviewfrontendservice.Helper.ReviewInfo"%>
<%@page import="com.ryerson.rentviewfrontendservice.Helper.ReviewsXML"%>
<%@page import="com.ryerson.rentviewfrontendservice.Helper.MovieInfo"%>
<%@page import="com.ryerson.rentviewfrontendservice.Helper.RentalInfo"%>
<%@page import="javax.servlet.http.Cookie"%>

<%
    boolean isAuthenticated = false;
    MemberInfo memberInfo = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("login_token".equals(cookie.getName())) {
                // Assuming you have a method in MemberManager to verify the token and get MemberInfo
                memberInfo = MemberManager.verifyTokenAndGetMemberInfo(cookie.getValue());
                if (memberInfo != null) {
                    isAuthenticated = true;
                    break;
                }
            }
        }
    }
%>

<html>
    <head>
        <title>Movie Details</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <header>
            <nav>
                <form action="index.jsp">
                        <button type="submit">Home</button>
                </form>
                <% 
                    if (isAuthenticated) {
                %>
                    <span>Welcome, <%= memberInfo.getFirstName() %></span>
                    <form action="logout.jsp">
                        <button type="submit">Logout</button>
                    </form>
                    <% if (memberInfo.getMemberType().equals("manager")){ %>
                        <form action="MovieManagementServlet" method="GET">
                            <button type="submit">MANAGER TOOLS</button>
                        </form>
                    <% } %>
                <% } else { %>
                    <form action="login.jsp">
                        <button type="submit">Login</button>
                    </form>
                <% } %>
            </nav>
        </header>
        <% 
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            MovieInfo movie = MovieManagement.readMovie(movieID);            
            
            if (movie != null) {
        %>
            <h1 class="movie-details-header"><%= movie.getMovieName() %></h1>
            <img src="<%= request.getContextPath() + movie.getMovieImagePath() %>" alt="<%= movie.getMovieName() %>" class="movie-img" width="270" height="400">
            <p class="movie-info">Release Year: <%= movie.getReleaseYear() %></p>
            <p class="movie-info">Rental Cost: $<%= movie.getRentalCost() %></p>
            
            <% if (session.getAttribute("rentalStatus") != null && session.getAttribute("rentalStatus").equals("Success")) { %>
                <p style="color: green;">Rental successful!</p>
                <% session.removeAttribute("rentalStatus"); %>
            <% } %>

            <% if (isAuthenticated) {                
                RentalManager rentalManager = new RentalManager();
                boolean hasRented = rentalManager.authenticateRentalByMemberID(memberInfo.getMemberID(), movie.getMovieID());
                if (hasRented) {
            %>
                <button>Watch Now</button>
            <% } else { %>
                <form action="RentMovieServlet" method="post">
                    <input type="hidden" name="movieID" value="<%= movie.getMovieID() %>">
                    <button type="submit">Rent Now</button>
                </form>
            <% }
            } else { %>
                <a href="login.jsp?redirect=movie.jsp&movieID=<%= movie.getMovieID() %>"><button>Rent Now</button></a>
            <% } %>
            
        <% 
            } else {
        %>
            <p>Movie not found.</p>
        <% 
            }
        %>
        
        <% 
            // Using XMLParser provided by prof (doesn't look good tbh)
//            Business bs = new Business();
//            ReviewsXML result = bs.getReviewsFromReviewService(String.valueOf(movieID), "");
//            List<ReviewInfo> reviews = result.getReviews();
//            
//            String resultXMLString = bs.getReviewsFromReviewService_XMLString(String.valueOf(movieID), "");
//            XMLParser xmlparser = new XMLParser();
//            String resultsHTMLTable = xmlparser.ConvertXmlToHtmlTable(resultXMLString);            
//            request.setAttribute("resultsHTMLTable", resultsHTMLTable);
//            
//            if (reviews != null && !reviews.isEmpty()) {
//                request.getAttribute(resultsHTMLTable);
//                String htmlTable = (String) request.getAttribute("resultsHTMLTable");  // Retrieving the attribute correctly
//                out.print(htmlTable);  // This line will output your HTML table to the page
//            }        
        %>
        
        <% 
            Business bs = new Business();
            ReviewsXML result = bs.getReviewsFromReviewService(String.valueOf(movieID), "");            
            List<ReviewInfo> reviews = result.getReviews();
            if (reviews != null && !reviews.isEmpty()) {
        %>
            <table class="reviews-table">
                <tr>
                    <th>Rating</th>
                    <th>Review</th>
                </tr>
                <% for (ReviewInfo review : reviews) { %>
                    <tr>
                        <td><%= review.getRating() %></td>
                        <td><%= review.getReviewText() %></td>
                    </tr>
                <% } %>
            </table>
        <% } %>
        
        
        <form action="SubmitReviewServlet" method="post" class="movie-form">
            <input type="hidden" name="movieID" value="<%= movieID %>">
            Rating: <select name="rating">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select><br>
            Review: <textarea name="review" placeholder="Enter your review here..."></textarea><br>
            <button type="submit">Submit Review</button>
        </form>
        
        <footer>
            <nav>
                <button onclick="window.scrollTo(0, 0);">scroll to top</button>
                <form action="index.jsp">
                        <button type="Terms & conditions">Home</button>
                </form>                
                <a href="#about">About us</a>
                <a href="#support">Support</a>
            </nav>
        </footer>
    </body>
</html>