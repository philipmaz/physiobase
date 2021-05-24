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
    <title>Patient Form</title>
</head>
<body>
<h3>Patient Form</h3>
<form:form method="post" modelAttribute="patient">
    <div>
    <label for="firstName">First Name: </label>
    <form:input id="firstName" path="firstName"/>
    <form:errors path="firstName"/>
    </div>
    <div>
        <label for="lastName">Last Name: </label>
        <form:input id="lastName" path="lastName"/>
        <form:errors path="lastName"/>
    </div>
    <div>
        <label for="sex">Gender</label>
        Male: <form:radiobutton id="sex" path="sex" value="M"/>
        Female: <form:radiobutton id="sex" path="sex" value="F"/>
    </div>
    <div>
        <label for="pesel">PESEL: </label>
        <form:input id="pesel" path="pesel"/>
        <form:errors path="pesel"/>
    </div>
    <div>
        <label for="address">Address: </label>
        <form:input id="address" path="address"/>
        <form:errors path="address"/>
    </div>
    <div>
        <label for="phoneNumber">Phone Number: </label>
        <form:input id="phoneNumber" path="phoneNumber"/>
        <form:errors path="phoneNumber"/>
    </div>
    <div>
        <label for="email">Email: </label>
        <form:input id="email" path="email"/>
        <form:errors path="email"/>
    </div>
<%--    <div>--%>
<%--        <label for="visits">Visits: </label>--%>
<%--        <form:input id="visits" path="visits"/>--%>
<%--        <form:errors path="visits"/>--%>
<%--    </div>--%>
    <div>
        <label for="isAgreement">Agreement for data processing: </label>
        <form:checkbox id="isAgreement" path="isAgreement"/>
        Wyrażam zgodę na przetwarzanie moich danych osobowych zgodnie z ustawą o ochronie danych osobowych w celu np. prowadzenia historii przebiegu terapii pacjenta, kontaktu z pacjentem czy ustalania wizyt.
        Podanie danych osobowych jest dobrowolne. Zostałem poinfrmowany, że przysługuje mi prawo dostępu do moich danych, możliwości ich poprawiania oraz żądania zaprzestania ich przetwarzania.
    </div>
    <div>
        <input type="submit">
    </div>
    <div>
        <form:errors path="*"/>
    </div>
</form:form>

<div>
    <a href="http://localhost:8080/patient/all">Show All Patients</a>
</div>
</body>
</html>
