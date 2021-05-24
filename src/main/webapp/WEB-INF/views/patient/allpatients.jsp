<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Patient List</title>
</head>
<body>
<form method="post" action="http://localhost:8080/patient/all">
    <div>
        <label for="lastName">Search for Patients by Last Name: </label>
        <input id="lastName" name="lastName" type="lastName"/>
    </div>
    <div>
        <input type="submit" value="Search">
    </div>
</form>

<table border="1">
    <thead>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Gender</th>
    <th>PESEL</th>
    <th>Address</th>
    <th>Phone Number</th>
    <th>Email</th>
    <th>Visits</th>
    <th>Agreement</th>
    <th>Modify</th>
    <th>Delete</th>
    <th>Visits</th>
    </thead>
    <tbody>
    <c:forEach items="${patients}" var="patient">
        <tr>
            <td><c:out value="${patient.id}"/></td>
            <td><c:out value="${patient.firstName}"/></td>
            <td><c:out value="${patient.lastName}"/></td>
            <td><c:out value="${patient.sex}"/></td>
            <td><c:out value="${patient.pesel}"/></td>
            <td><c:out value="${patient.address}"/></td>
            <td><c:out value="${patient.phoneNumber}"/></td>
            <td><c:out value="${patient.email}"/></td>
            <td><c:out value="${patient.visits}"/></td>
            <td><c:out value="${patient.isAgreement}"/></td>
            <td><a href="//localhost:8080/patient/editpatient/${patient.id}">Modify this patient</a></td>
            <td><a href="//localhost:8080/patient/deletepatient/${patient.id}">Delete this patient</a></td>
            <td><a href="//localhost:8080/patient/showvisits/${patient.id}">Show visits</a></td>
            <td><a href="//localhost:8080/patient/addvisit/${patient.id}">Add visit</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<div>
    <a href="http://localhost:8080/patient/addpatient">Add Patient</a>

</div>
<div>
    <a href="http://localhost:8080/patient/all">Show All Patients</a>
</div>
<div>
    <a href="http://localhost:8080/visit/all">Show All Visits</a>
</div>
</body>
</html>

