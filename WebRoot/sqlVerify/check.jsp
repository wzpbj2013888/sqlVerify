<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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

		if (data == "notExists") {
			window.location = "submit.html";
		} else if (data == "notChecked") {
			$("#notChecked").remove();
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
	function checkChangeExist(changeNo) {
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
	<label for="changeNo">变更号</label>
	<input type="text" id="changeNo">
	<input type="button" onclick="checkChangeExist(234)" value="检查变更是否存在" />
</body>
</html>