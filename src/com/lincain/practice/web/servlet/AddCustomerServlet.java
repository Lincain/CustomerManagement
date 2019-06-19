package com.lincain.practice.web.servlet;

import com.lincain.practice.domain.Customer;
import com.lincain.practice.service.CustomerService;
import com.lincain.practice.service.impl.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addCustomerServlet")
public class AddCustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Map<String, String[]> map = request.getParameterMap();

        if (map == null || map.isEmpty()){
            request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }else {
            Customer customer = new Customer();
            try {
                BeanUtils.populate(customer, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            customerService.addCustomer(customer);
            String _totalCount = (String) request.getSession().getAttribute("totalCount");
            int rows = Integer.parseInt((String) request.getSession().getAttribute("rows"));
            int totalCount = Integer.parseInt(_totalCount) + 1;
            Integer currentPage = totalCount % rows == 0 ? totalCount/rows : totalCount/rows + 1;
            response.sendRedirect(request.getContextPath() + "/customerListByPageServlet?currentPage="+ currentPage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
