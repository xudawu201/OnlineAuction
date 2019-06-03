
//ajax设置同步刷新改变全局变量
$.ajaxSetup({async:false});
//存Watch信息
var JsonList=[];
var JsonListStamp=[];
GetJson(JsonList);//调用函数存入数据
GetJsonStamp(JsonListStamp);//调用函数存入数据
function GetJsonData(id,type,price,introduce,picture,date,state){
	this.id=id;
	this.type=type;
	this.price=price;
	this.introduce=introduce;
	this.picture=picture;
	this.date=date;
	this.state=state;
}
//获取数据库中数据并存入WatchJsonList中
function GetJson(List) {
	
	$.getJSON("CommodityServlet?type=book", function(result) {
		
		//js需要通过eval()函数  将返回值 转为一个js能够识别的json对象
		var json = eval(result);
		for (var i = 0; i < json.length; i++) {
			var data=new GetJsonData(json[i].id,json[i].type,
					json[i].price,json[i].introduce,
					json[i].picture,json[i].date,json[i].state);
			List.push(data);
			JsonList=List;
		}
	})
};

function GetJsonStamp(List) {
	
	$.getJSON("CommodityServlet?type=stamp", function(result) {
		
		//js需要通过eval()函数  将返回值 转为一个js能够识别的json对象
		var json = eval(result);
		for (var i = 0; i < json.length; i++) {
			var data=new GetJsonData(json[i].id,json[i].type,
					json[i].price,json[i].introduce,
					json[i].picture,json[i].date,json[i].state);
			List.push(data);
			GetJsonStamp=List;
		}
	})
};

//获取json中的时间,返回0000/00/00 00:00:00
function timeStampDate(id,time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
//    return year + "/" + month + "/" + date+" "+hour+":"+minute+":"+second;
    document.getElementById(id).innerHTML="截至日期(UTC+8):"+year + "年" + month + "月" + date+"日"+" "+hour+":"+minute+":"+second;
}

//function toDays(ss) {
//	//转换为Int
////	parseInt()
////	alert("eee");
////	alert("vvvvv");
//	var s=parseInt(ss % 60); // 秒
//	var mi=parseInt((ss - s) / 60 % 60); // 分钟
//	var h=parseInt(((ss - s) / 60 - mi) / 60 % 24); // 小时
//	var d=parseInt((((ss - s) / 60 - mi) / 60 - h) / 24) // 天
//	return "截至日期(UTC+8):"+d+"天"+h+"小时"+mi+"分钟"+s+"秒";
//}
//
////然后写一个定时器
//function RunS(id,TotalS) {
//	setInterval(function() {
//		TotalS--;
//		document.getElementById(id).innerHTML=toDays(TotalS);
//	}
//	, 1000);
//}
//alert(JsonList[0].picture);
//alert(JsonList[0].picture);
var AddBookBox = document.getElementById("bookBox");
var AddStampBox = document.getElementById("stampBox");
AddBookDiv();
AddStampDiv();
function AddBookDiv() {
	//创建div元素，布局会默认使用css中定义的布局属性
//	for (var i = 0; i < 4; i++) {
//	遍历List,$.each(list,function(i,element){var cc=this.name})
	var count=1;
	var commodityId=1;
	$.each(JsonList,function(i,element){
//		jQuery中each类似于javascript的for循环 
//		但不同于for循环的是在each里面不能使用break结束循环，也不能使用continue来结束本次循环，想要实现类似的功能就只能用return,
//		break           用return false
//		continue      用return ture
		//只显示4张图
//		alert(count);
		if (this.state==2) {
			return true;
		}
		if (count==5) {
			return false;
		}
		count++;
//		<a href="<%=path %>/application/admin/index.jsp">
		var newA=document.createElement("a");
		//传递商品id和种类
		commodityId=i+1;
		newA.href="AuctionServlet?type=book&id="+this.id;
//		newA.innerHTML="测试";
		//创建div
		var newDiv=document.createElement("div");
		//为新添加的元素写style
//		newDiv.style.background="red";
		
		//创建Img
		var newImg=document.createElement("img");
		newImg.src=this.picture;
		//创建Introduce
		var newIntroduce=document.createElement("p");
		newIntroduce.innerText=this.introduce;
		//价格
//		var newPrice=document.createElement("p");
//		newPrice.innerText="$:"+this.price;
		//创建CountDown
		var newCountTime=document.createElement("p");
		//创建时间
		var nowTime = new Date();
		//获取时间毫秒差值
		//3天259200000毫秒
		var TotalMS = this.date.time+259200000;
		//设置id
		newCountTime.id=i;
		newCountTime.style.position="absolute";
		newCountTime.style.bottom="2px";
		//添加到父节点之后
		AddBookBox.appendChild(newA);
//		newA.appendChild(newDiv);
		newA.appendChild(newImg);
		newA.appendChild(newIntroduce);
//		newA.appendChild(newPrice);
		newA.appendChild(newCountTime);
		
		timeStampDate(i,TotalMS);
	})
//	}
}
function AddStampDiv() {
	//创建div元素，布局会默认使用css中定义的布局属性
//	for (var i = 0; i < 4; i++) {
//	遍历List,$.each(list,function(i,element){var cc=this.name})
	var count=1;
	var commodityId=1;
	$.each(JsonListStamp,function(i,element){
//		jQuery中each类似于javascript的for循环 
//		但不同于for循环的是在each里面不能使用break结束循环，也不能使用continue来结束本次循环，想要实现类似的功能就只能用return,
//		break           用return false
//		continue      用return ture
		//只显示4张图
//		alert(count);
		if (this.state==2) {
			return true;
		}
		if (count==5) {
			return false;
		}
//		alert(i);
		count++;
//		<a href="<%=path %>/application/admin/index.jsp">
		var newA=document.createElement("a");
		//传递商品id和种类
		commodityId=i+1;
		newA.href="AuctionServlet?type=stamp&id="+this.id;
//		newA.innerHTML="测试";
		//创建div
		var newDiv=document.createElement("div");
		//为新添加的元素写style
//		newDiv.style.background="red";
		
		//创建Img
		var newImg=document.createElement("img");
		newImg.src=this.picture;
		//创建Introduce
		var newIntroduce=document.createElement("p");
		newIntroduce.innerText=this.introduce;
		//价格
//		var newPrice=document.createElement("p");
//		newPrice.innerText="$:"+this.price;
		//创建CountDown
		var newCountTime=document.createElement("p");
		//3天259200000毫秒
		var TotalMS = this.date.time+259200000;
//		alert(TotalMS);
		//设置id
		newCountTime.id=i;
		newCountTime.style.position="absolute";
		newCountTime.style.bottom="2px";
		//添加到父节点之后
		AddStampBox.appendChild(newA);
		newA.appendChild(newImg);
		newA.appendChild(newIntroduce);
		newA.appendChild(newCountTime);
//		alert(i);
		timeStampDate(i,TotalMS);
	})
//	}
}
