<%@ page language="java" pageEncoding="gb2312" %>
<html>
<head>
    <title>�������԰�</title>
</head>
<body bgcolor="#E3E3E3">
<div>��¼����������</div>
<div>���룺123</div>


<s:form action="LoginAction" method="post" theme="simple">
    <table>
        <caption>�û���¼</caption>
        <td>�û���</td>
        <td><s:textfield name="user.username" size="20"></s:textfield></td>
        <td>����</td>
        <td><s:password name="user.password" size="20"></s:password></td>
    </table>
    <s:submit value="�ύ"/>
    <s:reset value="����"/>
    <input type="button" value="ע��"/>

</s:form>

���ûע�ᵥ��<a href="register.jsp">����</a>ע�ᣡ

</body>
</html>