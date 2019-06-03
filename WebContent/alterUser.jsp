<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>修改密码</title>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/modifyUser.css">
</head>
<body onload="yuanPasswordIsOk('<%=request.getParameter("error")%>')">
<div class="parent">
    <div style="width: 100%;height: 80px;"></div>
    <div class="content">
    <form method="post" action="<%=path%>/AlterUserServlet"  onsubmit="return yanzheng()">
    <div>
        <div style="width: 100%;height: 30px;"></div>
        <div class="div-title"><font size="5">原密码</font></div>
        <div class="div-title">
            <div class="news-title">
                <input type="text" class="form-control" placeholder="请输入原密码" id="yuanpassword" name="yuanpassword">
            </div>
        </div>
    </div>
    <div>
        <div style="width: 100%;height: 30px;"></div>
        <div class="div-title"><font size="5">新密码</font></div>
        <div class="div-title">
            <div class="news-title">
                <input type="text" class="form-control" placeholder="请输入新密码" id="newpassword" name="newpassword">
            </div>
        </div>
    </div>
    <div>
        <div style="width: 100%;height: 30px;"></div>
        <div class="div-title"><font size="5">确认新密码</font></div>
        <div class="div-title">
            <div class="news-title">
                <input type="text" class="form-control" placeholder="请输入新密码" id="password" name="password">
            </div>
        </div>
    </div>
    <input type="hidden" name="user_id" value="<%=request.getParameter("user_id")%>">
    <input type="hidden" name="modify" value="password">
    <div>
        <div style="width: 100%;height: 50px;"></div>
        <div class="div-title">
            <div class="news-title" style="margin-bottom: 20px;">
                <button type="submit" class="btn btn-info">修改</button>
            </div>
        </div>
    </div>
    </form>
    </div>
</div>
<script type="text/javascript" src="js/modifyUser.js"></script>
</body>
</html>