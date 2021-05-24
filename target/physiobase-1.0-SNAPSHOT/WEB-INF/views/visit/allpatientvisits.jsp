<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Visit List</title>
</head>
<body>

<h2>${patient.firstName} ${patient.lastName}</h2>


<span>
${patient.sex}
${patient.pesel}
${patient.address}
${patient.phoneNumber}
${patient.email}
</span>

<p></p>



<form method="post" action="http://localhost:8080/visit/all">
    <div>
        <label for="date">Search for Visits by Date: </label>
        <input id="date" name="date" type="date"/>
    </div>
    <div>
        <input type="submit" value="Search">
    </div>
</form>

<table border="1">
    <thead>
    <th>ID</th>
    <th>Date</th>
    <th>Description</th>
    <th>Pain Scale</th>
    <th>Code ICD</th>
    <th>Modify</th>
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
            <td><a href="//localhost:8080/patient/editvisit/${patient.id}/${visit.id}">Modify this visit</a></td>
            <td><a href="//localhost:8080/patient/deletevisit/${visit.id}">Delete this visit</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<div>
    <a href="http://localhost:8080/patient/addvisit/${patient.id}">Add visit</a>
</div>

<div>
    <a href="http://localhost:8080/patient/all">Show All Patients</a>
</div>
</body>
</html>

