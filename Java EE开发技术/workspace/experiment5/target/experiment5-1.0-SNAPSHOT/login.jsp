<%@ page language="java" import="java.util.*"   pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ÓÃ»§µÇÂ¼</title>
</head>
<body>
<div align="right">
    <s:a href="register.jsp"><font color="blue"><h5>»¹Î´×¢²á£¿µã»÷×¢²á</h5></font></s:a>
</div>

<div style="color: blue">
    <s:form action="login" method="post">
        <s:textfield name="username" label="ÕËºÅ"></s:textfield>
        <s:password name="password" label="ÃÜÂë"></s:password>
        <s:submit value="µÇÂ½"></s:submit>
    </s:form>
</div>
</body>
</html>