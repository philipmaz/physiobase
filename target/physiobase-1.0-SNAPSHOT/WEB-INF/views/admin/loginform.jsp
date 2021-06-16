<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 28.05.2021
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Login Form</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>

<div>
    <h1>Phisiobase</h1>
</div>
<div>
    <h2>* an app making physiotherapists, personal trainers and coaches lives easier </h2>
</div>
<br/>
<br/>


<form:form method="post" modelAttribute="admin">
    <form:hidden path="id"/>
    <div>
<%--        <label for="email">Email </label>--%>
<%--        <div>--%>
            <form:input id="email" path="email" type="text" placeholder="email adress"/>
<%--        </div>--%>
    </div>
    <div>
<%--        <label for="password">Password </label>--%>
<%--        <div>--%>
            <form:input id="password" path="password" type="password" placeholder="password" />
<%--        </div>--%>

    </div>
    <div>
        <input type="submit" value="Login">
    </div>
    <p>
        <form:errors path="*"/>
    </p>


</form:form>


</body>
</html>
