<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 28.05.2021
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href=/css/home.css>
</head>
<body>
    <h1>Welcome in Phisiobase <p>${admin.email}</p> </h1>
<div id="tile">
    <div class="row">
        <div class="column">
            <a href="http://localhost:8080/patient/all"><img src="${pageContext.request.contextPath}/images/allpatients.jpg" alt="All Patients" width="222px" height="222px" title="All Patients"/></a>
        </div>
        <div class="column">
            <a href="http://localhost:8080/patient/addpatient"><img src="${pageContext.request.contextPath}/images/addpatient_icon1.jpg" alt="Add Patient" width="222px" height="222px" title="Add Patient"/></a>
        </div>
        <div class="column">
            <a href="http://localhost:8080/visit/all"><img src="${pageContext.request.contextPath}/images/visit_icon5.png" alt="All Visits" width="280px" height="222px" title="All Visits"/></a>
        </div>
    </div>
</div>

</body>

</html>
