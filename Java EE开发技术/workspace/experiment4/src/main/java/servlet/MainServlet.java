package servlet;

import db.DB;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class MainServlet
 */
//@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gb2312");
//        response.setContentType("gb2312");
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        DB db = new DB();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = db.checkUser(username, pwd);
        }
        session.setAttribute("user", user);
        if (user != null) {
            ArrayList al1 = db.findUserInfo();
            session.setAttribute("al1", al1);

            ArrayList al2 = db.findLyInfo();
            session.setAttribute("al2", al2);
            response.sendRedirect("main.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
