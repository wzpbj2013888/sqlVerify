<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Paging Grid Example</title>
<style type="text/css">
.sqlInfo {
	font-size: 23px;
}
</style>
<link rel="stylesheet" type="text/css" href="ext-all.css" />
<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
<script type='text/javascript'
	src='<%=basePath%>dwr/interface/GetSqlById.js'></script>
<script type='text/javascript'
	src='<%=basePath%>dwr/interface/DBACheck.js'></script>


<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>

<script type="text/javascript" src="ext-all.js"></script>
<script type="text/javascript">
	function afterCheck(){
		location.href="status.jsp";
	}
	function rendererCompany(value) {
		return Ext.String
				.format("<b><a alt='click to look up this on baidu' href='http://www.baidu.com/s?wd="
						+ value + "'>" + value + "</a></b>");
	}
	function callback(data){
		$("#sqlInfo").append("<h1> 当前Sql语句是：<span style='color:red;'>"+data+"</span></h1>");
		$("#sqlInfo").append("<h1> 当前Sql验证状态是：<span style='color:red;'>"+changeState+"</span></h1>");
	}
	Ext.onReady(function() {
				
				statementId = '<%=request.getParameterValues("stat_id")[0]%>';
				optimizer = '<%=request.getParameterValues("optimizer")[0]%>';
				
				changeState = '<%=request.getParameterValues("changeState")[0]%>';

				$("#sqlInfo").append(
						"<h1>当前优化器模式是：<span style='color:red;'>" + optimizer
								+ "</span></h1>");
				var a = GetSqlById.getSql(statementId, callback);
				//console.log(a);
				//$("#sqlInfo")

				fields = [ 'id', 'statementId', 'database', 'bytes',
						'carddinality', 'cost', 'description', 'io_cost',
						'object_name', 'object_owner', 'operation', 'time' ];
				width = 900;
				height = 300;
				url = parent.getRootUrl()
						+ 'json/showAll.action?entityName=ExecutionPlanInfo&statementId='
						+ statementId;
				title = "Sql执行计划信息对比";
				columns = [ {
					text : "ID",
					dataIndex : 'id'
				}, {
					text : "Sql语句Id",
					dataIndex : 'statementId',
					width : 170
				}, {
					text : "数据库",
					dataIndex : 'database',
					width : 170
				},

				{
					text : "bytes",
					dataIndex : 'bytes'
				}, {
					text : "carddinality",
					dataIndex : 'carddinality',
					renderer : rendererCompany
				}, {
					text : "cost",
					dataIndex : 'cost'
				}, {
					text : "description",
					dataIndex : 'description'
				}, {
					text : "object_name",
					dataIndex : 'object_name'
				}, {
					text : "object_owner",
					dataIndex : 'object_owner'
				}, {
					text : "operation",
					dataIndex : 'operation'
				}, {
					text : "time",
					dataIndex : 'time'
				} ];
			});
</script>
<script type="text/javascript" src="paging.js"></script>


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
	<div id="sqlInfo" class="sqlInfo"></div>
	<div id="topic-grid"></div>
	<div>
		<br />
		<input type='button' value='验证通过'
			onclick='DBACheck.passCheck("<%=request.getParameterValues("stat_id")[0]%>",afterCheck);' />
			
			 <input type='button'
			value='验证不通过' onclick='DBACheck.failCheck("<%=request.getParameterValues("stat_id")[0]%>",afterCheck);' />
	</div>
</body>
</html>
