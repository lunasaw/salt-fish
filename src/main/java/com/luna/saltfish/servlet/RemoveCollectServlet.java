package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.dbHandle.CollectHandle;
import com.luna.saltfish.tools.LoginVerify;
import com.luna.saltfish.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RemoveCollectServlet
 */
@WebServlet("/RemoveCollectServlet")
/**
 * @author luna@mac
 */
public class RemoveCollectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCollectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 从收藏夹移除一个物品
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer goodsId = Integer.parseInt(request.getParameter("goodsid"));
        if (LoginVerify.isLogin(request)) {
            User loginUser = (User)request.getSession().getAttribute(UserLoginConstant.LOGIN_USER);
            int loginUserId = loginUser.getId();
            CollectHandle collectHandle = new CollectHandle();
            try {
                if (collectHandle.removeOneFromCollect(goodsId, loginUserId)) {
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("false");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print("false");
            } finally {
                collectHandle.close();
            }
        } else {
            response.getWriter().print("false");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
