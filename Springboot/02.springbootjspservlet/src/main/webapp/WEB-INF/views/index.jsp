<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <base href="<%=basePath%>">
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
    </head>
    <body>
        <h2>Hello World!</h2>
        <h1>${mesg}</h1>
    </body>
</html>
