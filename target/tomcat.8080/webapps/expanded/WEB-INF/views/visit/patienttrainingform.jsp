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
    <title>Patient Training Form</title>
</head>
<body>
<h3>Patient: ${patient.firstName} ${patient.lastName} Training Form</h3>
<form:form method="post" modelAttribute="training">
    <form:hidden path="id"/>
    <form:hidden path="patient.id"/>
    <div>
        <label for="description">Description: </label>
        <div>
            <form:textarea id="description" path="description" rows="50" cols="100"/>
            <form:errors path="description"/>
        </div>
    </div>
    <div>
        <label for="date">Training Date: </label>
        <form:input id="date" path="date" type="date"/>
        <form:errors path="date"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <div>
        <form:errors path="*"/>
    </div>
</form:form>
<div>
    <a href="http://localhost:8080/patient/showvisits/${patient.id}">Show All Patient Visits </a>
</div>
<div>
    <a href="http://localhost:8080/patient/all">Show All Patients</a>
</div>
</body>
</html>
