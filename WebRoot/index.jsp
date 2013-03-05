<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base id="baseUrl" href="<%=basePath%>">
    <script type="text/javascript">
    	function getRootUrl(){
    		return document.getElementById("baseUrl").href;
    	}
    </script>
</head>
  
  <body>
    <iframe frameborder="no" src="menu.jsp" width="90%" height="50px;"></iframe>
    
    <!-- <iframe frameborder="no" width="90%" height="900px;" src="extjs/changeInfoList.html"></iframe>
   -->
   <iframe id='container' frameborder="no" width="90%" height="900px;" src="extjs/test.html"></iframe>
  
  
  </body>
</html>
