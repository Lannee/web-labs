<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% int statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
   String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");%>

<html>
<head>
    <title>Error <%= statusCode %></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="src/css/style.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Amaranth:ital@0;1&family=Montserrat:wght@600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="centerOfScreen convex">
        <div class="errorImage" style='background-image: url(https://httpducks.com/<%= statusCode %>.jpg)'></div>
        <!--<p class="subtitle">Ошибка <%= statusCode %></p>
        <p><%= errorMessage %></p><br>-->
        <% if(errorMessage != null && errorMessage.trim().equals("") && errorMessage.length() <= 50){ %>
            <p><%= errorMessage %></p><br>
        <% }%>

        <div class="convex paddingBox row iconLabel">
            <div class="icon" style="background-image: url(src/icons/leftArrow.png);"></div>
            <a href="<%= request.getContextPath()%>">
                    <p>Вернуться на главную</p>
            </a>
        </div>
    </div>
</body>
</html>