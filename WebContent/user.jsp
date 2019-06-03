<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/user.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
   
 </head>
<body bgcolor="#efefef" onload="error('<%=request.getParameter("error") %>','<%=((User)session.getAttribute("user")).getAdmin()%>')">
<div class="top-parent">
    <div class="top-title">
        <div class="top-title-left">
            <a href="<%=path%>/user.jsp">
               <font size="5" color="#5de" class="top-title-text">个人中心</font>
            </a>
        </div>
        <div class="top-title-center" style="display: none" id="adminDiv">
             <a href="<%=path %>/admin/index.jsp"><font size="5" color="#e56" class="top-title-text">管理员后台</font></a> 
        </div>
        <div class="top-title-right">
            <a href="<%=path%>"><font size="5" color="#e56" class="top-title-text">返回首页</font></a>
        </div>
    </div>
</div>
<% if(session.getAttribute("user") != null){ %>
<div class="content-div">
    <div class="content">
        <div class="content-top">
            <div class="content-top-left">
                <div class="content-top-left-icon" onclick="openIcon()">
<%--                     <img src="<%=path+"/" %>${user.icon}" width="100%" height="100%" style="border-radius: 50%;"> --%>
                    <img alt="" src="img/name.jpg" width="100%" height="100%" style="border-radius: 50%;">
                </div>
                <div class="content-top-left-right">
                    <div class="content-top-left-right-name">
                        <font size="5" color="#554">姓名:${user.name }</font>
                        <img src="img/modifyUserName.jpg" width="15px" height="15px" onclick="diplayModifyName()">
                    </div>
                    <div class="content-top-left-right-id">
                        <font size="4" color="#554">PID:</font>
                        <font size="4" color="#e33">${user.id }</font>
                    </div>
                </div>
            </div>
        </div>
        <div class="content-center">
            <div style="width: 100%;height: 40px;">
                <button class="button" id="button1" color="red" onclick="display(1)">个人资料</button>
                <button class="button" id="button2" onclick="display(2)">拍卖信息</button>
            </div>
            <div class="content-content1" id="content1" style="display: '';">
<%--                 <div>
                    <font size="3" color="#334" style="">用户ID:	</font>
                    <font size="3" color="red" style="margin-left: 0px;">${user.id }</font>
                </div> --%>
                <div>
                    <font size="3" color="#334" style="">用户名:</font>
                    <font size="3" color="red" style="margin-left: 0px;">${user.name }</font>
                </div>
                <div>
                    <font size="3" color="#334" style="">手机号:</font>
                    <font size="3" color="red" style="margin-left: 0px;">${user.phone }</font>
                </div>
                <div>
                    <font size="3" color="#334" style="">地址:</font>
                    <font size="3" color="red" style="margin-left: 16px;">${user.address}</font>
                </div>
                <div>
                    <a href="<%=path %>/alterUser.jsp?user_id=${user.id }"><font size="4" color=rgb(192,2,103) style="">修改密码</font></a>
                    <a href="<%=path %>/addCommodity.jsp?user_id=${user.id }" style="margin-left: 20px;"><font size="4" color=rgb(192,2,103) style="">上架商品</font></a>
                </div>
            </div>
            <div class="content-content2" id="content2" style="display: none;">
                <div>
                    <font size="3" color="#334" style="">出售数量</font>
                    <font size="3" color="#5e1" style="margin-left: 20px;">${user.auction_number }</font>
                    <a href="<%=path%>/MyAuctionServlet?user_id=${user.id }"><font size="3" color="blue" style="margin-left: 30px;">详情</font></a>
                </div>
                <div>
                    <font size="3" color="#334" style="">购买数量</font>
                    <font size="3" color="#5e1" style="margin-left: 20px;">${user.bought_number }</font>
                    <a href="<%=path%>/MyBoughtServlet?user_id=${user.id }"><font size="3" color="blue" style="margin-left: 30px;">详情</font></a>
                </div>
            </div>
            
        </div>
    </div>
</div>
<%}else{%>
    <div>您的登录已过时，请重新登录</div>
    <%}%>
   	<br>
<!--修改头像弹出的窗口-->
<div id="iconDiv" class="shadow" style="display: none;">
        <div style="float:left;width:100%;height:10%;">
            <div style="float:left;width:90%;height:100%;text-align:center;">
                <font size="3" color="#4d2">上传图片修改头像</font>
            </div>
            <div style="float:right;width:10%;height:100%;text-align: center;" onclick="closeIcon()">
                <font size="6" style="">×</font>
            </div>
        </div>
        <div style="float:left;width:50%;height:55%;margin-left:25%;margin-top:10%;">
            <form action="<%=path%>/UserServlet" method="post" enctype="multipart/form-data" onsubmit="return yanzheng()">
                <div class="inputbg">
                    <input type="file" unselectable="on" name="user_icon" id="user_icon">
                    <input type="hidden" value="${user.id }" name="user_id">
                </div>
                <button type="submit" class="btn btn-info" style="margin-left: 40%;margin-top: 20px;">修改</button>
            </form>
        </div>
        <div style="float:left;width:100%;text-align: center;">
            <font size="6" color="red" id="bid_price"></font>
        </div>
</div>
<!--修改用户名弹出的窗口-->
<div id="userDiv" class="shadow" style="display: none;">
        <div style="float:left;width:100%;height:10%;">
            <div style="float:left;width:90%;height:100%;text-align:center;">
                <font size="3" color="#4d2">修改用户名</font>
            </div>
            <div style="float:right;width:10%;height:100%;text-align: center;" onclick="closeUser()">
                <font size="6" style="">×</font>
            </div>
        </div>
        
        <div class="name-content">
        	<form action="<%=path %>/AlterUserServlet" method="post" onsubmit="return modifyUser()">
        	<input type="text" class="form-control" placeholder="新用户名" onchange="registerUserName()" name="username" id="username">
        	<input type="hidden" name="user_id" value="${user.id }">
        	<input type="hidden" name="modify" value="username">
        	<div id="msg" style="display:none;"><font color="red" size="1">用户名已存在</font></div>
	        <input type="submit" class="btn btn-info" style="width: 40%;margin-top: 100px;" value="修改">
	        </form>
        </div>
    
</div>
<script type="text/javascript" src="js/user.js"></script>
</body>
</html>
