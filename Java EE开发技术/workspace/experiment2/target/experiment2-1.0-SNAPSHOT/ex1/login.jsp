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
    <title>��¼</title>
</head>
<body>
<form id="loginForm" method="post" action="checkLogin.jsp">
    ������<br/> <input type="text" name="name" placeholder="����">
    <hr/>
    ���룺<br/> <input type="password" name="password" placeholder="����">
    <hr/>
    �Ա�<br/>
    �У�<input type="radio" name="gender" value="��">
    Ů��<input type="radio" name="gender" value="Ů">
    <input type="submit" value="�ύ">
    <input type="reset" value="����">
</form>

</body>
</html>
