<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/22
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <!-- enctype需要指定成multipart/form-data 意味着不需要编码传递.所得到的数据是二进制数据 -->
    <form action="/upload" method="post" enctype="multipart/form-data">

        姓名: <input type="text" name="username">

        <br>

        头像: <input type="file" name="headImg">

        <br>

        相册: <input type="file" name="album">

        <br>

        <input type="submit" value="提交">

    </form>

</body>
</html>
