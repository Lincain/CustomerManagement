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

@WebServlet("/showCustomerServlet")
public class ShowCustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String _id = request.getParameter("id");
        if (_id == null || "".equals(_id)){
            request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }else {
            try {
                Customer customer =
                        customerService.selectCustomerById(Integer.parseInt(_id));
                if (customer == null){
                    request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
                    response.sendRedirect(request.getContextPath() + "/fail.jsp");
                }else {
                    request.getSession().setAttribute("customer",customer);
                    request.getSession().setAttribute("currentPage",request.getParameter("currentPage"));
                    request.getRequestDispatcher("/cityListServlet?referer=update").forward(request, response);
                }
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
