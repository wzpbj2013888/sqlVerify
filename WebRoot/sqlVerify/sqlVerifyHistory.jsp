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
	function renderName(value,p,record) {
		return Ext.String
				.format("<b><a  href='sqlVerifyHistory.action?taskId="
						+ record.data.id + "'>���������֤���</a></b>");
	}
	Ext.onReady(function() {
	
				var taskId = (<%=request.getParameter("taskId")%> == null) ? "" : <%=request.getParameter("taskId")%>;
				console.log(taskId);
				
				fields = ['id','feedBackDate','status','submitDate','submitDate'];
				
				url = "<%= basePath %>json/showSqlVerifyHistory.action?taskId="+taskId;
				console.log(url);
				
				title = "������֤���";
				width = 700;
				height = 500;
				columns = [ 
				{
					text : "�ύ����",
					dataIndex : 'submitDate',
					width: 170
				}, 
				{
					text : "��������",
					dataIndex : 'feedBackDate',
					width: 170
				} , 
				{
					text : "״̬",
					dataIndex : 'status',
					width: 170
				}, 
				{
					text : "",
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
	<h1 style="font-size: 21px;">��ʷ��¼��ѯ</h1>
	<div id="topic-grid"></div>
</body>
</html>
