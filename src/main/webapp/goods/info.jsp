<%@page import="com.luna.saltfish.dbHandle.UserHandle" %>
<%@page
        import="com.luna.saltfish.dbHandle.GoodsHandle,com.luna.saltfish.constant.*,com.luna.saltfish.vo.*,com.luna.saltfish.tools.*,java.util.*,java.text.*,com.luna.saltfish.servlet.*"
%>
<%/*
物品详情页，包含详情和操作按钮
*/%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <jsp:include page="../site/head.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>物品详情</title>
    <script type="text/javascript">
        function toLogin(isLogin) {
            if (!isLogin) {
                window.location.href = "user/login.jsp?login-info=" + "请先登录";
            }
        }

        function collect(goodsId, isLogin) {
            if (isLogin) {
                collectRequest = new XMLHttpRequest();
                collectRequest.onreadystatechange = function () {
                    // 已经收藏readyState=4
                    if ((collectRequest.readyState == 4) && (collectRequest.status == 200)) {
                        if (collectRequest.responseText == "success") {
                            document.getElementById("collectButton").innerHTML = "==已收藏==";
                        }
                    }
                }
                collectRequest.open("GET", "CollectServlet?goodsId=" + goodsId + "&t=" + Math.random(), true);
                collectRequest.send();
            } else {
                window.location.href = "user/login.jsp?login-info=" + "请先登录";
            }
        }


        $(document).ready(function () {
            let searchParams = new URLSearchParams(window.location.search);
            let goodsId = searchParams.get('goodsid');
            collectRequest = new XMLHttpRequest();
            collectRequest.onreadystatechange = function () {
                // 已经收藏readyState=4
                if ((collectRequest.readyState == 4) && (collectRequest.status == 200)) {
                    if (collectRequest.responseText == "success") {
                        document.getElementById("collectButton").innerHTML = "==已收藏==";
                    }
                }
            }
            collectRequest.open("GET", "CheckCollectServlet?goodsId=" + goodsId + "&t=" + Math.random(), true);
            collectRequest.send();

            ShoppingRequest = new XMLHttpRequest();
            ShoppingRequest.onreadystatechange = function () {
                // 已经收藏readyState=4
                if ((collectRequest.readyState == 4) && (collectRequest.status == 200)) {
                    if (collectRequest.responseText == "success") {
                        document.getElementById("addCastButton").innerHTML = "已加入购物车";
                    }
                }
            }
            ShoppingRequest.open("GET", "CheckShoppingServlet?goodsId=" + goodsId + "&t=" + Math.random(), true);
            ShoppingRequest.send();
        });

        function shoppingCart(isLogin, goodsNum, goodsId) {

            if (isLogin) {
                xmlShop = new XMLHttpRequest();
                xmlShop.onreadystatechange = function () {
                    if ((xmlShop.readyState == 4) && (xmlShop.status == 200)) {
                        if (xmlShop.responseText == "success") {
                            document.getElementById("goodsNum").innerHTML = (parseInt(document.getElementById("goodsNum").innerHTML) + 1).toString();
                            document.getElementById("addCastButton").innerHTML = "已加入购物车";
                        } else {
                            document.getElementById("addCastButton").innerHTML = "错误，你可能重复添加了！";
                        }
                    }
                }
                xmlShop.open("GET", "ShoppingCartServlet?goodsId=" + goodsId + "&t=" + Math.random(), true);
                xmlShop.send();
            } else {
                window.location.href = "user/login.jsp?login-info=" + "请先登录";
            }
        }
    </script>
