package com.lincain.practice.web.servlet;

import com.lincain.practice.service.CustomerService;
import com.lincain.practice.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String[] ids = request.getParameterValues("uid");
        if (ids == null || ids.length == 0){
            request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }else {
            try {
                customerService.deleteSelected(ids);
            } catch (Exception e) {
                request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
                response.sendRedirect(request.getContextPath() + "/fail.jsp");
            }

            response.sendRedirect(request.getContextPath() + "/customerListByPageServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
