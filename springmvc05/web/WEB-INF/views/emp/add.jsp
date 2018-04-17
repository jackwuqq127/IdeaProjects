<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
    Enumeration<String> attributeNames = request.getAttributeNames();
    while (attributeNames.hasMoreElements()){
        String name = attributeNames.nextElement();
        String value=request.getAttribute(name).toString();
       // System.out.println(name+":"+value);
    }
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>添加员工</title>
    <style>
        form div.group{
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>添加员工</h1>
    <form:form action="emp/addEmp" method="post" modelAttribute="empError">
        <form:errors path="*" cssStyle="color: red"/>
        <div class="group">
            <lable>编号</lable>
            <form:input path="empno"/>
            <from:errors path="empno" cssStyle="color: red"/>
        </div>

        <div class="group">
            <lable>姓名</lable>
            <form:input path="ename"/>
            <from:errors path="ename" cssStyle="color: red"/>
        </div>

        <div class="group">
            <lable>工种</lable>
            <form:input path="job"/>
            <from:errors path="job" cssStyle="color: red"/>
        </div>

        <div class="group">
            <lable>入职</lable>
            <form:input path="hiredate"/>
            <from:errors path="hiredate" cssStyle="color: red"/>
        </div>

        <div class="group">
            <lable>工资</lable>
            <form:input path="sal"/>
            <from:errors path="sal" cssStyle="color: red"/>
        </div>

        <div class="group">
            <button type="submit">提交</button>
        </div>
    </form:form>
</body>
</html>
