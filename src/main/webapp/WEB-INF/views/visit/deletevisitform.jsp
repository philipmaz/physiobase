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
    <link rel="stylesheet" href="/css/newform.css">

</head>
<body>

<div id="tile">
    <a href="/patient/all"><img src="/images/allpatients.jpg" title="All Patients" width="80px" height="80px" title="All Patients"></a>
    <a href="/patient/addpatient"><img src="/images/addpatient_icon1.jpg" title="Add Patient" width="80px" height="80px" title="Add Patient"></a>
    <a href="/visit/all"><img src="/images/visit_icon5.png" title="All Visits" width="100px" height="80px" title="All Visits"></a>
    <a href="http://localhost:8080/patient/home"><img src="/images/home.png" width="80px" height="80px" title="Homepage"></a>
</div>

<h3>Are you sure you want to delete this visit?</h3>
<form:form method="post" modelAttribute="visit">
    <form:hidden path="id"/>
    <form:hidden path="patient.id"/>
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
        <input type="submit" value="Delete">
    </div>
    <div>
        <form:errors path="*"/>
    </div>
</form:form>

</body>
</html>
