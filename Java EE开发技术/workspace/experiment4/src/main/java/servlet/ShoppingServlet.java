package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Silence
 * @date: 2022/4/5 15:33
 * @description:
 */
public class ShoppingServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("gb2312");
        response.setContentType("text/html");
        response.setCharacterEncoding("gb2312");

        String productId = request.getParameter("productId");
        String date = request.getParameter("date");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
