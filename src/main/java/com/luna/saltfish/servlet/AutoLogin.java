package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.dao.SessionHandle;
import com.luna.saltfish.dao.UserHandle;
import com.luna.saltfish.tools.LoginVerify;
import com.luna.saltfish.tools.MD5;
import com.luna.saltfish.vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class AutoLogin
 */
@WebFilter("/AutoLogin")
/**
 * @author luna@mac
 */
public class AutoLogin implements Filter {
    public AutoLogin() {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession ses = req.getSession();

        if (LoginVerify.isLogin(req)) {
            chain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = req.getCookies();
        UserHandle userHandle = new UserHandle();
        SessionHandle sessionHandle = new SessionHandle();
        String emailCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("LOGIN_EMAIL".equals(cookie.getName())) {
                    emailCookie = cookie.getValue();
                    try {
                        User user = userHandle.findByEmail(emailCookie);
                        if (user != null) {
                            if (MD5.getMD5(emailCookie).equals(sessionHandle.getSession(user.getId()))) {
                                ses.setAttribute(UserLoginConstant.LOGIN_USER, user);
                                ses.setAttribute(UserLoginConstant.IS_LOGIN, true);
                            }
                        } else {
                            // 未检测到cookie，不做任何事
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}
}
