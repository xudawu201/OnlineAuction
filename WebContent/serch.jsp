<%--    
@author rhythm  
@date 2019年5月9日 下午5:40:37
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
//获取物品列表
List<Commodity> commodity = null;
commodity = (List<Commodity>)request.getAttribute("commodity");
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
			<p class="h1">搜索结果</p>
		</div>
		<div class="watchBox2" id="watchBox2">
			<p class="h2">浏览所有拍品</p>
			<%
			if(!commodity.isEmpty()){
			for(int i=0;i<commodity.size();i++){
// 				若已拍出则不显示
				if(commodity.get(i).getState()==2)
				{
					continue;
				} 
			%>
        	<a href="<%=path%>/AuctionServlet?id=<%=commodity.get(i).getCommodityId() %>&type=<%=commodity.get(i).getType()%>" style="width: 20%;height: 35%" >
        	<div>
               <img src="<%=path+"/"+commodity.get(i).getPicture() %>">
                     <% long Ms=commodity.get(i).getDate().getTime();
                        long TotalMS = commodity.get(i).getDate().getTime()+259200000;
                        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	                  %>
               <p><%=commodity.get(i).getIntroduce() %></p>
               <p class="p2" id="" style="position: absolute;border: 2px;">截止时间:<%=myFmt.format(TotalMS)%></p>
          </div>
               </a>
            <%} } %>
       </div>
	</div>
</body>
</html>