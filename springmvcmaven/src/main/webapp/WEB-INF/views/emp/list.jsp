<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>员工信息</h1>
<h3>
    <a href="emp/addView">新增员工</a>
</h3>
<table border="1" cellspacing="0" cellpadding="12">
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>工作</td>
        <td>入职日期</td>
        <td>工资</td>
        <td>奖金</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${emplist}" var="li">
        <tr>
            <td>${li.empno}</td>
            <td>${li.ename}</td>
            <td>${li.job}</td>
            <td><fmt:formatDate value="${li.hiredate}" pattern="yyyy-MM-dd"/></td>
            <td>${li.sal}</td>
            <td>${li.comm}</td>
            <td>
                <a href="emp/deleteEmp/${li.empno}">删除</a>
                <a href="#">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
