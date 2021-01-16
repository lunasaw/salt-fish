package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.GoodsStatusConstant;
import com.luna.saltfish.constant.ResultConstant;
import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.dao.GoodsHandle;
import com.luna.saltfish.dao.ShopCartHandle;
import com.luna.saltfish.entity.Goods;
import com.luna.saltfish.entity.User;
import com.luna.saltfish.tools.LoginVerify;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加一个物品到购物车
 * 
 * @author luna
 */
@WebServlet("/ShoppingCartServlet")
/**
 * @author luna@mac
 */
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShoppingCartServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        if (LoginVerify.isLogin(request)) {
            User user = (User)request.getSession().getAttribute(UserLoginConstant.LOGIN_USER);
            int goodsId = Integer.parseInt(request.getParameter("goodsId"));
            ShopCartHandle shopCartHandle = new ShopCartHandle();
            GoodsHandle goodsHandle = new GoodsHandle();
            Goods goods = null;
            try {
                goods = goodsHandle.findById(goodsId);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                if (goods != null && goods.getStatus().equals(GoodsStatusConstant.REVIEW_ED)
                    && shopCartHandle.doSaveShoppingCart(goodsId, user.getId())) {
                    response.getWriter().print(ResultConstant.SUCCESS);
                } else {
                    response.getWriter().print(ResultConstant.ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print(ResultConstant.ERROR);
            }
        } else {
            response.getWriter().print(UserLoginConstant.UN_LOGIN);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
