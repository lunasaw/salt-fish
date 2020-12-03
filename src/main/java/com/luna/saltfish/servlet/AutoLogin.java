package com.luna.saltfish.servlet;


import com.luna.saltfish.dbHandle.UserHandle;
import com.luna.saltfish.tools.LoginVerify;
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
    public AutoLogin() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession ses = req.getSession();

        if (LoginVerify.isLogin(req)) {
            chain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = req.getCookies();
        UserHandle userHandle = new UserHandle();
        String emailCookie = null;
        /*
         * **重要**：//日后修复标记：这里仅用了email作为cookie并用于验证，极不安全
         */
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("LOGIN_EMAIL".equals(cookie.getName())) {
                    emailCookie = cookie.getValue();
                    try {
                        if (userHandle.findByEmail(emailCookie) != null) {
                            User user = userHandle.findByEmail(emailCookie);
                            if (user != null) {
                                ses.setAttribute("loginUser", user);
                                ses.setAttribute("isLogined", true);
                            } else {
                                //未检测到cookie，不做任何事
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        userHandle.close();
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
