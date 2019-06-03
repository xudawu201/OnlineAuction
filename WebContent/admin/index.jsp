<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/admin/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>"> 
    
    <title>后台管理</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/admin.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
    
  </head>
  
  <body>
    <div class="top-parent">
    <div class="top-title">
        <div class="top-title-left">
            <font size="6" color="#68228B" class="top-title-text">拍卖系统管理</font>
        </div>
    </div>
	</div>
	<div style="width: 100%;height: 30px;"></div>
	<div class="content">
	    <div class="content-1">
	        <a href="<%=path %>/IndexServlet">
	        <div class="content-1-1 content-raduis">
	            <font class="content-text">返回首页</font>
	        </div>
	        </a>
	    </div>
	    <div class="content-2">
	    <a href="<%=path %>/CommodityManageServlet?level=guanliyuan&page=4&start=0">
	        <div class="content-2-1 content-raduis">
	            <font class="content-text">商品管理</font>
	        </div>
	        </a>
	        <a href="<%=path %>/UserManageServlet?start=0&end=6">
	        <div class="content-2-2 content-raduis">
	            <font class="content-text">用户管理</font>
	        </div>
	        </a>
	    </div>
	</div>
 </body>
</html>
