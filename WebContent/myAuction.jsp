<%@page import="entity.Commodity"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page 
import="entity.*"
import="java.text.SimpleDateFormat"
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//获取物品列表
List<Commodity> myGoodss = null;
myGoodss = (List<Commodity>)request.getAttribute("myGoodss");
User user = (User)session.getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的拍卖</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <font size="6" color="green">你的竞拍品：</font><br>
    <div>
    
    	<table class="table table-hover">
    	<%for(int i=0;i<myGoodss.size();i++){%>
        	<tr><td><a href="<%=path%>/AuctionServlet?id=<%=myGoodss.get(i).getCommodityId() %>&type=<%=myGoodss.get(i).getType()%>">
        	<div style="height:300px;">
               <div style="float:left;margin-left:10px;width:65%;height:150px;">
               
                  <div style="width:100%;height:20%;">
                     <div style="width:30px;height:100%;float:left;">
<%--                      <img src="<%=path+"/"+user.getUser_icon().toString()%>" width=100% height=100% style="border-radius:200px;"> --%>
                     </div>
                     <div style="float:left;margin-left:5px;width:100px">
                                      类别:<%=myGoodss.get(i).getType() %>
                     </div>
                     <div style="float:left;margin-top:30px;margin-left:-100px;">
                     <% long Ms=myGoodss.get(i).getDate().getTime();
                        long TotalMS = myGoodss.get(i).getDate().getTime()+259200000;
                        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	                  %>
                     <font color="#f4e465" size="5px">起拍时间:<%=myFmt.format(Ms)%></font>
                     <font color="#000" size="5px">截止时间:<%=myFmt.format(TotalMS)%></font>
                     </div>
                  </div>
                  <div style="width:100%;">
                  <div style="height:7px;"></div>
<%--                      <font size="6"><%=myGoodss.get(i).getType() %></font> --%>
                  </div>
                  <div style="width:100%;margin-top: 50px;margin-left: 30px">
                  <font size="4" color="#b3acb3"><%=myGoodss.get(i).getIntroduce() %></font>
                  </div>
               </div>
               <div style="height:100px; width:28%;float:right;margin-right:30px;margin-top:5px;">
               <img src="<%=path+"/"+myGoodss.get(i).getPicture() %>" width=70% height=300%></div>
               </div></a></td></tr>
            
            <%} %>
		</table>
    	
    </div>
  </body>
</html>
