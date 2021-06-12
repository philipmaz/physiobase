<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Visit List</title>
    <link rel="stylesheet" href="/css/table.css">
</head>
<body>

<div id="tile">
    <a href="/patient/all"><img src="/images/allpatients.jpg" title="All Patients" width="150px" height="150px" title="All Patients"></a>
    <a href="/patient/addpatient"><img src="/images/addpatient_icon1.jpg" title="Add Patient" width="150px" height="150px" title="Add Patient"></a>
    <a href="/visit/all"><img src="/images/visit_icon5.png" title="All Visits" width="190px" height="150px" title="All Visits"></a>
    <a href="http://localhost:8080/patient/home"><img src="/images/home.png" width="150px" height="150px" title="Homepage"></a>

</div>

<form method="post" action="http://localhost:8080/visit/all">
    <div>
        <label for="date">Search for Visits by Date: </label>
        <input id="date" name="date" type="date"/>
    </div>
    <div>
        <input type="submit" value="Search">
    </div>
</form>

<table>
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
            <td><a href="//localhost:8080/visit/editvisit/${visit.id}"><img src="/images/edit.png" width="30px" height="35px" title="Edit visit"></a></td>
            <td><a href="//localhost:8080/visit/deletevisit/${visit.id}"><img src="/images/delete.png" width="30px" height="35px" title="Delete Visit"></a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>


</body>
</html>

