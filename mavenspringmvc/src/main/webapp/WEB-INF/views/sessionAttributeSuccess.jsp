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
<%--<div><font color="red">req</font>:${requestScope.msg}</div>
<div><font color="red">session</font>:${sessionScope.msg}</div>--%>

<div>
    <font color="red">salBySession:</font>
    ${sessionScope.sal}
</div>
<div>
    <font color="red">commBySession:</font>
    ${sessionScope.comm}
</div>
</body>
</html>
