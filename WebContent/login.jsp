<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String error = request.getParameter("login");
if(error == null){
	error = "";
}
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/iconfont.css">
    <link rel="stylesheet" href="css/reg.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
    <!-- 判断登录是否成功的js -->
    <script type="text/javascript">
    	var error = "<%=error%>";
    	function errorLogin(){

	    	if(error == "error"){
	    		alert("用户名或密码错误,请重新登录!");
    		}
    	
    	}
    </script>
</head>
<body onload="errorLogin()">
<div id="ajax-hook"></div>
<div class="wrap" style="background: url(img/background.jpg) no-repeat center;">
    <div class="wpn">
        <div class="form-data pos">
            <a href="index.jsp"><img src="img/logo.png" class="head-logo"></a>
            <div class="change-login">
                <p class="account_number on">账号登录</p>
<!--                 <p class="message">短信登录</p> -->
            </div>
            <form action="LoginServlet" method="post">
            <div class="form1">
                <p class="p-input pos">
                    <label for="num">用户名</label>
                    <input type="text" id="num" name="username">
                    <span class="tel-warn num-err hide"><em>账号或密码错误，请重新输入</em><i class="icon-warn"></i></span>
                </p>
                <p class="p-input pos">
                    <label for="pass">请输入密码</label>
                    <input type="password" style="display:none"/>
                    <input type="password" id="pass" autocomplete="new-password" name="password"/>
                    <span class="tel-warn pass-err hide"><em>账号或密码错误，请重新输入</em><i class="icon-warn"></i></span>
                </p>
<!--                 <p class="p-input pos code hide"> -->
<!--                     <label for="veri">请输入验证码</label> -->
<!--                     <input type="text" id="veri"> -->
<!--                     <img src=""> -->
<!--                     <span class="tel-warn img-err hide"><em>账号或密码错误，请重新输入</em><i class="icon-warn"></i></span> -->
<!--                     <a href="javascript:;">换一换</a> -->
<!--                 </p> -->
            </div>
<!--             <div class="form2 hide"> -->
<!--                 <p class="p-input pos"> -->
<!--                     <label for="num2">手机号</label> -->
<!--                     <input type="number" id="num2"> -->
<!--                     <span class="tel-warn num2-err hide"><em>账号或密码错误</em><i class="icon-warn"></i></span> -->
<!--                 </p> -->
<!--                 <p class="p-input pos"> -->
<!--                     <label for="veri-code">输入验证码</label> -->
<!--                     <input type="number" id="veri-code"> -->
<!--                     <a href="javascript:;" class="send">发送验证码</a> -->
<!--                     <span class="time hide"><em>120</em>s</span> -->
<!--                     <span class="tel-warn error hide">验证码错误</span> -->
<!--                 </p> -->
<!--             </div> -->
            <div class="r-forget cl">
                <a href="register.jsp" class="z">账号注册</a>
<!--                 <a href="./getpass.jsp" class="y">忘记密码</a> -->
            </div>
            <button class="lang-btn off log-btn">登录</button>
            <div class="third-party">
                <a href="#" class="log-qq icon-qq-round"></a>
                <a href="#" class="log-qq icon-weixin"></a>
                <a href="#" class="log-qq icon-sina1"></a>
            </div>
            </form>
            <p class="right">在线拍卖系统 by © 2019</p>
        </div>
    </div>
</div>
<script src="js/jquery.js"></script>
<script src="js/agree.js"></script>
<script src="js/login.js"></script>
</body>
</html>