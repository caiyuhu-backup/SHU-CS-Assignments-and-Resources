<%@ page language="java" import="java.util.*" pageEncoding="gb2312" %>
<%@page import="model.User" %>
<%@page import="db.DB" %>
<%@ page import="model.Product" %>
<html>
<head>
    <title>�û���Ϣ</title>
</head>
<body bgcolor="#E3E3E3">
<form action="success.jsp" method="post">
    <table border="1">
        <caption>�����û���Ϣ</caption>
        <tr>
            <th>���</th>
            <th>����</th>
            <th>ɾ��</th>
        </tr>
        <%
            ArrayList al1 = (ArrayList) session.getAttribute("al1");
            Iterator iter1 = al1.iterator();
            while (iter1.hasNext()) {
                User user = (User) iter1.next();
        %>
        <tr>
            <td><%= new DB().getUserName(user.getId()) %>
            </td>
            <td><%= user.getUsername() %>
            </td>
            <td>
                <input type="submit" value="ɾ��"/>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <table border="1">
        <caption>������Ʒ��Ϣ</caption>
        <tr>
            <th>���</th>
            <th>��Ʒ��</th>
            <th>��Ʒ����</th>
            <th>ɾ��</th>
        </tr>
        <%
            ArrayList al2 = (ArrayList) session.getAttribute("al2");
            Iterator iter2 = al2.iterator();
            while (iter2.hasNext()) {
                Product product = (Product) iter2.next();
        %>
        <tr>
            <td><%= product.getId() %>
            </td>
            <td><%= product.getProductCode() %>
            </td>
            <td><%= product.getProductName() %>
            </td>
            <td>
                <input type="submit" value="ɾ��"/>
            </td>
        </tr>

        <%
            }
        %>
    </table>
    <input type="submit" value="����û�"/>
    <input type="submit" value="�����Ʒ"/>
</form>
</body>
</html>
