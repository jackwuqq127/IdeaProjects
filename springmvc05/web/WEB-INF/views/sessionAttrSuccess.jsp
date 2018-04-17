<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
    <div>
        <font color="red">requestScope</font>
        <label>${requestScope.msg}</label>
    </div>
    <div>
        <font color="blue">sessionScope</font>
        <label>${sessionScope.msg}</label>
    </div>

    ${sessionScope.sal} ${sessionScope.com}
</body>
</html>
