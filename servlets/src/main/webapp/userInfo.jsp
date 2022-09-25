<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<table>
    <colgroup>
        <col span="3" style="background:Khaki">
    </colgroup>
    <tr>
        <th>IP</th>
        <th>HTTP-HEADER</td>
        <th>DATE</td>
    </tr>
        <jsp:useBean id="userList" scope="request" type="java.util.List"/>
        <c:forEach items="${userList}" var="user" >
        <tr>
            <td style="font-weight:bold">${user.ip}</td>
            <td style="font-weight:bold">${user.header}</td>
            <td>${user.date}</td>
        </tr>
        </c:forEach>
</table>
</body>
</html>