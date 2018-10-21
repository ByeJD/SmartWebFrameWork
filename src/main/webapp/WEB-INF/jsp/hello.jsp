<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Hello</title>
</head>

<body>
<h1>hello,servlet</h1>
<%= "current:" + request.getAttribute("currentTime") %>
</body>
</html>