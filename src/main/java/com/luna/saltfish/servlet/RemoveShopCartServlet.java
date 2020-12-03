package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.dbHandle.ShopCartHandle;
import com.luna.saltfish.tools.LoginVerify;
import com.luna.saltfish.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 从购物车移除一个物品
 */
@WebServlet("/RemoveShopCartServlet")
/**
 * @author luna@mac
 */
public class RemoveShopCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveShopCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
        if (LoginVerify.isLogin(request)) {
            User loginUser = (User)request.getSession().getAttribute(UserLoginConstant.LOGIN_USER);
            int loginUserId = loginUser.getId();
            ShopCartHandle shopCartHandle = new ShopCartHandle();
            try {
                if (shopCartHandle.removeList(goodsId, loginUserId)) {
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("false");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print("false");
            } finally {
                shopCartHandle.close();
            }
        } else {
            response.getWriter().print("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
