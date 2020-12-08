package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.ResultConstant;
import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.dao.CollectHandle;
import com.luna.saltfish.tools.LoginVerify;
import com.luna.saltfish.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckCollectServlet")
/**
 * @author luna@mac
 */
public class CheckCollectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (LoginVerify.isLogin(request)) {
            User user = (User)request.getSession().getAttribute(UserLoginConstant.LOGIN_USER);
            int goodsId = Integer.parseInt(request.getParameter("goodsId"));
            CollectHandle collectHandle = new CollectHandle();
            try {
                boolean b = collectHandle.checkCollect(user.getId(), goodsId);
                if (b) {
                    response.getWriter().print(ResultConstant.SUCCESS);
                } else {
                    response.getWriter().print(ResultConstant.ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.getWriter().print("unLogin");
        }
    }
}
