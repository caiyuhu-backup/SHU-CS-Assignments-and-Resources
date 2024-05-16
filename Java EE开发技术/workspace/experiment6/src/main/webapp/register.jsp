<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>注册</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
<s:form action="RegisterAction" method="post" theme="simple">
	<table>
		<caption>用户注册</caption>
		<tr>
			<td>用户名</td>
			<td>
				<s:textfield name="user.username" />
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td><s:password name="user.password" /></td>
		</tr>
	</table>
	<s:submit value="确认" />
	<s:reset  value="重置" />
</s:form>
</body>
</html>