<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/24
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
</head>
<span style="color: #ff0000ff;">${errorMsg}</span>
<body>

    <form action="/login" method="post">

        用户名: <input type="text" name="username">

        <br>

        密码: <input type="password" name="password">

        <br>

        <input type="submit" value="提交">

    </form>

</body>
</html>
