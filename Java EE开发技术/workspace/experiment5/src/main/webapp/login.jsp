<%@ page language="java" import="java.util.*"   pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>�û���¼</title>
</head>
<body>
<div align="right">
    <s:a href="register.jsp"><font color="blue"><h5>��δע�᣿���ע��</h5></font></s:a>
</div>

<div style="color: blue">
    <s:form action="login" method="post">
        <s:textfield name="username" label="�˺�"></s:textfield>
        <s:password name="password" label="����"></s:password>
        <s:submit value="��½"></s:submit>
    </s:form>
</div>
</body>
</html>