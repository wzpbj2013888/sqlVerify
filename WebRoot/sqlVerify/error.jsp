<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%String fla=(String)request.getAttribute("flag");%>
<% 
if(fla.equals("1")){%>
<p>
<h1>读取sql异常，异常信息如下：</h1><br>
	<font color="red">${execSqlResult}</font>
	
	<%} %>
	<%if(fla.equals("2")){ %>
	<p>
<h1>执行sql异常，异常信息如下：</h1><br>
	<font color="red">${execSqlResult}</font>
	
	<%} %>

</body>
</html>