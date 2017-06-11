<%@ page import="com.mark.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 17/6/11
  Time: 下午12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>EL表达式的使用</title>
</head>
<body>

    <%
        //1.创建一个User对象
        User u = new User();
        System.out.println("name = " + u.getName());
        //2.将user对象放置到作用域中
        request.setAttribute("u", u);
    %>

    取出作用域中共享数据user的数据 <br>

    用户信息: ${u} <br>

    单个数据-name: ${u.name} <br/>

    单个数据-age : ${u.age} <br>

    数据数组-favs[index]-index表示下标: ${u.favs[0]} <br>

    集合数据-soce[index]-index表示下标: ${u.soce[1]} <br>

    map数据-map-使用点操作符来获取数据: ${u.map.name} <br>

    map数据-map-使用["key"]来获取数据: ${u.map["name.nickname"]} <br>

</body>
</html>
