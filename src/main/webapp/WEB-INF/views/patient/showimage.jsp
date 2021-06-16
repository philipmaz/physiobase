<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 08.06.2021
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Title</title>
</head>
<body>


<%--<img src="<html: rewrite page='${patient.imagePath}'/>">--%>
<%--<img src="${patient.imagePath}">--%>
<%--<img src="http://localhost:8080/webapp/images/file1.jpg">--%>
<%--<img src="https://rexmedica.pl/wp-content/uploads/2021/03/RTG-stawu-kolanowego.jpg.webp"/>--%>

<c:forEach items="${patient.imagePaths}" var="path">
    <div>
        <img src="${path.path}"/>
    </div>
</c:forEach>
</body>
</html>
