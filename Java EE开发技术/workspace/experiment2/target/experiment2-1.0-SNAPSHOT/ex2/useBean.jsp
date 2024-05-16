<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>JavaBean </title>
    <%
        request.setCharacterEncoding("gb2312");
    %>
</head>
<body>
<%
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String sex = request.getParameter("sex");
    String age = request.getParameter("age");
    String country = request.getParameter("country");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
%>

<jsp:useBean id="student"
             class="shu.silence.experiment2.Student" scope="session"/>
<jsp:setProperty name="student" property="name"
                 value="<%=name%>"/>
<jsp:setProperty name="student" property="password"
                 value="<%=password%>"/>
<jsp:setProperty name="student" property="sex"
                 value="<%=sex%>"/>
<jsp:setProperty name="student" property="age"
                 value="<%=age%>"/>
<jsp:setProperty name="student" property="country"
                 value="<%=country%>"/>
<jsp:setProperty name="student" property="email"
                 value="<%=email%>"/>
<jsp:setProperty name="student" property="phone"
                 value="<%=phone%>"/>


</body>
<jsp:forward page="showBean.jsp"/>


</html>
