<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Paging Grid Example</title>

<link rel="stylesheet" type="text/css" href="<%= basePath %>extjs/ext-all.css" />

<script type="text/javascript" src="<%= basePath %>extjs/ext-all.js"></script>
<script type="text/javascript">
	function renderName(value,p,record) {
		return Ext.String
				.format("<b><a  href='sqlVerifyHistory.jsp?taskId="
						+ record.data.id + "'>" + value + "</a></b>");
	}
	function renderStatus(value){
		if(value=="submitted"){
			return "已提交验证申请";
		}else{
			a = "任务关闭";
		}
		return "验证申请已反馈";
	}
	Ext.onReady(function() {
	
				fields = ['id','name','status'];
				url = "<%= basePath %>json/showTaskList.action";
				
				
				title = "任务队列查询结果";
				width = 700;
				height = 500;
				columns = [ 
				{
					text : "任务名称",
					dataIndex : 'name',
					width: 170,
					renderer: renderName
				}, 
				{
					text : "任务状态",
					dataIndex : 'status',
					width: 170,
					renderer:renderStatus
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

	<div id="topic-grid"></div>
</body>
</html>
