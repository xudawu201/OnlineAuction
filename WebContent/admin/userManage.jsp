<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/admin/";
List<User> users = new ArrayList<User>();
users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户管理</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/bid.css">
<link rel="stylesheet" type="text/css" href="css/goods.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
	<!--头部-->
	<div class="top-parent">
		<div class="top-title">
			<div class="top-title-left">
				<a href="<%=path %>/admin/index.jsp" style="text-decoration: none;"> <font size="6" color="#191970" class="top-title-text">用户管理</font>
				</a>
			</div>
		</div>
	</div>
	<!--主体-->
	<div>
		<div class="content-div">
			<div class="content">
				<div class="content-table">
					<table class="table table-striped">
						<tr>
							<th>ID</th>
							<th>用户名</th>
							<th>用户密码</th>
							<th>出售数量</th>
							<th>购买数量</th>
							<th>用户权限</th>
							<th></th>
						</tr>
						<%
	                    int i;
	                    int maxPage=users.size();
	                    int start=Integer.parseInt(request.getParameter("start"));
	                    int end=Integer.parseInt(request.getParameter("end"));
	                    //超出最大
	                    if(end>=maxPage)
	                    {
	                    	end=maxPage;
	                    }
	//                     out.print(maxPage);
	                    %>
	                    <%for(i=start;i<end;i++){%>
						
<%-- 						<%for(int i=0;i<users.size();i++){ %> --%>
						<tr>
							<td><font style="font-weight: bold;"><%=users.get(i).getId() %></font></td>
							<td><%=users.get(i).getName()%></td>
							<td><%=users.get(i).getPassword()%></td>
							<td><%=users.get(i).getAuction_number()%></td>
							<td><%=users.get(i).getBought_number()%></td>
							<%if(users.get(i).getAdmin()==0) {%>
							<td>管理员</td>
							<%}else{%>
							<td>普通用户</td>
							<%}%>
<%-- 							<%=users.get(i).getAdmin()%></td> --%>
							<td>
								<%if(users.get(i).getAdmin()==1){%> 
								<a href="<%=path %>/UserManageServlet?changeAdmin=ok&userId=<%=users.get(i).getId()%>&start=<%=start%>&end=<%=end%>">变为管理员</a>
								<%}else{ %> 
								<a href="<%=path %>/UserManageServlet?changeAdmin=no&userId=<%=users.get(i).getId()%>&start=<%=start%>&end=<%=end%>">变为普通用户</a> <%} %>
							</td>
						</tr>
						<%} %>
					</table>
				</div>
<!-- 				<div class="bottom-div">
					<div style="float: right; margin-right: 2%;"></div>
				</div> -->
				<!--主体下面的分页栏-->
                <div class="bottom-div-user">
                    <div style="float: right;margin-right: 2%;">
                    <nav aria-label="Page navigation">
                      <ul class="pagination">
                        <li>
                        <%
                        int preEnd=i-6;
                        int preStart=preEnd-6;
                        if(preStart<=0)
                        {
                        	preStart=0;
                        }
                        preEnd=preStart+6;
                        if(preEnd>=maxPage)
                        {
                        	preEnd=maxPage;
                        }
//                         int preEnd=preStart+4;
                        %>
                          <a href="<%=path %>/UserManageServlet?end=<%=preEnd %>&start=<%=preStart %>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                          </a>
                        </li>
                        <li><a href="<%=path %>/UserManageServlet?start=0&end=6">1</a></li>
<%--                         <li><a href="<%=path %>/UserManageServlet?start=6&end=12">2</a></li> --%>
<%--                         <li><a href="<%=path %>/UserManageServlet?start=12&end=18">3</a></li> --%>
                        <li>
                        <%
                        int nextEnd=i+6;
                        if(nextEnd>=maxPage)
                        {
                        	nextEnd=maxPage;
                        }
                        int nextStart=nextEnd-6;
                        if(nextStart<=0)
                        {
                        	nextStart=0;
                        }
                        %>
                          <a href="<%=path %>/UserManageServlet?end=<%=nextEnd %>&start=<%=nextStart %>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                          </a>
                          <div class="pageDivP">
                          <%
                          //最大页数
                          int thisMaxPage=maxPage/6;
                          int thisPage=i/6;
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
                          <p>第<%=thisPage%>页</p>
                          </div>
                        </li>
                      </ul>
                    </nav>
                    </div>
                </div>
			</div>
		</div>
</body>
</html>
