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

<script type="text/javascript"
	src="<%=basePath%>jquery/jquery-1.9.1.min.js"></script>


<script type="text/javascript">
function query() {

window.parent.document.getElementById("container").src = "taskListShow.jsp?changeNo='"+($("#changeNo").val() =="" ? "" : $("#changeNo").val())+"'&taskName='"+($("#taskName").val() =="" ? '' : $("#taskName").val())+"'&status='"+($("#status").val() =="" ? '' : $("#status").val())+"'";
	}
	
</script>

</head>
<body>
	<table>
		<tr>
			<td>变更号</td>
			<td><input type="text" id="changeNo" />
			</td>
		</tr>
		<tr>
			<td>任务名称</td>
			<td><input type="text" id="taskName" />
			</td>
		</tr>
		<tr>
			<td>状态</td>
			<td><input type="text" id="status" />
			</td>
		</tr>
		<tr>
			<td><input type="button" value="查询" onclick="query();">
			</td>
		</tr>
	</table>
	<div id="topic-grid"></div>
</body>
</html>
