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
    <title>��Ϣչʾ</title>
</head>
<body>
<%
    //���ý��뷽ʽ�����ڼ������ģ�ʹ��gb2312����
    request.setCharacterEncoding("gb2312");

//�������λ�ȡ�����ֵ
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");

%>
<!-- ����������������ֵ -->
�������֣�<%=name%>
<hr/>
�������룺<%=password%>
<hr/>
�����Ա�<%=gender%>
<hr/>
</body>
</html>
