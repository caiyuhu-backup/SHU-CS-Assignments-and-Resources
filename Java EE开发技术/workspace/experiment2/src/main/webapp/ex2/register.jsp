<%--
  Created by IntelliJ IDEA.
  User: Silence
  Date: 2022/3/18
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>ע��</title>
</head>
<body>
<form id="form" method="post" action="useBean.jsp">
    ������<br/>
    <input type="text" name="name">
    <hr/>
    ���룺<br/>
    <input type="password" name="password">
    <hr/>
    �Ա�<br/>
    �У�<input type="radio" name="sex" value="��">
    Ů��<input type="radio" name="sex" value="Ů">
    <hr/>
    ���䣺<br/>
    <input type="number" name="age">
    <hr/>
    ���ԵĹ��ң�<br/>
    <select name="country">
        <option value="�й�">�й�</option>
        <option value="����">����</option>
        <option value="����˹">����˹</option>
        <option value="�ձ�">�ձ�</option>
        <option value="�¹�">�¹�</option>
        <option value="�Ĵ�����">�Ĵ�����</option>
    </select>
    ���䣺<input type="email" name="email">
    �绰��<input type="tel" name="phone">

    <hr/>
    <input type="submit" value="�ύ">
    <input type="reset" value="����">
</form>
</body>
</html>
