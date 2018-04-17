<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加员工</title>
    <script src="js/jquery-3.2.1.min.js"></script>
    <style>
        form div{
            margin-bottom: 10px;
        }
        form div label{
            display: inline-block;
            width:100px;
            text-align: right;
        }
    </style>
</head>
<body>
<h1><fmt:message key="addemp"/></h1>
<form:form action="emp/addEmp" method="post" modelAttribute="emp">
    <div><font color="red"><form:errors path="*"/></font></div>
    <div><label><fmt:message key="empno"/>：</label><form:input path="empno"/><form:errors path="empno" cssStyle="color:red;"/></div>
    <div><label><fmt:message key="ename"/>：</label><form:input path="ename"/><form:errors path="ename"/></div>
    <div><label><fmt:message key="job"/>：</label><form:input path="job"/><form:errors path="job" cssStyle="color:red;"/></div>
    <div><label><fmt:message key="sal"/>：</label><form:input path="sal"/> <form:errors path="sal" cssStyle="color:red;"/></div>
    <div><label><fmt:message key="hiredate"/>：</label><form:input path="hiredate"/><form:errors path="hiredate" cssStyle="color:red;"/></div>
    <div><label><fmt:message key="comm"/>：</label><form:input path="comm"/></div>
    <div><label><fmt:message key="deptno"/>：</label><form:select path="deptno" items="${deptList}" itemLabel="dname" itemValue="deptno"/></div>
    <button type="submit"><fmt:message key="submit"/></button>
</form:form>
</body>
</html>
