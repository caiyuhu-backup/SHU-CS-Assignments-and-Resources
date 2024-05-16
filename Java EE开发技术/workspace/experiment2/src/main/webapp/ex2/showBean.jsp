<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>showBean</title>
</head>
<body>
<%
    request.setCharacterEncoding("gb2312");
%>
<jsp:useBean id="student"
             class="shu.silence.experiment2.Student" scope="session"/>

<jsp:getProperty name="student" property="name"/>
<jsp:getProperty name="student" property="password"/>
<jsp:getProperty name="student" property="sex"/>
<jsp:getProperty name="student" property="age"/>
<jsp:getProperty name="student" property="country"/>
<jsp:getProperty name="student" property="email"/>
<jsp:getProperty name="student" property="phone"/>

</body>
</html>
