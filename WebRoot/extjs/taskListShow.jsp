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
			return "���ύ��֤����";
		}else{
			a = "����ر�";
		}
		return "��֤�����ѷ���";
	}
	Ext.onReady(function() {
	
				fields = ['id','name','status'];
				url = "<%= basePath %>json/showTaskList.action";
				
				
				title = "������в�ѯ���";
				width = 700;
				height = 500;
				columns = [ 
				{
					text : "��������",
					dataIndex : 'name',
					width: 170,
					renderer: renderName
				}, 
				{
					text : "����״̬",
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
