<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<script type='text/javascript' src='jquery/jquery-1.9.1.min.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/testDwr.js'></script>

<script type='text/javascript' src='<%=basePath%>dwr/interface/changeQeury.js'></script>


<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		function callBackHello(data) {
			console.log(data);
		}
		
		changeQeury.queryChangeNo("",callBackHello);
		
		//console.log(testDwr.hello(callBackHello));
		$("body").append("Hello");
	});
</script>
</head>
<body>


</body>

</html>