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
<body bgcolor="#efefef" onload="error('<%=request.getParameter("error") %>','<%=((User)session.getAttribute("user"))/* .getLevel() */ %>')">
<div class="top-parent">
    <div class="top-title">
        <div class="top-title-left">
            <a href="<%=path%>/user.jsp">
            <font size="5" color="#5de" class="top-title-text">个人中心</font>
            </a>
        </div>
        <div class="top-title-center" style="display: none" id="adminDiv">
<%--             <a href="<%=path %>/admin/index.jsp"><font size="5" color="#e56" class="top-title-text">管理员后台</font></a>  --%>
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
                <div class="content-top-left-icon">
<%--                     <img src="<%=path+"/" %>${user.icon}" width="100%" height="100%" style="border-radius: 50%;"> --%>
                    <img alt="" src="img/name.jpg" width="100%" height="100%" style="border-radius: 50%;">
                </div>
                <div class="content-top-left-right">
                    <div class="content-top-left-right-name">
                        <font size="5" color="#554">姓名:${user.name }</font>
                    </div>
                    <div class="content-top-left-right-id">
                        <font size="4" color="#554">PID:</font>
                        <font size="4" color="#e33">${user.id }</font>
                    </div>
                </div>
            </div>
        </div>
        <div class="content-center">
            <form action="AddCommodityServlet?userId=${user.id }" method="post" enctype="multipart/form-data">
                      拍品起拍价：<input name="price" id="inputPrice" onkeyup="value=value.replace(/[^(\d)]/g,'');if(value.length>7)value=value.slice(0,7)"/><br/>
			拍品的简介：<input name="introduce" id="inputIntroduce" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" /><br/>
			选择您要上架拍品的类别
			<select name="select">
			      <option value="book">书籍漫画</option>
			      <option value="watch">手表腕表</option>
			      <option value="stamp">邮票钱币</option>
			      <option value="wine">葡萄酒及威士忌</option>
			</select><br/>
                      选择拍品图片：<input type="file" name="uploadFile" id="File" /> <br/><br/>
            <input type="submit" value="确认上架" onclick=" return SumbitJudge()"/>
            </form>
        </div>
    </div>
</div>
<%}else{%>
    <div>您的登录已过时，请重新登录</div>
    <%}%>
   	<br>
<script type="text/javascript">
//表单未填写完全禁止提交
function SumbitJudge() {
// 	alert("sada");
    var inputPrice=document.getElementById("inputPrice").value;
    var inputIntroduce=document.getElementById("inputIntroduce").value;
	var fileVal=document.getElementById("File").value;
// 	alert(fileVal);
	if (!inputPrice) {
		alert("请输入起拍价！");
		return false;
	}
	else if (!inputIntroduce) {
		alert("请输入拍品简介！");
		return false;
	}
	else if(!fileVal){
	    alert("请上传图片！");
	    return false;
	}
	return true;
}
</script>   	
<script type="text/javascript" src="js/user.js"></script>
</body>
</html>
