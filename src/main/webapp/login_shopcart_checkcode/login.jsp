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
    <script>

        function changeRandomCode() {
            //点击刷新验证码
            var randomCodeImg = document.getElementById("randomCode")
            //添加时间戳 来欺骗浏览器这是一个新的请求 以便让浏览器不会从缓存中获取原来的值
            randomCodeImg.src = "/randomCode;" + new Date().getTime();
        }

    </script>
</head>
<body>
<span style="color: #ff0000ff;">${errorMsg}</span>
    <form action="/login" method="post">

        用户名: <input type="text" name="username">

        <br>

        密码: <input type="password" name="password">

        <br>

        验证码: <input type="text" name="captcha"> <img src="/randomCode" id="randomCode" onclick="changeRandomCode()">

        <br>

        <input type="submit" value="提交">

    </form>

</body>
</html>
