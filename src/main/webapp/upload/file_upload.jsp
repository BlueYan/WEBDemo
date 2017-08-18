<%--
  Created by IntelliJ IDEA.
  User: Mark_Yan
  Date: 2017/8/18
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC文件上传</title>
</head>
<body>

    <form action="/fileUpload" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" name="提交"/>
    </form>

</body>
</html>
