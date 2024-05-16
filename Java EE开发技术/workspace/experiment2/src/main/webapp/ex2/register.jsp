<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form id="form" method="post" action="useBean.jsp">
    姓名：<br/>
    <input type="text" name="name">
    <hr/>
    密码：<br/>
    <input type="password" name="password">
    <hr/>
    性别：<br/>
    男：<input type="radio" name="sex" value="男">
    女：<input type="radio" name="sex" value="女">
    <hr/>
    年龄：<br/>
    <input type="number" name="age">
    <hr/>
    来自的国家：<br/>
    <select name="country">
        <option value="中国">中国</option>
        <option value="美国">美国</option>
        <option value="俄罗斯">俄罗斯</option>
        <option value="日本">日本</option>
        <option value="德国">德国</option>
        <option value="澳大利亚">澳大利亚</option>
    </select>
    邮箱：<input type="email" name="email">
    电话：<input type="tel" name="phone">

    <hr/>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>
