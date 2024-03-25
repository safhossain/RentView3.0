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
            </nav>
        </header>
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            Email: <input type="text" name="email"><br>
            Password: <input type="password" name="password"><br>
            <% if (request.getParameter("redirect") != null) { %>
                <input type="hidden" name="redirect" value="<%= request.getParameter("redirect") %>">
                <% if (request.getParameter("movieID") != null) { %>
                    <input type="hidden" name="movieID" value="<%= request.getParameter("movieID") %>">
                <% } %>
            <% } %>
            <input type="submit" value="Login">
        </form>        
    </body>
</html>