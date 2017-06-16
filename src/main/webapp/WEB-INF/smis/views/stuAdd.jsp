<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/16
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>

    <h3>添加学生信息</h3>

    <form method="post" action="/Student?cmd=saveOrUpdate">
        <!-- 隐藏id 因为不需要显示给用户看 但是却要提交给后台 用于更新用户信息 -->
        <input type="hidden", name="id", value="${stu.id}">
        姓名:
        <input type="text" name="name" value="${stu.name}"/>
        <br>
        年龄:
        <input type="number" name="age" value="${stu.age}"/>
        <br>
        <input type="submit" value="提交"/>

    </form>

</body>
</html>
