<%@ page import="com.mark.project.mahjong.proto.ProtoGame" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/4
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <script>
        function onCreateRoom() {

        }
    </script>

    <style>
        div {
            display: inline;
        }
    </style>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <title>主页</title>
</head>
<body>

欢迎: ${sessionScope.USER_IN_SESSION.nickname} 登录 <br>
<span>错误码: ${requestScope.errorMsg}</span>

<div style="display: inline;">
    <form action="/mahjong/create_room" method="post">
        钻石：
        <input type="radio" name="pay" value="房主扣钻" placeholder="房主扣钻"> 房主扣钻
        <input type="radio" name="pay" value="AA扣钻" placeholder="AA扣钻"> AA扣钻
        <br>
        局数：
        <input type="radio" name="round" value="8" placeholder="8局(x3)"> 8局(x3)
        <input type="radio" name="round" value="16" placeholder="16局(x6)"> 16局(x6)
        <input type="radio" name="round" value="4" placeholder="4局(体验局x2)"> 4局(体验局x2)
        <br>
        人数:
        <input type="radio" name="people" value="4" placeholder="4人"> 4人
        <input type="radio" name="people" value="3" placeholder="3人"> 3人
        <br>
        玩法:
        <input type="checkbox" name="method" placeholder="无字(100张)" value="无字"> 无字(100张)
        <input type="checkbox" name="method" placeholder="无风(108张)" value="无风"> 无风(108张)
        <br>
        胡牌:
        <input type="radio" name="hu" placeholder="吃胡" value="chiHu"> 吃胡
        <input type="radio" name="hu" placeholder="自摸(20分以上可以吃胡)" value="ziMo"> 自摸(20分以上可以吃胡)
        <input type="radio" name="hu" placeholder="10倍不计分" value="tenTimes"> 10倍不计分
        <br>
        倍数:
        <input type="checkbox" name="multiple" placeholder="小胡(鸡胡1倍其他2倍)" value="xiaoHu"> 小胡(鸡胡1倍其他2倍)
        <input type="checkbox" name="multiple" placeholder="鸡胡不能吃胡" value="jiHuCannotChi"> 鸡胡不能吃胡
        <input type="checkbox" name="multiple" placeholder="跟庄1分" value="genZhuang"> 跟庄1分
        <br>
        结算:
        <input type="checkbox" name="settlement" placeholder="流局算杠" value="tieStillKong"> 流局算杠
        <input type="checkbox" name="settlement" placeholder="吃杠杠爆全包" value="kongBaoPayAll"> 吃杠杠爆全包
        <input type="checkbox" name="settlement" placeholder="连庄" value="lianZhuang"> 连庄
        <br>
        奖马:
        <input type="radio" name="awardHorse" placeholder="奖马" value="0"> 无马
        <input type="radio" name="awardHorse" placeholder="奖马" value="2"> 2马
        <input type="radio" name="awardHorse" placeholder="奖马" value="5"> 5马
        <input type="radio" name="awardHorse" placeholder="奖马" value="8"> 8马
        <input type="radio" name="awardHorse" placeholder="奖马" value="10"> 马跟杠
        <br>
        买马:
        <input type="radio" name="buyHorse" placeholder="买马" value="0"> 无马
        <input type="radio" name="buyHorse" placeholder="买马" value="1"> 买1马
        <input type="radio" name="buyHorse" placeholder="买马" value="2"> 买2马
        <input type="radio" name="buyHorse" placeholder="买马" value="3"> 罚1马
        <input type="radio" name="buyHorse" placeholder="买马" value="4"> 罚2马
        <br>
        封顶:
        <input type="radio" name="maxMultiple" placeholder="封顶5倍" value="fiveMultiple"> 封顶5倍
        <input type="radio" name="maxMultiple" placeholder="封顶10倍" value="tenMultiple"> 封顶10倍
        <input type="radio" name="maxMultiple" placeholder="不设封顶" value="noMultiple"> 不设封顶
        <br>
        <input type="submit" value="创建房间">
    </form>
</div>


<div style="display: inline;">
    房间号: ${sessionScope.ROOMID_IN_SESSION}
    <form action="/mahjong/get_room_info" method="post">
        <input hidden="true" type="text" name="roomId" placeholder="房间号" value="${sessionScope.ROOMID_IN_SESSION}">
        <input type="submit" value="获取房间信息">
    </form>
    房间信息:
    玩法: ${sessionScope.ROOMINFO_IN_SESSION.playMethod} <br>
    房主: ${sessionScope.ROOMINFO_IN_SESSION.owner} <br>
    局数: ${sessionScope.ROOMINFO_IN_SESSION.round} <br>
    钻石: ${sessionScope.ROOMINFO_IN_SESSION.diamond} <br>
    扩展: ${sessionScope.ROOMINFO_IN_SESSION.extend} <br>
    奖马: ${sessionScope.ROOMINFO_IN_SESSION.awardHorse} <br>
    结算: ${sessionScope.ROOMINFO_IN_SESSION.settlement} <br>
</div>

<div style="display: inline;">
    房间号: ${sessionScope.ROOMID_IN_SESSION == null ? "解散成功" : "还未解散"}
    <form action="/mahjong/dismiss_room" method="post">
        <input type="text" name="isDismiss" placeholder="不填表示申请..0表示同意..1表示拒绝">
        <input type="submit" value="解散房间">
    </form>
</div>

</body>