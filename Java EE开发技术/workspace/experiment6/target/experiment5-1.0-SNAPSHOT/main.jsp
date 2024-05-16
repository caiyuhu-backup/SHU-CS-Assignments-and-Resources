<html>
<head>


    <title>留言板信息</title>
</head>

<body>
<s:form action="add.jsp" method="post" theme="simple">
    <table border="1">
        <caption>留言信息</caption>
        <tr>
            <th>留言人姓名</th>
            <th>留言时间</th>
            <th>留言标题</th>
            <th>留言内容</th>
        </tr>
        <%

            SqlserverDBConn DBConn=new SqlserverDBConn();
            Connection conn=DBConn.getConnection();
            PreparedStatement pstmt=null;
            List<LyTable> al=(List<LyTable>)session.getAttribute("al");
            Iterator it=al.iterator();
            while(it.hasNext()){
                LyTable ly=(LyTable)it.next();
                String username=null;
                String sql="select username from TEST..userTable where id=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1, ly.getUserId());
                ResultSet rs=pstmt.executeQuery();
                //String username=rs.getString(1);
                while(rs.next()){
                    username=rs.getString(1);
                }
        %>
        <tr>
            <td><%=username%></td>
            <td><%=ly.getDate() %></td>
            <td><%=ly.getTitle() %></td>
            <td><%=ly.getContent() %></td>
        </tr>
        <%
            }
        %>

    </table>

    <s:submit value="添加留言" />

</s:form>
</body>
</html>