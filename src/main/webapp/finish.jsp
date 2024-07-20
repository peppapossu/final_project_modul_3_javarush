
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loose</title>
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
        .button2 {background-color: #008CBA;} /* Blue */
        h1 {text-align: center;}
        p {text-align: center;}
        div {text-align: center;}
    </style>
</head>
<body>
<br><br><br><br>
<h1>${currentQuestion}</h1>
<p>
<a href=index>
    <button class="button button2">Начать сначала</button>
</a>
</p>
<br><br><br><br><br><br><br><br><br>
<p>Session content</p>
<p>Имя игрока: ${name} </p>
<p>Игр сынграно: ${countGames}</p>
<p>Session id: <% out.print(session.getId()); %>

</body>
</html>
