package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.UserLoginConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ExitLoginServlet")
/**
 * @author luna@mac
 */
public class ExitLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ExitLoginServlet() {
        super();
    }

    /**
     * 退出登录，移除cookies和session属性
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(UserLoginConstant.IS_LOGIN, false);
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; ++i) {
            if ("LOGIN_EMAIL".equals(cookies[i].getName())) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
