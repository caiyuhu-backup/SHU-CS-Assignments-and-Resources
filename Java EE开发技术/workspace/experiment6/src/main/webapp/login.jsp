<%@ page language="java" pageEncoding="gb2312" %>
<html>
<head>
    <title>�������԰�</title>
</head>
<body bgcolor="#E3E3E3">
<div>��¼����������</div>
<div>���룺123</div>

<sx:head/>
<s:form action="LoginAction" method="post" theme="simple">

    <table>
        <caption>�û���¼</caption>
        <tr>
            <td>�û���</td>
            <td><s:textfield name="user.username" size="20"></s:textfield></td>
        </tr>
        <tr>
            <td>����</td>
            <td><s:password name="user.password" size="20"></s:password></td>
        </tr>
    </table>
    <s:submit value="�ύ"/>
    <s:reset value="����"/>
    <input type="button" value="ע��"/>


<%--    <form action="LoginAction" method="post">--%>
<%--        <table>--%>
<%--            <caption>�û���¼</caption>--%>
<%--            <tr>--%>
<%--                <td>��¼����</td>--%>
<%--                <td><input type="text" name="username" size=��20��/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>����:</td>--%>
<%--                <td><input type="password" name="password" size=��21��/></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <input type="submit" value="��¼"/>--%>
<%--        <input type="reset" value="����"/>--%>
<%--    </form>--%>
<%--    ���ûע�ᵥ��<a href="register.jsp">����</a>ע�ᣡ--%>


</s:form>

���ûע�ᵥ��<a href="register.jsp">����</a>ע�ᣡ

</body>
</html>