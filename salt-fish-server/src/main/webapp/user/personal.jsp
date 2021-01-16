<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page
        import="java.sql.*,com.luna.saltfish.entity.*,com.luna.saltfish.constant.*,com.luna.saltfish.tools.*,javax.servlet.http.HttpSession,com.luna.saltfish.dao.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    Boolean isMe = false;
    Boolean isLogin = LoginVerify.isLogin(request);
    User me = null;
    User user = null;
    Integer userId = 0;
    if (request.getParameter("userId") != null && request.getParameter("userId").length() != 0) {
        UserHandle userHandle = new UserHandle();
        userId = Integer.parseInt(request.getParameter("userId"));
        user = userHandle.findById(userId);

    }

    if (isLogin) {
        me = (User) session.getAttribute(UserLoginConstant.LOGIN_USER);
        if ((userId != 0 && me.getId() == user.getId()) || userId == 0) {
            isMe = true;
            user = me;
            request.setAttribute("isMe", true);
        }
    } else {
        request.getRequestDispatcher("../user/login.jsp?login-info=" + java.net.URLEncoder.encode("你应该先登录，之后才可查看自己或其他人的个人信息页", "UTF-8")).forward(request, response);
        return;
    }

    String tab = request.getParameter("tab");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <jsp:include page="../site/head.jsp"/>
    <title>用户中心 - salt-fish</title>
</head>
<body>
    <jsp:include page="../site/header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="row">
                    <div class="col-md-12">
                        <div class="list-group">
                            <div class="list-group-item personal-main-info">

                                <img class="img-rounded img-personal-main-info"
                                     src="<%=user.getImg() %>"/>
                                <div class="row detail-goods text-muted">姓名：<%=user.getName() %>
                                </div>
                                <div class="row detail-goods text-muted">邮箱：<%=user.getEmail() %>
                                </div>

                            </div>
                            <a href="user/personal.jsp?tab=info&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("info")?"active":"" %>">
                                个人信息</a>

                            <%
                                if (isMe) {
                            %>
                            <%
                                if (LoginVerify.isAdmin(request)) {
                            %>

                            <a href="user/personal.jsp?tab=auditing&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("auditing")?"active":"" %>">
                                物品审核</a>
                            <%}%>
                            <a href="user/personal.jsp?tab=mess&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("mess")?"active":"" %>">
                                站内消息</a>
                            <a href="user/personal.jsp?tab=shopcart&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("shopcart")?"active":"" %>">
                                购物车</a>
                            <a href="user/personal.jsp?tab=history&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("history")?"active":"" %>">
                                购买历史</a>
                            <a href="user/personal.jsp?tab=pushed&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("pushed")?"active":"" %>">
                                我发布的</a>
                            <a href="user/personal.jsp?tab=push&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("push")?"active":"" %>">
                                发布商品</a>
                            <a href="user/personal.jsp?tab=like&pn=1&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("like")?"active":"" %>">
                                收藏夹 </a>
                            <!-- 并不需要设置页
							<a href="user/personal.jsp?tab=setting&userId=<%=user.getId() %>"
							class="list-group-item <%=tab.equals("setting")?"active":"" %>">
							设置</a>
							-->
                            <%} else {%>
                            <a href="user/personal.jsp?tab=pushed&userId=<%=user.getId() %>"
                               class="list-group-item <%=tab.equals("pushed")?"active":"" %>">
                                他发布的商品</a>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <%if (tab.equals("push")) {%>
                <jsp:include page="../site/personal/push.jsp"/>
                <%} else if (tab.equals("info")) {%>
                <jsp:include page="../site/personal/info.jsp"/>
                <%} else if (tab.equals("auditing")) {%>
                <jsp:include page="../site/personal/auditing.jsp"/>
                <%} else if (tab.equals("shopcart")) {%>
                <jsp:include page="../site/personal/shopcart.jsp"/>
                <%} else if (tab.equals("like")) {%>
                <jsp:include page="../site/personal/like.jsp"/>
                <%} else if (tab.equals("mess")) {%>
                <jsp:include page="../site/personal/mess.jsp"/>
                <%} else if (tab.equals("pushed")) {%>
                <jsp:include page="../site/personal/pushed.jsp"/>
                <%} else if (tab.equals("history")) {%>
                <jsp:include page="../site/personal/history.jsp"/>
                <%}%>
            </div>
        </div>
    </div>
    <jsp:include page="../site/footer.jsp"/>
</body>
</html>