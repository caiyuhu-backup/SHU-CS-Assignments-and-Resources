<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
    <title>����¼</title>
</head>
<body>
<%
    PrintWriter writer = response.getWriter();
    writer.println("response��ʼ\n");
    //��ȡ��������ͷ������
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        //��ȡÿ�����󡢼����Ӧ��ֵ
        writer.println(headerName + "-->" + request.getHeader(headerName) + "<br/>");
    }
    writer.println("<hr/>");

    String method = request.getMethod();
    writer.println(method);

%>

<jsp:forward page="showLogin.jsp"/>
</body>
</html>
