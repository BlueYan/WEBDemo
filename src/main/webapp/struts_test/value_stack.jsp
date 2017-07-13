<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/10
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>演示ValueStack</title>
</head>
<body>

    从valueStack中root区域取值: <br>

    <!--
        使用索引下标来取值
    -->
    <s:property value="[0].top"/> <br>

    <s:property value="[1].top"/> <br>

    <!-- 使用别名来取值 -->
    <s:property value="sex"/> <br>

    从valueStack中context区域取值: <br>

    <!--
        对于从context区域中取值,使用#属性名
    -->
    <s:property value="#name"/> <br>

    <s:property value="#age"/>

</body>
</html>
