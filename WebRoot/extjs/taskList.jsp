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
</head>

<body>
	<div><iframe  frameborder="0"  width="100%" height="100%" src="taskListCriteria.jsp"></iframe></div>
	<div>
	<iframe frameborder="0" width="100%" height="600px" id="container" src="taskListShow.jsp">
		
	</iframe>
	</div>
	
</body>
</html>
