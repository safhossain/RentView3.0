<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("login_token".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
    }
    response.sendRedirect("index.jsp");
%>
