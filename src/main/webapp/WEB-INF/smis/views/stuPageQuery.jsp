<%@ page import="com.mark.project.smis.page.PageResult" %>
<%@ page import="com.mark.project.smis.domain.Student" %><%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 17/6/18
  Time: 上午11:55
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

<br>
<form action="/Student?cmd=pageQuery" method="post">

    姓名:
    <input type="text" name="name"/> <br>

    年龄:
    <input type="number" name="minAge"> - <input type="number" name="maxAge"/> <br>

    <input type="submit" value="提交">

</form>

<table width="40%" border="1" cellspacing="0" cellpadding="0">

    <tr>
        <th>序号</th>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <%-- 循环迭代输出学生信息 --%>
    <c:forEach items="${stuResult.listData}" var="stu" varStatus="vs">
        <tr align="center" style="background-color: ${vs.count % 2 == 0 ? 'yellow' : ''}">
            <td>${vs.count}</td>
            <td>${pageScope.stu.id}</td>
            <td>${pageScope.stu.name}</td>
            <td>${pageScope.stu.age}</td>
            <td><a href="/Student?cmd=delete&id=${stu.id}">删除</a> | <a href="/Student?cmd=edit&id=${stu.id}">编辑</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5" align="center">
            总${stuResult.totalCount}条数据 当前页: ${stuResult.currentPage} / ${stuResult.totalPage}
            <a href="/Student?cmd=pageQuery&currentPage=1">首页</a>
            <a href="/Student?cmd=pageQuery&currentPage=${stuResult.prePage}">上一页</a>
            <c:forEach begin="${stuResult.pageIndex.beginIndex}" end="${stuResult.pageIndex.endIndex}" step="1" var="index">
                <a href="/Student?cmd=pageQuery&currentPage=${index}">${index}</a>
            </c:forEach>
            <a href="/Student?cmd=pageQuery&currentPage=${stuResult.nextPage}">下一页</a>
            <a href="/Student?cmd=pageQuery&currentPage=${stuResult.totalPage}">尾页</a>
        </td>
    </tr>
</table>

</body>
</html>
