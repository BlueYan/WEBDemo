<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/14
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>员工列表</title>
</head>
<body>
<s:a action="input" namespace="/emp">添加
    <s:param name="e.id"/>
</s:a>

<s:form namespace="/emp" action="list" theme="simple">

    姓名: <s:textfield name="eqo.name"/>

    入职时期: <s:textfield name="eqo.lowerDate"/> ---- <s:textfield name="eqo.higherDate"/>

    <s:submit value="查询"/>

</s:form>

<table width="50%" border="1" cellpadding="0" cellspacing="0">

    <tr>
        <th>编号</th>
        <th>名字</th>
        <th>邮箱</th>
        <th>入职时间</th>
        <th>操作</th>
    </tr>


    <s:iterator value="#request.employeesPR.listData" var="e">

        <tr align="center">
            <td><s:property value="#e.id"/></td>
            <td><s:property value="#e.empName"/></td>
            <td><s:property value="#e.email"/></td>
            <td><s:date name="e.hireDate" format="yyyy-MM-dd" var="#e.hireDate"/></td>
            <td>
                <s:a namespace="/emp" action="input">编辑
                    <s:param name="e.id" value="#e.id"/>
                </s:a>
                |
                <s:a namespace="/emp" action="delete">删除
                    <s:param name="e.id" value="#e.id"/>
                </s:a>
            </td>
        </tr>

    </s:iterator>

    <tr>
        <td colspan="5" align="center">

            总条数: <s:property value="#request.employeesPR.totalCount"/>
            当前页: <s:property value="#request.employeesPR.currentPage"/> / <s:property value="#request.employeesPR.totalPage"/>

            <s:a namespace="/emp" action="list">首页
                <s:param name="eqo.currentPage" value="1"/>
                <s:param name="eqo.pageSize" value="#request.employeesPR.pageSize"/>
                <s:param name="eqo.name" value="eqo.name"/>
                <s:param name="eqo.lowerDate" value="eqo.lowerDate"/>
                <s:param name="eqo.higherDate" value="eqo.higherDate"/>
            </s:a>
            <s:a namespace="/emp" action="list">上一页
                <s:param name="eqo.currentPage"
                         value="#request.employeesPR.prePage"/>
                <s:param name="eqo.pageSize"
                         value="#request.employeesPR.pageSize"/>
                <s:param name="eqo.name" value="eqo.name"/>
                <s:param name="eqo.lowerDate" value="eqo.lowerDate"/>
                <s:param name="eqo.higherDate" value="eqo.higherDate"/>
            </s:a>
            <s:a namespace="/emp" action="list">下一页
                <s:param name="eqo.currentPage"
                         value="#request.employeesPR.nextPage"/>
                <s:param name="eqo.pageSize"
                         value="#request.employeesPR.pageSize"/>
                <s:param name="eqo.name" value="eqo.name"/>
                <s:param name="eqo.lowerDate" value="eqo.lowerDate"/>
                <s:param name="eqo.higherDate" value="eqo.higherDate"/>
            </s:a>

            <s:iterator begin="#request.employeesPR.pageIndex.beginIndex"
                        end="#request.employeesPR.pageIndex.endIndex"
                        var="index" step="1">

                <s:if test="index==#request.employeesPR.currentPage">
                    hahaha
                    <s:property value="index.count"/>
                </s:if>

                <s:else>
                    <s:a namespace="/emp" action="list">
                        <s:param name="eqo.currentPage"
                                 value="index"/>
                        <s:param name="eqo.pageSize"
                                 value="#request.employeesPR.pageSize"/>
                        <s:param name="eqo.name" value="eqo.name"/>
                        <s:param name="eqo.lowerDate" value="eqo.lowerDate"/>
                        <s:param name="eqo.higherDate" value="eqo.higherDate"/>
                        <s:property value="index"/>
                    </s:a>
                </s:else>

            </s:iterator>

            <s:a namespace="/emp" action="list">尾页
                <s:param name="eqo.currentPage"
                         value="#request.employeesPR.totalPage"/>
                <s:param name="eqo.pageSize"
                         value="#request.employeesPR.pageSize"/>
                <s:param name="eqo.name" value="eqo.name"/>
                <s:param name="eqo.lowerDate" value="eqo.lowerDate"/>
                <s:param name="eqo.higherDate" value="eqo.higherDate"/>
            </s:a>
        </td>
    </tr>

</table>

</body>
</html>
