<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${patient.firstName} ${patient.lastName} Visit History</title>
    <link rel="stylesheet" href="/css/table.css">
</head>
<body>
<div id="tile">
    <a href="/patient/all"><img src="/images/allpatients.jpg" title="All Patients" width="80px" height="80px" title="All Patients"></a>
    <a href="/patient/addpatient"><img src="/images/addpatient_icon1.jpg" title="Add Patient" width="80px" height="80px" title="Add Patient"></a>
    <a href="/visit/all"><img src="/images/visit_icon5.png" title="All Visits" width="100px" height="80px" title="All Visits"></a>
    <a href="http://localhost:8080/patient/home"><img src="/images/home.png" width="80px" height="80px" title="Homepage"></a>
</div>

<div>
    <h2>${patient.firstName} ${patient.lastName}    <a href="//localhost:8080/patient/editpatient/${patient.id}"><img src="/images/editpatient.png" width="80px" height=80px" title="Edit Patient Details"></a>
    </h2>
</div>

<div>
    ${patient.pesel}
</div>
<div>
    ${patient.address}
</div>
<div>
    ${patient.phoneNumber}
</div>
<div>
    ${patient.email}
</div>


<div>
    <h5>Diagnostic Research: </h5>
    <p>${patient.diagnostic}</p>
</div>

<div>
    <form method="post" action="http://localhost:8080/patient/showvisits/${patient.id}">
        <div>
            <label for="date">Search for Visits by Date: </label>
            <input id="date" name="date" type="date">
        </div>
        <div>
            <input type="submit" value="Search">
        </div>
    </form>
</div>

<div id="table">
    <table >
        <thead>
        <th>ID</th>
        <th>Date</th>
        <th>Description</th>
        <th>Pain Scale</th>
        <th>Code ICD</th>
        <th>Edit Visit</th>
        <th>Delete</th>
        </thead>
        <tbody>
        <c:forEach items="${visits}" var="visit">
            <tr>
                <td><c:out value="${visit.id}"/></td>
                <td><c:out value="${visit.date}"/></td>
                <td><c:out value="${visit.description}"/></td>
                <td><c:out value="${visit.painScale}"/></td>
                <td><c:out value="${visit.codeICD}"/></td>
                <td><a href="//localhost:8080/patient/editvisit/${patient.id}/${visit.id}"><img src="/images/edit.png" width="30px" height="35px" title="Edit visit"></a></td>
                <td><a href="//localhost:8080/patient/deletevisit/${patient.id}/${visit.id}"><img src="/images/delete.png" width="30px" height="35px" title="Delete Visit"></a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>

<div>
    <a href="http://localhost:8080/patient/addvisit/${patient.id}"><img src="/images/add.png" width="50px" height="50px" title="Add Visit"></a>
</div>

<table >
    <thead>
    <th>ID</th>
    <th>Date</th>
    <th>Description</th>
    <th>Edit Training</th>
    <th>Delete</th>
    </thead>
    <tbody>
    <c:forEach items="${trainings}" var="training">
        <tr>
            <td><c:out value="${training.id}"/></td>
            <td><c:out value="${training.date}"/></td>
            <td><c:out value="${training.description}"/></td>
            <td><a href="//localhost:8080/patient/edittraining/${patient.id}/${training.id}"><img src="/images/edit.png" width="30px" height="35px" title="Edit Training"></a></td>
            <td><a href="//localhost:8080/patient/deletetraining/${patient.id}/${training.id}"><img src="/images/delete.png" width="30px" height="35px" title="Delete Training"></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <a href="http://localhost:8080/patient/addtraining/${patient.id}"><img src="/images/add.png" width="50px" height="50px" title="Add Training"></a>
</div>

<%--<div>--%>
<%--    <a href="http://localhost:8080/patient/all">Show All Patients</a>--%>
<%--</div>--%>
<div>
    <a href="http://localhost:8080/patient/addfile/${patient.id}"><img src="/images/addimage.png" width="50px" height="50px" title="Add Image"></a>
    <a href="http://localhost:8080/patient/showfile/${patient.id}"><img src="/images/images.png" width="50px" height="50px" title="Show Images"></a>
</div>
</body>
</html>

