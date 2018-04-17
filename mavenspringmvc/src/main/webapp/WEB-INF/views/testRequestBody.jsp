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
    <form action="emp/testRequestBody" method="post" enctype="multipart/form-data">
        <input type="file" name="file"><br/>
        <input type="text" name="fileName" value="test"><br>
        <button type="submit">提交</button>
    </form>
</body>
</html>
