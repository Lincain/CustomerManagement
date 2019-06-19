package com.lincain.practice.web.servlet;

import com.lincain.practice.domain.PageBean;
import com.lincain.practice.service.CustomerService;
import com.lincain.practice.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/customerListByPageServlet")
public class CustomerListByPageServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 修改请求和响应的字符集编码，并获取session
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        // 首先判断从哪个页面跳转过来的
        // 如果是index.jsp则将session中的数据删除，除了user
        if (request.getHeader("referer").contains("index.jsp")){
            // 删除session中的部分数据
            session.removeAttribute("conditions");
            session.removeAttribute("rows");
        }

        // 获取页面提交的参数，包括条件查询(name,address,email)和分页查询(currentPage,rows)参数
        Map<String, String[]> map = request.getParameterMap();
        // 创建一个List集合用来储存条件查询参数
        Map<String, String[]> conditions = new HashMap<>();
        // 对map集合进行遍历，符合条件的添加到conditions集合中
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key))
                continue;
            conditions.put(key,map.get(key));
        }

        // 表单中的查询参数的优先级高于session中的查询参数
        // 如果页面没有传递条件查询参数，则从session中获取，
        // 如果两者均没有，则向后端传递一个空的map集合
        if (conditions.size() == 0) {
            conditions = (conditions = (Map<String, String[]>) session.getAttribute("conditions")) != null
                    ? conditions : new HashMap<String, String[]>();
        }
        // 将该参数集合储存在session中
        session.setAttribute("conditions",conditions);

        // 单独获取分页查询参数(currentPage,rows)
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        // 对分页查询参数进行判断
        if (currentPage == null || "".equals(currentPage)) {
            String _currentPage = (String) session.getAttribute("currentPage");
            session.removeAttribute("currentPage");
            if (_currentPage != null){
                currentPage = _currentPage;
            }else {
                currentPage = "1";
            }
        }
        if (rows == null || "".equals(rows)) {
            String _rows = (String) session.getAttribute("rows");
            if ( _rows != null){
                rows = _rows;
            }else {
                rows = "5";
            }
        }
        session.setAttribute("rows", rows);

        // 调用service层代码
        PageBean pageBean = customerService.selectCustomerByPage(currentPage, rows, conditions);

        // 根据结果进行跳转
        if (pageBean != null) {
            request.setAttribute("pageBean", pageBean);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }else {
            session.setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
