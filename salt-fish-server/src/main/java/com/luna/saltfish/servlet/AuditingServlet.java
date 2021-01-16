package com.luna.saltfish.servlet;


import com.luna.saltfish.constant.GoodsPassConstant;
import com.luna.saltfish.constant.GoodsStatusConstant;
import com.luna.saltfish.constant.ResultConstant;
import com.luna.saltfish.dao.GoodsHandle;
import com.luna.saltfish.entity.Goods;
import com.luna.saltfish.tools.LoginVerify;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AuditingServlet
 */
@WebServlet("/AuditingServlet")
/**
 * @author luna@mac
 */
public class AuditingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuditingServlet() {
        super();
    }

    /**
     * Servlet:验证待审核物品
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginVerify.isAdmin(request)) {
            int isPass = Integer.parseInt(request.getParameter("pass"));
            int goodsId = Integer.parseInt(request.getParameter("goodsId"));
            boolean isSuc = false;
            GoodsHandle goodsHandle = new GoodsHandle();
            try {
                Goods goods = goodsHandle.findById(goodsId);
                if (isPass == GoodsPassConstant.IS_PASS) {
                    goods.setStatus(GoodsStatusConstant.REVIEW_ED);
                    isSuc = true;
                } else if (isPass == GoodsPassConstant.NO_PASS) {
                    goods.setStatus(GoodsStatusConstant.REVIEW_FAIL);
                    isSuc = true;
                } else {
                    response.getWriter().print(ResultConstant.ERROR);
                }
                if (isSuc) {
                    goodsHandle.doUpdate(goods);
                    response.getWriter().print(ResultConstant.SUCCESS);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print(ResultConstant.ERROR);
            } finally {

            }
        } else {
            response.getWriter().print(ResultConstant.ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
