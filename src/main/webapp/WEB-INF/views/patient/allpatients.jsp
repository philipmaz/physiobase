<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Patient List</title>
    <link rel="stylesheet" href="/css/table.css">
</head>
<body>

<div id="tile">
    <a href="/patient/all"><img src="/images/allpatients.jpg" title="All Patients" width="150px" height="150px" title="All Patients"></a>
    <a href="/patient/addpatient"><img src="/images/addpatient_icon1.jpg" title="Add Patient" width="150px" height="150px" title="Add Patient"></a>
    <a href="/visit/all"><img src="/images/visit_icon5.png" title="All Visits" width="190px" height="150px" title="All Visits"></a>
    <a href="http://localhost:8080/patient/home"><img src="/images/home.png" width="150px" height="150px" title="Homepage"></a>
</div>

<form method="post" action="http://localhost:8080/patient/all">
    <div>
        <label for="lastName">Search for Patients by Last Name: </label>
        <input id="lastName" name="lastName" type="lastName"/>
    </div>
    <div>
        <input type="submit" value="Search">
    </div>
</form>

<table>
    <thead>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Gender</th>
    <th>PESEL</th>
    <th>Address</th>
    <th>Phone Number</th>
    <th>Email</th>
    <th>Agreement</th>
    <th>Details</th>
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
            <td><c:out value="${patient.isAgreement}"/></td>
            <td><a href="//localhost:8080/patient/showvisits/${patient.id}"><img src="/images/details2.png" width="30px" height="35px" title="Show Details"></a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<%--<div>--%>
<%--    <a href="http://localhost:8080/patient/home"><img src="/images/back1.png" width="150px" height="150px" title="Homepage"></a>--%>
<%--</div>--%>
<%--<div>--%>
<%--    <a href="http://localhost:8080/patient/all">Show All Patients</a>--%>
<%--</div>--%>
<%--<div>--%>
<%--    <a href="http://localhost:8080/visit/all">Show All Visits</a>--%>
<%--</div>--%>
</body>
</html>

