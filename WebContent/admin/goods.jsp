<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.*"
import="java.text.SimpleDateFormat"
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/admin/";
//接收session数据
User user = (User)session.getAttribute("user");
//接收Servlet传来的数据
List<Commodity> goodss = new ArrayList<Commodity>();
goodss = (List)request.getAttribute("goodss");
List<User> users = new ArrayList<User>();
users = (List)request.getAttribute("users");
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>拍卖品管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/goods.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

  </head>
  
  <body>
	<!--头部-->
    <div class="top-parent">
        <div class="top-title">
            <div class="top-title-left">
                <a href="<%=path %>/admin/index.jsp" style="text-decoration:none;">
                <font size="6" color="#191970" class="top-title-text">商品管理</font>
                </a>
            </div>
        </div>
    </div>
    <!--主体-->
    <div>
        <div class="content-div">
            <div class="content">
                <!-- 列表 -->
                <div class="content-table">
                    <table class="table table-hover">
                    <%
                    int i;
                    int maxPage=users.size();
                    int start=Integer.parseInt(request.getParameter("start"));
                    int end=Integer.parseInt(request.getParameter("page"));
                    //超出最大
                    if(end>=maxPage)
                    {
                    	end=maxPage;
                    }
//                     out.print(maxPage);
                    %>
                    <%for(i=start;i<end;i++){%>
                        <tr>
                            <div class="table-div">
                                <div class="table-div-left">
                                    <div class="table-div-left-left">
                                        <div class="div1">
                                  <%--           <div class="div1-left">
                                                <img src="<%=path+"/"+users.get(i).getUser_icon().toString()%>" width="25px" height="25px" style="border-radius: 50%;">
                                            </div> --%>
                                            <div class="div1-right">
                                                <font size="3" style="line-height: 25px;">发布者:<%=users.get(i).getName().toString()%></font>
                                            </div>
                                        </div>
                                        <div class="div2">
                                        
                                        <% long Ms=goodss.get(i).getDate().getTime();
                                           long TotalMS = goodss.get(i).getDate().getTime()+259200000;
                                           SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	                                     %>
                                            <font size="2" color="#61de51" style="line-height: 20px;">
                                                                                   起拍时间:<%=myFmt.format(Ms)%>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                  截止时间:<%=myFmt.format(TotalMS)%></font>
                                        </div>
                                        <div class="div3">
                                            <font size="5" color="#ee1226" style="line-height: 35px;">类型:<%=goodss.get(i).getType() %></font>
                                        </div>
                                        <div class="div4">
                                            <font size="2" color="#3d35ed" style="line-height: 20px;"><%=goodss.get(i).getIntroduce() %></font>
                                        </div>
                                    </div>
                                    <div class="table-div-left-right">
                                        <a href="<%=path%>/AuctionServlet?id=<%=goodss.get(i).getCommodityId()%>&type=<%=goodss.get(i).getType()%>">
                                        <img src="<%=path %>/<%=goodss.get(i).getPicture()%>" width="90px" height="80px">
                                        </a>
                                    </div>
                                </div>
                                <div class="table-div-right">
                                    <div class="table-div-right-left">
                                    	<% if(goodss.get(i).getState()==1){%>
                                        <font size="4" style="line-height: 100px;" color="blue">未拍出</font>
                                        <%} %>
                                        <% if(goodss.get(i).getState()==2){%>
                                        <font size="4" style="line-height: 100px;" color="#111">已拍出</font>
                                        <%} %>
                                    </div>
                                    <div class="table-div-right-right">
                                        <a href="<%=path%>/DropCommodityServlet?commodityId=<%=goodss.get(i).getCommodityId()%>&type=<%=goodss.get(i).getType()%>&userId=<%=goodss.get(i).getUserId()%>&winnerId=<%=goodss.get(i).getWinnerId()%>">
                                        <font size="4" style="line-height: 100px;">删除</font>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </tr>
                        <!--分割线-->
                        <div style="width: 100%;height: 1px;background-color: #bbb;float: left;"></div>
                        <%} %>
                    </table>
                </div>
                <!--主体下面的分页栏-->
                <div class="bottom-div">
                    <div style="float: right;margin-right: 2%;">
                    <nav aria-label="Page navigation">
                      <ul class="pagination">
                        <li>
                        <%
                        int preEnd=i-4;
                        int preStart=preEnd-4;
                        if(preStart<=0)
                        {
                        	preStart=0;
                        }
                        preEnd=preStart+4;
                        if(preEnd>=maxPage)
                        {
                        	preEnd=maxPage;
                        }
//                         int preEnd=preStart+4;
                        %>
                          <a href="<%=path %>/CommodityManageServlet?level=guanliyuan&page=<%=preEnd %>&start=<%=preStart %>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                          </a>
                        </li>
                        <li><a href="<%=path %>/CommodityManageServlet?level=guanliyuan&page=4&start=0">1</a></li>
                        <li><a href="<%=path %>/CommodityManageServlet?level=guanliyuan&page=8&start=4">2</a></li>
                        <li><a href="<%=path %>/CommodityManageServlet?level=guanliyuan&page=12&start=8">3</a></li>
                        <li>
                        <%
                        int nextEnd=i+4;
                        if(nextEnd>=maxPage)
                        {
                        	nextEnd=maxPage;
                        }
                        int nextStart=nextEnd-4;
                        if(nextStart<=0)
                        {
                        	nextStart=0;
                        }
                        %>
                          <a href="<%=path %>/CommodityManageServlet?level=guanliyuan&page=<%=nextEnd %>&start=<%=nextStart %>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                          </a>
                          <div class="pageDivP">
                          <%
                          //最大页数
                          int thisMaxPage=maxPage/4;
                          int thisPage=i/4;
                          if(thisMaxPage<=0)
                          {
                        	  thisMaxPage=1;
                          }
                          if(thisPage<=0)
                          {
                        	  thisPage=1;
                          }
                          %>
                          <p>共<%=thisMaxPage%>页</p>
                          <p>当前第<%=thisPage%>页</p>
                          </div>
                        </li>
                      </ul>
                    </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
