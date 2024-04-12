<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rentview Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <header>
            <nav>
                <form action="index.jsp">
                        <button type="submit">Home</button>
                </form>                
                <form action="register.jsp">
                        <button type="submit">Register</button>
                </form>
            </nav>
        </header>
        <div class="login-container">
            <h2 class="login-title">Login</h2>
            <form action="LoginServlet" method="post" class="login-form">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" class="form-input">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-input">
                </div>
                <% if (request.getParameter("redirect") != null) { %>
                    <input type="hidden" name="redirect" value="<%= request.getParameter("redirect") %>">
                    <% if (request.getParameter("movieID") != null) { %>
                        <input type="hidden" name="movieID" value="<%= request.getParameter("movieID") %>">
                    <% } %>
                <% } %>
                <input type="submit" value="Login" class="login-button">
            </form>
        </div>
    </body>
</html>