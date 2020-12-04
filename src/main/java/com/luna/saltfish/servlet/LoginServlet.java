package com.luna.saltfish.servlet;

import com.luna.saltfish.constant.UserLoginConstant;
import com.luna.saltfish.dbHandle.SessionHandle;
import com.luna.saltfish.dbHandle.UserHandle;
import com.luna.saltfish.tools.MD5;
import com.luna.saltfish.vo.Session;
import com.luna.saltfish.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
/**
 * @author luna@mac
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();

    }

    /**
     * 登录验证，设置session和coookies
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String inputEmail = request.getParameter("inputEmail");
        String inputPassword = request.getParameter("inputPassword");
        String autoLogin = request.getParameter("auto_login");
        UserHandle userHandle = new UserHandle();
        try {
            if (userHandle.findByEmail(inputEmail) != null) {
                User user = userHandle.findByEmail(inputEmail);
                String pass = MD5.getMD5(MD5.getMD5(inputPassword));
                if (user.getPwd().equals(pass)) {
                    if (autoLogin != null && autoLogin.equals("on")) {
                        Cookie cookieE = new Cookie("LOGIN_EMAIL", inputEmail);
                        cookieE.setMaxAge(60 * 60 * 24 * 7);
                        // cookieE.setDomain("/");
                        response.addCookie(cookieE);

                        // 通过Cookie设置Seession
                        String inputEmailMd5 = MD5.getMD5(inputEmail);
                        Session session = new Session();
                        session.setKey(inputEmailMd5);
                        session.setUserId(user.getId());

                        SessionHandle sessionHandle = new SessionHandle();
                        if (sessionHandle.getSession(user.getId()) == null) {
                            sessionHandle.insert(session);
                        }
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute(UserLoginConstant.LOGIN_USER, user);
                    session.setAttribute(UserLoginConstant.IS_LOGIN, true);
                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("isLoginOk", "false");
                    request.getRequestDispatcher("user/login.jsp").forward(
                        request, response);
                }
            } else {
                request.setAttribute("isLoginOk", "false");
                request.getRequestDispatcher("user/login.jsp").forward(
                    request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userHandle.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
