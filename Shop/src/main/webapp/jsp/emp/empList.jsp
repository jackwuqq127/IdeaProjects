<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<table>
    <h1>员工信息</h1>
    <c:forEach items="${emplist}" var="li">
        <tr>
            <td>${li.empno}</td>
            <td>${li.ename}</td>
            <td>${li.job}</td>
            <td>${li.sal}</td>
            <td>${li.deptno}</td>
            <td>
                <c:if test="${li.hiredate!=null}">
                    <fmt:formatDate value="${li.hiredate}" pattern="yyyy:MM:dd"/>
                </c:if>
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
