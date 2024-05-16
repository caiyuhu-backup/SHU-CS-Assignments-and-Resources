<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>信息展示</title>
</head>
<body>
<%
    //设置解码方式，对于简体中文，使用gb2312解码
    request.setCharacterEncoding("gb2312");

//下面依次获取表单域的值
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");

%>
<!-- 下面依次输出表单域的值 -->
您的名字：<%=name%>
<hr/>
您的密码：<%=password%>
<hr/>
您的性别：<%=gender%>
<hr/>
</body>
</html>
