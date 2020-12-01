package com.luna.saltfish.servlet;

import com.luna.saltfish.dbHandle.CollectHandle;
import com.luna.saltfish.dbHandle.GoodsHandle;
import com.luna.saltfish.tools.LoginVerify;
import com.luna.saltfish.vo.Goods;
import com.luna.saltfish.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class collectServlet
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    /**
     * 添加一个物品到收藏夹
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (LoginVerify.isLogin(request)) {
            User user = (User)request.getSession().getAttribute("loginUser");
            int userId = user.getId();
            int goodsId = Integer.parseInt(request.getParameter("goodsId"));
            CollectHandle collectHandle = new CollectHandle();
            GoodsHandle goodsHandle = new GoodsHandle();
            Goods goods = null;
            try {
                goods = goodsHandle.findById(goodsId);
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                goodsHandle.close();
            }
            try {
                if (goods != null && goods.getStates() == 2 && collectHandle.doCreate(userId, goodsId)) {
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print("error");
            } finally {
                collectHandle.close();
            }
        } else {
            response.getWriter().print("unLogin");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
