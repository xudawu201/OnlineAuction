//ajax设置同步刷新改变全局变量
$.ajaxSetup({async:false});
//存Watch信息
var JsonList=[];
var total;
GetJson(JsonList);//调用函数存入数据
function GetJsonData(id,name,type,price,introduce,picture,date,state){
	this.id=id;
	this.type=type;
	this.price=price;
	this.name=name;
	this.introduce=introduce;
	this.picture=picture;
	this.date=date;
	this.state=state;
}
//获取数据库中数据并存入JsonList中
function GetJson(List) {
	
	$.getJSON("CommodityServlet?type=wine", function(result) {
		
		//js需要通过eval()函数  将返回值 转为一个js能够识别的json对象
		var json = eval(result);
		for (var i = 0; i < json.length; i++) {
			var data=new GetJsonData(json[i].id,json[i].name,json[i].type,
					json[i].price,json[i].introduce,
					json[i].picture,json[i].date,json[i].state);
			List.push(data);
			
			JsonList=List;
			total=i;
		}
		total+=1;
	})
};

//获取json中的时间,返回0000/00/00 00:00:00
function timeStampDate(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "/" + month + "/" + date+" "+hour+":"+minute+":"+second;
}

function toDays(ss) {
	//转换为Int
	var s=parseInt(ss % 60); // 秒
	var mi=parseInt((ss - s) / 60 % 60); // 分钟
	var h=parseInt(((ss - s) / 60 - mi) / 60 % 24); // 小时
	var d=parseInt((((ss - s) / 60 - mi) / 60 - h) / 24) // 天
	return "倒计时(UTC+8):"+d+"天"+h+"小时"+mi+"分钟"+s+"秒";
}

//然后写一个定时器
function RunS(id,TotalS) {
	setInterval(function() {
		TotalS--;
		document.getElementById(id).innerHTML=toDays(TotalS);
	}
	, 1000);
}

var AddWatchBox2 = document.getElementById("watchBox2");
//页面加载完成之后自动执行
AddWatchDiv();
function AddWatchDiv() {
	var commodityId=1;
	$.each(JsonList,function(i,element){
		//如果商品已下架则不显示在页面中
		if (this.state==2) {
			return true;
		}
		var newA=document.createElement("a");
		//传递商品id和种类
		commodityId=i+1;
		newA.href="AuctionServlet?type=wine&id="+this.id;
		//创建div
		var newDiv=document.createElement("div");
		//为新添加的元素写style
//		newDiv.style.background="#000";
		//创建Img
		var newImg=document.createElement("img");
		newImg.src=this.picture;
		//创建Introduce
		var newIntroduce=document.createElement("p");
		newIntroduce.innerText=this.introduce;
		//创建CountDown
		var newCountDown=document.createElement("p");
		//创建时间
		var nowTime = new Date();
		//获取时间毫秒差值
		//3天259200000毫秒
		var TotalMS = this.date.time+259200000-nowTime.getTime();
		//设置id
		newCountDown.id=i;
		newCountDown.style.position="absolute";
		newCountDown.style.bottom="2px";
		//添加到父节点之后
		AddWatchBox2.appendChild(newA);
		newA.appendChild(newImg);
		newA.appendChild(newIntroduce);
		newA.appendChild(newCountDown);
		RunS(i,TotalMS/1000);
	})
}

