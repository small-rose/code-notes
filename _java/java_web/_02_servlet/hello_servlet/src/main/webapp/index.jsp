<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<form action="/hello_servlet_war_exploded/request" method="get">
    <input name="username">
    <input name="password">
    <input type="submit" value="get方式提交">
</form>
<form action="/hello_servlet_war_exploded/request" method="post">
    <input name="username">
    <input name="password">
    <input type="submit" value="post方式提交">
</form>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
