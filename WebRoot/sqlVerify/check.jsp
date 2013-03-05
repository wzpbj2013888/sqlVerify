<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
<script type='text/javascript'
	src='<%=basePath%>dwr/interface/CheckChangeNOExists.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>
<script type="text/javascript">
	exist = false;
	function t(data) {
		console.log(data);
	}
	function callBackHello(data) {
		

		$("#sqlError0").remove();
		
		$("#notChecked").remove();
		$("#nullChangeNo").remove();
		
		if (data == "notExists") {
			
			$("#label4submitTask").css({'color':'red'},3000);
			
			
			$("#submitTask_taskName").focus();
			//window.location = "submit.action";
		} else if (data == "notChecked") {
			$("body")
					.prepend(
							"<div id='notChecked' style=\"color:red;\">sql验证已经提交，等待DBA验证</div>");
		} else if (data == "sqlError0") {
			$("#sqlError0").remove();
			$("body").prepend(
					"<div id='sqlError0' style=\"color:red;\">变更号只能为数字</div>");
		} else if (data == "exists") {

			window.location = "submitWithExist.html";
		}
		//console.log(data);
	}
	function checkChangeExist(e) {

		e.preventDefault();
		if ($("#changeNo").val() == "") {

			$("#nullChangeNo").remove();
			$("body")
					.prepend(
							"<div id='nullChangeNo' style=\"color:red;\">变更号不能为空</div>");
			return false;
		}

		CheckChangeNOExists.queryChangeNo($("#changeNo").val(), callBackHello);
	}
	$(document).ready(function() {	
		
	});
</script>
<script type="text/javascript">
	
</script>
</head>
<body>

	<s:label cssStyle="color:green;" for="submitExist" value="按照变更号提交验证申请" />
	<s:form id="submitExist">
		<s:textfield type="text" id="changeNo" label="输入变更号" />
		<s:submit onclick="checkChangeExist(event)" value="检查是否存在" />
	</s:form>

	<s:label id="label4submitTask" cssStyle="color:green;" for="submitTask" value="按照任务名称提交验证申请：" />
	<s:form id="submitTask">
		<s:textfield name="taskName" label="任务名称" />
		<s:file name="taskFile" label="上传附件" />
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>