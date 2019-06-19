package com.lincain.practice.web.servlet;

import com.lincain.practice.domain.User;
import com.lincain.practice.service.UserService;
import com.lincain.practice.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();



        // 判断验证码
        String verifyCode_servlet = (String) session.getAttribute("VERIFY_CODE");
        String verifyCode_page = request.getParameter("verifyCode");
        session.removeAttribute("VERIFY_CODE");
        if (!verifyCode_servlet.equalsIgnoreCase(verifyCode_page)){
            request.setAttribute("login_error_msg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 验证用户名和密码
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User result = userService.login(user);

        if (result == null){
            request.setAttribute("login_error_msg","用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            session.setAttribute("user", result);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
