<%@ page import="com.mark.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/12
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        User user = new User();
        request.setAttribute("user", user);
    %>

    jstl-if标签库的使用: <br>

    无内容本体: <br>
    <%--
        test: 表示判断的表达式
        var: 表示接收test判断的表达式的值
    --%>
    <c:if test="${user.age == 18}" var="result"></c:if>

    result = ${result} <br>

    有内容本体: <br>

    <%--
        有内容本体会输出内容
        当判断语句为true的时候才会输出
    --%>

    <c:if test="${user.age == 18}">${user.name}</c:if> <br>

    循环迭代集合: <br>

    <%--
        循环迭代集合
        items: 表示要迭代的集合
        var: 表示要接收每一个集合的元素
        varStatus: 可以获取当前迭代的下标以及当前是否是第一个元素或者是最后一个元素
    --%>
    <c:forEach items="${user.soce}" var="soce" varStatus="vs">
        ${vs.index} ---> ${soce}
    </c:forEach>

    <br>

    用下标循环迭代: <br>

    <%--
        begin: 表示从0开始
        end: 表示到10结束(包括10)
        step: 表示每次增加1
        循环迭代数组
    --%>

    <c:forEach begin="0" end="${fn:length(user.favs) - 1}" step="1" var="index">

        ${index} --> ${user.favs[index]}

    </c:forEach>

    <br>

    switch操作: <br>

    <%--
        类似与switch的操作
        c:choose 等价与 switch
        c:when 等价与 case
        c:otherwise 等价与 default
    --%>

    <c:choose>
        <c:when test="${user.age < 18}">未满18岁</c:when>
        <c:when test="${user.age >= 18 && user.age < 30}">符合条件</c:when>
        <c:otherwise>不接受</c:otherwise>
    </c:choose>

</body>
</html>
