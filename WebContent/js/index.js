function myAuction(session_user){
    //验证用户是否登录，提取session是否为null来识别。
    //判断我的拍卖连接是否可以点击
    var user = session_user;
    if(user != "null"){
    	alert("请先登录！");
		return false;
    }
	return true;
}