</head>
<body>
    <jsp:include page="../site/header.jsp" flush="true"/>
    <%
        int goodsId = Integer.parseInt(request.getParameter("goodsid"));

        Integer goodsNum = 0;
        GoodsHandle goodsHandle = new GoodsHandle();
        UserHandle userHandle = new UserHandle();
        Goods good = goodsHandle.findById(goodsId);
        if (good == null) {
            out.print("<div class=\"alert alert-danger\" role=\"alert\">" + "没有这个物品！，请返回并检查来源页" + "</div>");
            return;
        }
        boolean isLogin = LoginVerify.isLogin(request);
        pageContext.setAttribute("good", good);
        User Procuteuser = userHandle.findById(good.getProducterId());
        pageContext.setAttribute("Procuteuser", Procuteuser);
        int typeId = good.getTypeId();
        String typeName = "";
        switch (typeId) {
            case 6:
                typeName = "其他";
                break;
            case 1:
                typeName = "书籍";
                break;
            case 2:
                typeName = "生活用品";
                break;
            case 5:
                typeName = "体育";
                break;
            case 3:
                typeName = "衣装";
                break;
            case 4:
                typeName = "电子";
                break;
        }
        Date date = good.getCreatDate();
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String dateStr = myFmt.format(date);
    %>

    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <%
                            out.println("<span style=\"text-align:center;font-size:16px;\" class=\"center-block\">物品详情</span>");

                        %>

                    </div>

                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-5">
                                <img title="右键查看原图" class="info-img" src="<%=good.getImage()%>">
                            </div>
                            <div class="col-md-7 info-goods">
                                <p>
                                <h3 class="info-goods-name"></h3>
                                <p>
                                    ${good.getName()}
                                </p>
                                <p>
                                    <br/>类型：<a target="_blank"
                                               href="<%=basePath %>index.jsp?ceta=${good.getTypeId()}"><%=typeName %>
                                </a><br/>
                                    <br/>
                                </p>
                                <p>
                                    价格：${good.getPrice()}<br/>
                                    <br/>
                                </p>


                                <p>
                                    发布者：
                                    <a target="_blank"
                                       href="user/personal.jsp?tab=info&userid=<%=Procuteuser.getId()%>">${Procuteuser.getName()}</a>
                                    (联系:<a href="mailto:<%=Procuteuser.getEmail()%>">${Procuteuser.getEmail()}
                                </a>
                                    或
                                    <a target="_blank"
                                       href="user/personal.jsp?tab=mess&handle=write&toemail=<%=Procuteuser.getEmail() %>%20==>%20<%=Procuteuser.getName()%>">站内信</a>)
                                    <br/> <br/>
                                </p>
                                <p>
                                    发布时间：<%=dateStr %>
                                    <br/> <br/>
                                </p>
                                <p class="info-goods-content">
                                    物品说明：<%=good.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br />") %>
                                </p>
                            </div>
                        </div>
                        <!-- 确认购买区域 -->
                        <script type="text/javascript">
                            $(document).ready(function () {
                                $("#buy").click(function () {
                                    $(".buy-confirm").show(200);
                                });
                            });
                        </script>
                        <div style="display:none;" class="buy-confirm">
                            <hr/>
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <span class="center-block"
                                                  style="text-align:center;font-size:15px;">确定购买</span>
                                        </div>
                                        <div class="panel-body">
                                            <p>
                                                请输入给卖家的附加消息，然后点击 "确定" 按钮，我们将会通知卖家。
                                            </p>
                                            <%
                                                User user = (User) session.getAttribute(UserLoginConstant.LOGIN_USER);
                                                int userId = 0;
                                                if (user != null)
                                                    userId = user.getId();
                                            %>
                                            <form action="OrderCheckServlet?goodsId=<%=request.getParameter("goodsid")%>&userId=<%=userId%>"
                                                  method="post">
                                                <div class="form-group">
                                                    <textarea class="form-control" name="message-to-seller"
                                                              id="message-to-seller"></textarea>
                                                </div>
                                                <button type="submit" class="pull-left btn btn-default">确认购买</button>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end of 确认购买区域 -->

                        <!-- 提示消息 -->
                        <%
                            if (request.getParameter("info") != null) {
                        %>
                        <div class="alert alert-warning"><%=request.getParameter("info") %>
                        </div>
                        <%}%>

                        <hr/>
                        <div class="row">
                            <div class="col-md-4">
                                <button id="collectButton" onclick="collect(<%=good.getId()%>,<%=isLogin %>)"
                                        type="button"
                                        class="center-block btn btn-default">收藏此物品
                                </button>
                            </div>
                            <div class="col-md-4">
                                <button type="button" id="addCastButton"
                                        class="center-block btn btn-default"
                                        onclick="shoppingCart(<%=isLogin %>,<%=goodsNum %>,<%=good.getId()%>)">加入购物车
                                </button>
                            </div>
                            <div class="col-md-4">
                                <button <%=good.getStates() == 2 ? "" : "disabled=\"disabled\"" %> id="buy"
                                                                                                   type="button"
                                                                                                   class="center-block btn btn-default"
                                                                                                   onclick="toLogin(<%=isLogin %>)">
                                    <%
                                        if (good.getStates() != 2) {
                                            out.print("[不可用]已被购买或未通过审核");
                                        } else {
                                            out.print("立即购买");
                                        }
                                    %>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../site/footer.jsp"/>
</body>
</html>
<%
    userHandle.close();
    goodsHandle.close();
%>