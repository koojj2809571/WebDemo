<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 19.12.3
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Country List</title>
</head>
<body>
We operate in these countries:
<ul>
    <li>${countries.get("ca")}</li>
    <li>${countries.get("us")}</li>
</ul>
</body>
</html>
