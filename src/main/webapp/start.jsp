<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.07.24
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .button {
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .button1 {background-color: #04AA6D;} /* Green */
    h1 {text-align: center;}
    p {text-align: center;}
    div {text-align: center;}
    form {text-align: center;}
    </style>
</head>
<body>
<p>
    <br><br><br><br>
<form action="/index">
    Ввести имя для начала игры: <input name="name" type="text">
    <br><br>
    <input class="button button1" type="submit" value="Начать ->">
</form>
</p>

</body>
</html>
