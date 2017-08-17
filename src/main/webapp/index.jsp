<%@ page import="com.mark.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/12
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>

名字: ${requestScope.get("name")}
${msg}
</body>
</html>
