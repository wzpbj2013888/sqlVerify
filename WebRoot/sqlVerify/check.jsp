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
							"<div id='notChecked' style=\"color:red;\">sql��֤�Ѿ��ύ���ȴ�DBA��֤</div>");
		} else if (data == "sqlError0") {
			$("#sqlError0").remove();
			$("body").prepend(
					"<div id='sqlError0' style=\"color:red;\">�����ֻ��Ϊ����</div>");
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
							"<div id='nullChangeNo' style=\"color:red;\">����Ų���Ϊ��</div>");
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

	<s:label cssStyle="color:green;" for="submitExist" value="���ձ�����ύ��֤����" />
	<s:form id="submitExist">
		<s:textfield type="text" id="changeNo" label="��������" />
		<s:submit onclick="checkChangeExist(event)" value="����Ƿ����" />
	</s:form>

	<s:label id="label4submitTask" cssStyle="color:green;" for="submitTask" value="�������������ύ��֤���룺" />
	<s:form id="submitTask">
		<s:textfield name="taskName" label="��������" />
		<s:file name="taskFile" label="�ϴ�����" />
		<s:submit value="�ύ"></s:submit>
	</s:form>
</body>
</html>