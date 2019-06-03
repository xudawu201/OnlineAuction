var timer=null;/*计时器*/

var NowImageNumber=0;/*当前显示图片*/
var NextImageNumber=0;/*即将显示图片*/


//调用开始滑动函数
StartPlay();


//开始滑动函数
function StartPlay()
{
	timer = setInterval(function(){
		NextImageNumber++;
		if(NextImageNumber>3)/*回到第一张*/
		{
			NextImageNumber=0;
		}
		//滑动动画
		ScrollPlay();
		NowImageNumber=NextImageNumber;
		
	},2000);
}


//滑动函数
function ScrollPlay()
{
	//填充小方格
	//addClass("FillSquare")添加属性，siblings()过滤，remove("FillSquare")清除属性
	$("#List li").eq(NextImageNumber).addClass("FillSquare").siblings().removeClass("FillSquare");
	
	
	//向左滑动,当前显示小于即将显示
	if(NowImageNumber<NextImageNumber)
	{
	//	eq(),选择器，选取带有指定index值的元素
	//stop(ture,true)是否执行当前动画效果,是否将后续队列动画,stop()需要放在动画函数之后，下面这种方式不会产生影响
		$('#ImgBox img').eq(NowImageNumber).stop(true,true).animate({"left":"-1000px"});
	//当前的图片左移出，即将显示图片从右移入
	    $("#ImgBox img").eq(NextImageNumber).css("left","900px").stop(true,true).animate({"left":"0"});
	}
	//向右，当前显示大于即将显示
	else if(NowImageNumber>NextImageNumber)
	{
	    //stop(ture,true)关掉所有附带的动画效果,是否将当前动画执行到最后
		$('#ImgBox img').eq(NowImageNumber).stop(true,true).animate({"left":"900px"});
	   //当前的图片右移出，即将显示图片从左移入
	    $("#ImgBox img").eq(NextImageNumber).css("left","-1000px").stop(true,true).animate({"left":"0"});
	}
}


//鼠标经过小方格
//当鼠标指针位于元素上方，会发生mouseover()事件
$("#List li").mouseover(function()
{
	//清除计时器
	clearInterval(timer);
	
	NextImageNumber=$(this).index();/*获取下标*/
	ScrollPlay();/*滑动函数*/
	NowImageNumber=NextImageNumber;
}).mouseout(function()//鼠标离开
{
	StartPlay();
});
