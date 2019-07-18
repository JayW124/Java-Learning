package servlet;

import model.PageBean;
import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangjie on 2019/7/18
 * Descriptions:
 */
@WebServlet("/findStuByPageServlet")
public class FindStuByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取当前页码和每页显示页码
        String curPage = request.getParameter("curPage");
        String rows = request.getParameter("rows");
        if (curPage == null || "".equals(curPage)) {
            curPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }
        //2.调用service查询
        StudentService service = new StudentServiceImpl();
        PageBean pb = service.findByPage(curPage, rows);
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
