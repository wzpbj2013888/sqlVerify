<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>show history</title>

<link rel="stylesheet" type="text/css" href="<%= basePath %>extjs/ext-all.css" />

<script type="text/javascript" src="<%= basePath %>extjs/ext-all.js"></script>
<script type="text/javascript">
	
	Ext.onReady(function() {
	
				var historyId = (<%=request.getParameter("historyId")%> == null) ? "" : <%=request.getParameter("historyId")%>;
				
				function renderName(value,p,record) {
				
					return Ext.String.format("<b><a  href='sqlVerifyList.action?historyId="
									+ record.data.id+"&sqlId="+record.data.sqlId+"'>点击进入验证结果</a></b>");
				}
							
				
				fields = ['sqlId','historyId','sqlStatement','id'];
				
				url = "<%= basePath %>json/showSqlQueries.action?historyId="+historyId;
				
				
				title = "Sql语句列表";
				width = 700;
				height = 500;
				columns = [ 
				{
					text : "Sql编号",
					dataIndex : 'sqlId',
					width: 170
				}, 
				{
					text : "Sql语句",
					dataIndex : 'sqlStatement',
					width: 170
				} , 
				{
					text : "点击显示验证结果",
					dataIndex : 'id',
					width: 170,
					renderer:renderName
				} ];
			});
</script>
<script type="text/javascript" src="<%= basePath %>extjs/paging.js"></script>
<style>
.x-grid-cell-topic b {
	display: block;
}

.x-grid-cell-topic .x-grid-cell-inner {
	white-space: normal;
}

.x-grid-cell-topic a {
	color: #385F95;
	text-decoration: none;
}

.x-grid-cell-topic a:hover {
	text-decoration: underline;
}

.x-grid-cell-topic .x-grid-cell-innerf {
	padding: 5px;
}

.x-grid-rowbody {
	padding: 0 5px 5px 5px;
}
</style>
</head>
<body>
	<h1 style="font-size: 21px;">Sql 语句列表</h1>
	<div id="topic-grid"></div>
</body>
</html>
