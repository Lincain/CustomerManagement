package com.lincain.practice.web.servlet;

import com.lincain.practice.domain.Customer;
import com.lincain.practice.service.CustomerService;
import com.lincain.practice.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customerListServlet")
public class CustomerListServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        List<Customer> customers = customerService.findAllCustomer();

        if (customers == null || customers.isEmpty()){
            request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }else {
            request.setAttribute("customers",customers);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
