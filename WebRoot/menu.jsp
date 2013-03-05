<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<html>
<head>
<title>Sql 验证平台</title>

<script type="text/javascript" src="extjs/ext-all.js"></script>
<style type="text/css">
li {
	float: left;
	margin-left: 20px;
}
</style>
<script type="text/javascript">
	Ext.onReady(function() {
		function handleClick(e, t) {
			e.preventDefault();
			//alert(e.href);
			window.parent.document.getElementById('container').setAttribute(
					"src", t.href);
		}
		var el = Ext.get('userList');
		/* el.on('click', function() {
			alert('you click on a link');
		}); */
		/* 
		 var arr = [ 'userList', 'changeInfo', 'indexPage', 'submitSql',
		 'logout', 'sqlVerifyStatus','planInfo' ]; */
		 
		 
		var arr = [  'indexPage', 'submitSql',
				'logout', 'sqlVerifyStatus', 'planInfo','taskList' ];
		for (ar in arr) {
			Ext.EventManager.on(arr[ar], 'click', handleClick);
		}

		//console.log(el);
	});
</script>
</head>



<body>
	<ul>
		<!--
		<li><a id="userList" href="extjs/paging.html">用户列表</a></li>
		<li><a id="changeInfo" href="extjs/changeInfoList.html">变更信息</a>
		</li>
		
		  -->
		<li><a id="indexPage" href="extjs/test.html">主页</a></li>

		

		

		<li><a id="submitSql" href="sqlVerify/check.jsp">提交sql验证</a></li>
		
		<li><a id="taskList" href="extjs/taskList.jsp">任务队列查询</a></li>
		
		

		<li><a id="sqlVerifyStatus" href="extjs/status.jsp">sql验证状态信息</a>
		</li>


		<li><a id="planInfo" href="extjs/planInfo.html">执行计划信息</a></li>

		<li><a id="logout" href="extjs/test.html">退出系统</a></li>

	</ul>
</body>
</html>