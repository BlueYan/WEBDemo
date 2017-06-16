<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/16
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生列表</title>
</head>
<body>

    <h3>学生信息</h3>

    <a href="/Student?cmd=edit">添加学生</a>

    <table width="30%" border="1" cellspacing="0" cellpadding="0">

        <tr>
            <th>序号</th>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>操作</th>
        </tr>
        <%-- 循环迭代输出学生信息 --%>
        <c:forEach items="${stuList}" var="stu" varStatus="vs">
            <tr align="center" style="background-color: ${vs.count % 2 == 0 ? 'yellow' : ''}">
                <td>${vs.count}</td>
                <td>${pageScope.stu.id}</td>
                <td>${pageScope.stu.name}</td>
                <td>${pageScope.stu.age}</td>
                <td><a href="/Student?cmd=delete&id=${stu.id}">删除</a> | <a href="/Student?cmd=edit&id=${stu.id}">编辑</a></td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
