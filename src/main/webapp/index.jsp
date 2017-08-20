<%@ page import="com.mark.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/12
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<script src='/static/js/jquery.js' type='text/javascript'></script>
<script src='/static/js/jquery.gridly.js' type='text/javascript'></script>
<script type="text/javascript">

    function onTest() {
        console.log("test");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/data4",
            dataType: "json",
            async: true,
            success: function (data) {
                console.log("data.msg = " + data.msg);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("request = " + XMLHttpRequest.status);
                console.log("error textStatus = " + textStatus + " errorThrown = " + errorThrown);
            }
        });
        console.log("test2222")
    }

</script>

<body>
<h2>Hello World!</h2>

名字: ${requestScope.get("name")}
${msg}

<button type="button" onclick="onTest()">测试</button>

<form action="/param2" method="post">
    <input type="text" name="username"/> <br/>

    <input type="submit" value="提交"/>


</form>

</body>
</html>
