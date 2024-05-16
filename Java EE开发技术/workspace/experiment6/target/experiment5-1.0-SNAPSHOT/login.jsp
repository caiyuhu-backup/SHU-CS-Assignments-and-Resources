<%@ page language="java" pageEncoding="gb2312" %>
<html>
<head>
    <title>简易留言板</title>
</head>
<body bgcolor="#E3E3E3">
<div>登录名：胡才郁</div>
<div>密码：123</div>


<s:form action="LoginAction" method="post" theme="simple">
    <table>
        <caption>用户登录</caption>
        <td>用户名</td>
        <td><s:textfield name="user.username" size="20"></s:textfield></td>
        <td>密码</td>
        <td><s:password name="user.password" size="20"></s:password></td>
    </table>
    <s:submit value="提交"/>
    <s:reset value="重置"/>
    <input type="button" value="注册"/>

</s:form>

如果没注册单击<a href="register.jsp">这里</a>注册！

</body>
</html>