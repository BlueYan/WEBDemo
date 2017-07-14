<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/14
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>员工列表</title>
</head>
<body>

    <table width="50%" border="1" cellpadding="0" cellspacing="0">

        <tr>
            <th>编号</th>
            <th>名字</th>
            <th>邮箱</th>
            <th>入职时间</th>
            <th>操作</th>
        </tr>


        <s:iterator value="#request.employees" var="e">

            <tr align="center">
                <td><s:property value="#e.id"/></td>
                <td><s:property value="#e.empName"/></td>
                <td><s:property value="#e.email"/></td>
                <td><s:property value="#e.hireDate"/></td>
                <td>
                    <s:a namespace="/emp" action="update">编辑</s:a>

                </td>
                <td>
                    <s:a namespace="/emp" action="delete">删除
                        <s:param name="id" value="#e.id"/>
                    </s:a>

                </td>
            </tr>

        </s:iterator>

    </table>

</body>
</html>
