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
    <h1>modealAndView</h1>
    <%=request.getAttribute("msg")%>
    <%--page request session application--%>
    <h2>${requestScope.msg}</h2>
    ${name} ${age} ${sessionScope.msg}
</body>
</html>
