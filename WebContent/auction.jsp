<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(session.getAttribute("user")==null){
	response.sendRedirect("login.jsp");
}else{
%>

<html>
<head>
<title>拍卖品详情</title>
</head>
<body bgcolor="#f9f9f9">
<div><a href="<%=path%>"><font size="+2">返回</a></font> </div><br>
<br><br>
<div style="width: 60%; height: 5%; text-align: center;">         
<font size="5">距离拍卖结束时间还有: </font><font size="6" color="red" id="time"></font>
</div><br><br>
<div style="margin:0 auto; width: 60%;height: 55%; background-color: rgb(255,255,255);">
<div style="float: left; width:25%; height:100%; margin: 50 60;">
<style type="text/css">
div img{
				cursor: pointer;
				transition: all 0.1s;
			}
			div img:hover{
				transform: scale(1.2);
			}
			</style>
<img src="<%=path%>/${commodity.picture}" style="width: 200px;height: 200px;"> <br><br><br>
<font size="+1">物品类型: ${commodity.type}</font><br><br><br>
<font size="+1" color="#000000">拍卖人:${user.name}</font><br><br>
</div>
<div style="float: left; width:40%; height:80%; margin: 50 60;">
<font size="" color="#000000">物品介绍:   ${commodity.introduce}</font><br><br><br>
起拍日期:   ${commodity.date}<br><br>
起拍价格:   $:  ${commodity.price}<br><br><br>
当前最高出价: $:  ${commodity.maxPrice}<br><br><br>
当前最高出价者id:   ${commodity.winnerId}<br><br>
</div>
</div>
<div style="margin:0 auto; width: 60%;height: 20%; background-color: #FdFdFd;">
<div style="margin:0 auto; float:left; margin-right: 20%;">
 <form action="" method="post" onsubmit="return auction()">
 	<input type="hidden" name="user_id" value="${user.id}">
	<input type="hidden" name="goods_id" value="${commodity.id}">
	<input type="hidden" name="type" value="${commodity.type}">
	
	<%if(session.getAttribute("user") == null){ %>
		<input type="hidden" name="winner_id" value="error" id="winner_id" >
	<%}else{ %>
		<input type="hidden" name="winner_id" value="<%=((User)session.getAttribute("user")).getId()%>" id="winner_id">
	<%} %>
输入竞拍价格：<input type="text" name="max_price" id="price" style="border-bottom:1 solid black;background:;" onkeyup="value=value.replace(/[^(\d)]/g,'');if(value.length>7)value=value.slice(0,7)"> <input type="submit" value="参与竞拍" id="submit">

</form>
</div>
<div style="text-align:center; float:left; margin-right: 20%;">
</div>
</div>
<script type="text/javascript">
function auction() {
	
	var type = "<%=request.getAttribute("type")%>";
	var maxPrice ="<%=request.getAttribute("maxPrice")%>";
// 	alert("type:"+type);
	var price = "<%=request.getAttribute("price")%>";
//  	alert("price:"+price);
//  	alert("maxPrice:"+maxPrice);
	var session = document.getElementById("winner_id").value;
// 	var max_price = parseFloat(document.getElementById("price").value);
	var max_price1 = document.getElementById("price").value;
	if (max_price1=="") {
		alert("竞拍价格不能为空!");
	    return false;
	}
	var max_price=parseFloat(document.getElementById("price").value);
// 	alert(max_price);
//     alert("max_price:"+max_price);
// 	alert(max_price+"<="+maxPrice+" is "+(max_price <= maxPrice));
	var ss=max_price <= maxPrice;
	var cs=max_price <= price;
//  	alert(max_price+ "<="+ price+" is "+cs);
//  	if (max_price=="") {
// 		alert("空值");
// 		return false;
// 	}
//  alert("!max_price:"+dd);
	//判断session里面是否有user，就是用户是否登录，不登陆不能参与竞拍
	if(session == "error") {
		alert("请先登录！");
		return false;
	} 
	else if(type=="book") {
		if(ss){
			alert("竞拍价格不能小于等于原价格!");
			return false;
		}
		if(cs) {
			alert("竞拍价格不能小于等于起拍价格!")
			return false;
		}
// 		alert("book");
		return true;
	} 
	else if(type == "watch") {
		if(ss) {
			alert("竞拍价格不能小于等于原价格!");
			return false;
		}
		if(cs) {
			alert("竞拍价格不能小于等于起拍价格!")
			return false;
		}
// 		alert("watch");
		return true;
	}
	else if(type == "stamp") {
		if(ss) {
			alert("竞拍价格不能小于等于原价格!");
			return false;
		}
		if(cs) {
			alert("竞拍价格不能小于等于起拍价格!")
			return false;
		}
// 		alert("stamp");
		return true;
	}
	else if(type == "wine") {
		if(ss) {
			alert("竞拍价格不能小于等于原价格!");
			return false;
		}
		if(cs) {
			alert("竞拍价格不能小于等于起拍价格!")
			return false;
		}
// 		alert("stamp");
		return true;
	}
// 	alert("结尾");
	return false;
}	
	window.onload=timer;
	function timer(){
		var date = document.getElementById("time");
		var submit = document.getElementById("submit");
		var button = document.getElementById("endprice");
		var second = "<%=request.getAttribute("time")%>";
		var time=second;
		if(time<=0){
			submit.value = "已结束拍卖";
			submit.disabled=true;
			button.disabled=true;
			date.innerHTML = "该物品已拍出";
		}else{
// 			var s=parseInt(ss % 60); // 秒
// 			var mi=parseInt((ss - s) / 60 % 60); // 分钟
// 			var h=parseInt(((ss - s) / 60 - mi) / 60 % 24); // 小时
// 			var d=parseInt((((ss - s) / 60 - mi) / 60 - h) / 24) // 天
//                 alert(time);
                var ss=time/1000;
				var seconds = parseInt(ss % 60);
				var minutes = parseInt((ss - seconds) / 60 % 60);
				var hours = parseInt(((ss-seconds)/60-minutes)/60%24);
				var days = parseInt((((ss-seconds)/60-minutes)/60-hours) / 24);
				var interval = setInterval(function(){
					time = days+"天"+hours+"时"+minutes +"分"+seconds+"秒";
					date.innerHTML = time;
					seconds--;
					if(seconds <=0){
						if(minutes<=0){
							if(hours<=0){
								if(days<=0){
									//结束
									submit.disabled=true;
									button.disabled=true;
									days=0;hours=0;minute=0;seconds=0;
									alert("拍卖已结束");
									submit.value = "已结束拍卖";
									clearInterval(interval);
									bid();
								}
								days--;
								hours=24;
							}
							hours--;
							minutes=60;
						}
						minutes--;
						seconds=59;
					}
					
				},1000);
			}
		/* function checkTime(i)
        {
            if (i < 10) {
                i = "0" + i;
            }
            return i;
        } */
        //结束竞拍的网络请求
          function bid(){
//         	newA.href="AuctionServlet?type=book&id="+i;
        	var type = "<%=request.getAttribute("type")%>";
         	//ajax进行post请求
         	var url = "AddOrderServlet";
         	var request = new XMLHttpRequest();
         	request.open("POST", url, true);
         	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//          	alert(type);
         	if (type=="book") {
         		request.send("type=book&goods_id=${commodity.id}&user_id=${commodity.userId}&winner_id=${commodity.winnerId}&bid_price=${commodity.maxPrice}");
			}
         	else if (type=="watch") {
         		request.send("type=book&goods_id=${commodity.id}&user_id=${commodity.userId}&winner_id=${commodity.winnerId}&bid_price=${commodity.maxPrice}");
			}
         	else if (type=="stamp") {
         		request.send("type=book&goods_id=${commodity.id}&user_id=${commodity.userId}&winner_id=${commodity.winnerId}&bid_price=${commodity.maxPrice}");
			}
         	else if (type=="wine") {
         		request.send("type=book&goods_id=${commodity.id}&user_id=${commodity.userId}&winner_id=${commodity.winnerId}&bid_price=${commodity.maxPrice}");
			} 
         } 
	}
</script>
<%} %>
</body>
</html>