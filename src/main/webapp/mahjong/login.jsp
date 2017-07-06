<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/4
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

    <form action="/mahjong/login" method="post">

        <input type="text" name="openId" placeholder="账号"> <br>

        <input type="text" name="cmd" placeholder="命令"> <br>

        <input type="submit" value="提交">

    </form>

</body>
</html>
