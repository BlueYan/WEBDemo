<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 17/7/15
  Time: 下午12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑员工信息</title>
</head>
<body>
    <s:fielderror/> <br/>
    <s:form namespace="/emp" action="saveOrUpdate" theme="simple">
             <s:hidden name="e.id"/> <br/> <!-- 注意记得要提交上id -->
        姓名: <s:textfield name="e.empName"/> <br>
        密码: <s:password name="e.empPassword" showPassword="true"/> <br>
        邮箱: <s:textfield name="e.email"/> <br>
        日期: <s:textfield name="e.hireDate"/> <br/>
        <s:submit value="提交"/>
    </s:form>


</body>
</html>
