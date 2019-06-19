package com.lincain.practice.web.servlet;

import com.lincain.practice.domain.City;
import com.lincain.practice.service.CityService;
import com.lincain.practice.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cityListServlet")
public class CityListServlet extends HttpServlet {

    private CityService cityService = new CityServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        List<City> cities = cityService.showAllCity();

        if (cities == null || cities.isEmpty()){
            request.getSession().setAttribute("fail_msg","服务器繁忙，请稍后再试");
            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }else {
            String referer = request.getParameter("referer");
            String currentPage = request.getParameter("currentPage");
            request.getSession().setAttribute("cities",cities);
            request.getSession().setAttribute("totalCount",request.getParameter("totalCount"));
            if ("add".equals(referer))
                response.sendRedirect(request.getContextPath() + "/add.jsp?currentPage=" + currentPage);
            if ("update".equals(referer))
                response.sendRedirect(request.getContextPath() + "/update.jsp?currentPage=" + currentPage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
