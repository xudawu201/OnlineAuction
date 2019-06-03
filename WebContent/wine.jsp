<%--    
@author rhythm  
@date 2019年5月26日 上午9:51:32
相关说明  
--%>  

<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page 
import="dao.WatchDao"
import="dao.WatchDaoFactory"
import="entity.Watch"
import="java.util.*"
import="java.text.SimpleDateFormat"
import="entity.*"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<User> users = (List<User>)request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在线拍卖系统</title>
<link rel="stylesheet" href="css/HomePage.css" />
<link rel="stylesheet" href="css/SerchBox.css" />
<link rel="stylesheet" href="css/CategoryStyle.css" />

<script type="text/javascript" src="js/jquery-2.1.0.js" ></script>
</head>
<body>
<div class="HomePageTitle">
		<!--导航栏标签-->
		<nav>
			<!--超链接标签-->
			<%
				if (session.getAttribute("user") == null) {
			%>  
<!-- 			<a href="login.jsp">您好,请登录</a> -->
			<a href="<%=path%>/login.jsp"><font color="#A6686A">您好，请登录 </font></a>
<%-- 			<a href="<%=path%>/application/reg.jsp"><font color="red">免费注册</font></a> --%>
			<%
				} else {
			%>  
<%-- 			<a href="<%=path%>/user.jsp">个人中心</a> --%>
			        <a href="<%=path %>/UserServlet?user_id=<%=((User)session.getAttribute("user")).getId() %>"><font color="#A6686A">欢迎您,${user.name} </font></a> 
			<a href="<%=path%>/IndexServlet?login=no"><font color="#A6686A"><font color="#D6686A">注销</font> </font></a>
			<%
				}
			%>

			<!-- 			<a>个人中心</a> -->
<!-- 			<a href="#">个人中心</a> -->
<!-- 			<a href="login.jsp">登录</a> -->
			<a href="index.jsp">拍卖网欢迎您</a>
		</nav>
	</div>
	<!-- 搜索框 -->
	<div class="HomePageTitle2">
	    <div class="serchBox">
		<form action="SerchServlet" name="search" class="search" method="post" ><br>
			<input type="text" id="serchInput" name="serchInput" placeholder="搜索"/>
			<input type="submit" id="serchBtn" value="搜索" onclick=" return SumbitJudge()"/>
		</form>
		</div>
	</div>
	<script type="text/javascript">
//表单未填写完全禁止提交
function SumbitJudge() {
// 	alert("sada");
    var input=document.getElementById("serchInput").value;
	if (!input) {
		alert("请输入搜索内容！");
		return false;
	}
	return true;
}
</script>
	<!-- 导航栏 -->
	<div class="Navigation1">
		<nav>
			<!--超链接标签-->
			<a href="<%=path%>/index.jsp">首页</a>
			<a href="<%=path%>/watch.jsp">腕表</a>
			<a href="<%=path%>/stamp.jsp">钱币邮票</a>
			<a href="<%=path%>/book.jsp">书籍漫画</a>
			<a href="<%=path%>/wine.jsp">葡萄酒及威士忌</a>
		</nav>
	</div>

	<div class="watchBox">
		<div class="h1Box">
			<p class="h1">葡萄酒及威士忌</p>
<!-- 		<nav>
		<a href="#">万国手表</a>
		<a href="#">劳力士腕表</a>
		<a href="#">卡地亚腕表</a>
		<a href="#">女士腕表</a>
		
		<a href="#">欧米茄腕表</a>
		<a href="#">百年灵腕表</a>
		<a href="#">顶级名表</a>
		<a href="#">浪琴腕表</a>
		</nav> -->
		</div>
		<div class="watchBox2" id="watchBox2">
			<p class="h2">浏览所有拍品</p>
<!-- 			<div> -->
<!-- 			<img alt="" src=""> -->
<!-- 			<p> </p> -->
<!-- 			<p class="p2" id=""></p> -->
<!-- 			</div> -->
		</div>
	</div>
	
	<script type="text/javascript" src="js/CreateWine.js" ></script>
</body>
</html>