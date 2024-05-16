<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form id="loginForm" method="post" action="checkLogin.jsp">
    姓名：<br/> <input type="text" name="name" placeholder="汉字">
    <hr/>
    密码：<br/> <input type="password" name="password" placeholder="数字">
    <hr/>
    性别：<br/>
    男：<input type="radio" name="gender" value="男">
    女：<input type="radio" name="gender" value="女">
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>

</body>
</html>
