<%@ page language="java" import="java.util.*" pageEncoding="gb2312" %>
<%@page import="model.User" %>
<%@page import="db.DB" %>
<%@ page import="model.Product" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body bgcolor="#E3E3E3">
<form action="success.jsp" method="post">
    <table border="1">
        <caption>所有用户信息</caption>
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>删除</th>
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
                <input type="submit" value="删除"/>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <table border="1">
        <caption>所有商品信息</caption>
        <tr>
            <th>序号</th>
            <th>商品号</th>
            <th>商品名称</th>
            <th>删除</th>
            <th>购买</th>
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
                <input type="submit" value="删除"/>
            </td>
            <td>
                <a href="javascript:void(0)" οnclick="delProduct('${pro.pid}')">
                    购买
                </a>
            </td>
        </tr>

        <%
            }
        %>
    </table>

    <input type="submit" value="添加用户"/>
    <input type="submit" value="添加商品"/>
</form>
<script>
    function delProduct(pid) {
        //alert();
        var isDel = confirm("您确定要删除吗？")
        if (isDel) {
            //要删除
            location.href = "${pageContext.request.contextPath}/adminDelProduct?pid=" + pid;
        }
    }
</script>

</body>
</html>
