<%@ page language="java" pageEncoding="gb2312" %>
<html>
<head>
    <title>简易留言板</title>
</head>
<body bgcolor="#E3E3E3">
<form action="mainServlet" method="post">
    <table>
        <caption>用户登录</caption>
        <tr>
            <td>登录名：</td>
            <td><input type="text" name="username" size=”20”/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" size=”21”/></td>
        </tr>
    </table>
    <input type="submit" value="登录"/>
    <input type="reset" value="重置"/>
</form>
默认账号(我是1号顾客)和密码(123123)
</body>
</html>