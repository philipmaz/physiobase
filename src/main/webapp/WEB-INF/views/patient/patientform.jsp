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
    <link rel="stylesheet" href="/css/newform.css">
</head>
<body>
<div id="tile">
    <a href="/patient/all"><img src="/images/allpatients.jpg" title="All Patients" width="80px" height="80px" title="All Patients"></a>
    <a href="/patient/addpatient"><img src="/images/addpatient_icon1.jpg" title="Add Patient" width="80px" height="80px" title="Add Patient"></a>
    <a href="/visit/all"><img src="/images/visit_icon5.png" title="All Visits" width="100px" height="80px" title="All Visits"></a>
    <a href="http://localhost:8080/patient/home"><img src="/images/home.png" width="80px" height="80px" title="Homepage"></a>
</div>
<h3>Patient Form</h3>

    <form:form method="post" modelAttribute="patient">
        <form:hidden path="id"/>
        <div>
<%--            <label for="firstName">First Name: </label>--%>
            <form:input id="firstName" path="firstName" placeholder="First Name"/>
        </div>
        <div>
<%--            <label for="lastName">Last Name: </label>--%>
            <form:input id="lastName" path="lastName" placeholder="Last Name"/>
        </div>
        <div>
            <label for="sex">Gender</label>
            Male: <form:radiobutton id="sex" path="sex" value="M"/>
            Female: <form:radiobutton id="sex" path="sex" value="F"/>
        </div>
        <div>
<%--            <label for="pesel">PESEL: </label>--%>
            <form:input id="pesel" path="pesel" placeholder="PESEL"/>
        </div>
        <div>
<%--            <label for="address">Address: </label>--%>
            <form:input id="address" path="address" placeholder="Adress"/>
        </div>
        <div>
<%--            <label for="phoneNumber">Phone Number: </label>--%>
            <form:input id="phoneNumber" path="phoneNumber" placeholder="Phone Number"/>
        </div>
        <div>
<%--            <label for="email">Email: </label>--%>
            <form:input id="email" path="email" placeholder="Email"/>
        </div>
        <div>
            <label for="diagnostic">Diagnostic Research: </label>
            <div>
                <form:textarea id="diagnostic" path="diagnostic" rows="40" cols="70"/>
            </div>

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
        <p>
            <form:errors path="*"/>
        </p>
    </form:form>


</body>
</html>
