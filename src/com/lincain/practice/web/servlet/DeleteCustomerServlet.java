package com.lincain.practice.web.servlet;

import com.lincain.practice.service.CustomerService;
import com.lincain.practice.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String _id = request.getParameter("id");
        String _currentPage = request.getParameter("currentPage");
        if ((_id == null && "".equals(_id)) || (_currentPage == null && "".equals(_currentPage))){
            request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }else {
            try {
                int id = Integer.parseInt(_id);
                int currentPage = Integer.parseInt(_currentPage);
                customerService.deleteCustomer(id);
                response.sendRedirect(request.getContextPath() + "/customerListByPageServlet?currentPage=" + currentPage);
            }catch (Exception e){
                request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
                response.sendRedirect(request.getContextPath() + "/fail.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
