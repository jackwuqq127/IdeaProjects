<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>$Title$</title>
    <style>
        ul li{
            list-style-type: none;
            display: inline-block;
            background-color: aliceblue;
        }
        ul li a:hover{
            color: red;
            cursor: pointer;
        }
        ul.language li{
            font-size: 20px;
        }
    </style>
</head>
<body>
<div>
    <ul class="language">
        <li><a href="emp/setLanguage?language=zh_CN">中文</a></li>
        <li><a href="emp/setLanguage?language=en_US">English</a></li>
    </ul>
</div>
<div>
    <ul>
        <li><a href="index"><fmt:message key="home"/></a></li>
        <li><a href="emp/addView"><fmt:message key="addemp"/></a></li>
        <li><a href="emp/empList"><fmt:message key="listEmp"/></a></li>
    </ul>
</div>
<h1>文件上传</h1>
<form action="emp/upload" enctype="multipart/form-data" method="post">
    <div>
        <label>文件</label>
        <input type="file" name="file">
    </div>
    <div>
        <label>描述</label>
        <input name="desc"/>
    </div>
    <%--<div>
        <label>文件</label>
        <input type="file" name="file">
    </div>
    <div>
        <label>描述</label>
        <input name="desc"/>
    </div>--%>
    <div>
        <button type="submit">提交</button>
    </div>
</form>

</body>
</html>
