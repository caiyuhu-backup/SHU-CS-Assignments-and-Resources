package com.example.experiment6.action;

import com.example.experiment6.db.DB;
import com.example.experiment6.model.LyTable;
import com.example.experiment6.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddAction extends ActionSupport {
    private LyTable ly;

    public String execute() {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();

        String title = ly.getTitle();
        String content = ly.getContent();
        User user = null;
        user = (User) session.get("user");
        LyTable ly = new LyTable();
        ly.setUserId(user.getId());
        ly.setDate(new Date());
        ly.setTitle(title);
        ly.setContent(content);
        List<LyTable> al = (List<LyTable>) session.get("al");
        al.add(ly);

        DB DBConn = new DB();
        String sql = "insert into TEST..lyTable (userId,date,title,content) values(?,?,?,?)";

        PreparedStatement pstmt = (PreparedStatement) DBConn.ct;
        try {
            pstmt.setInt(1, ly.getUserId());
            pstmt.setDate(2, (java.sql.Date) ly.getDate());
            pstmt.setString(3, ly.getTitle());
            pstmt.setString(4, ly.getContent());
            pstmt.executeUpdate();
            pstmt.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}