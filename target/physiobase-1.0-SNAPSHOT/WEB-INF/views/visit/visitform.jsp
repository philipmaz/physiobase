<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 12.05.2021
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Visit Form</title>
</head>
<body>
<h3>Visit Form</h3>
<form:form method="post" modelAttribute="visit">
    <div>
    <label for="date">Visit Date: </label>
    <form:input id="date" path="date" type="date"/>
    <form:errors path="date"/>
    </div>
    <div>
    <label for="description">Description: </label>
        <div>
            <form:textarea id="description" path="description" rows="50" cols="100"/>
            <form:errors path="description"/>
        </div>
    </div>
    <div>
        <label for="painScale">Pain Scale: </label>
        <form:input id="painScale" path="painScale" type="number" min="0" max="10"/>
        <form:errors path="painScale"/>
    </div>
    <div>
        <label for="codeICD">ICD code: </label>
        <form:input id="codeICD" path="codeICD"/>
        <form:errors path="codeICD"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <div>
        <form:errors path="*"/>
    </div>
</form:form>
<div>
    <a href="http://localhost:8080/visit/all">Show All Visits</a>
</div>
<div>
    <a href="http://localhost:8080/patient/all">Show All Patients</a>
</div>
</body>
</html>
