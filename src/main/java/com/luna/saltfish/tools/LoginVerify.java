package com.luna.saltfish.tools;

import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author luna@mac
 */
public class LoginVerify {

    /**
     * 登录验证
     * 判断是否为管理员登录
     * 
     * @param request
     * @return
     */
    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        if (isLogin(request) && ((User)ses.getAttribute(UserLoginConstant.LOGIN_USER)).getId() < 1000) {
            return true;
        }
        return false;
    }

    /**
     * 是否已经登录
     * 
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        if (ses.getAttribute(UserLoginConstant.IS_LOGIN) != null
            && ses.getAttribute(UserLoginConstant.IS_LOGIN).equals(true)
            && ses.getAttribute(UserLoginConstant.LOGIN_USER) != null) {
            return true;
        }
        return false;
    }
}
