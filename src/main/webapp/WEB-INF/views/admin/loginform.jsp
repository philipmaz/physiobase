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
</head>
<body>

<h3>Log in: </h3>

<form:form method="post" modelAttribute="admin">
    <form:hidden path="id"/>
    <div>
        <label for="email">Email </label>
        <div>
            <form:input id="email" path="email" type="text" placeholder="email adress"/>
            <form:errors path="email"/>
        </div>
    </div>
    <div>
        <label for="password">Password </label>
        <div>
            <form:input id="password" path="password" type="text" placeholder="password"/>
            <form:errors path="password"/>
        </div>

    </div>
    <div>
        <input type="submit" value="Login">
    </div>
    <div>
        <form:errors path="*"/>
    </div>


</form:form>


</body>
</html>
