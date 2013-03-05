<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>

<body>
	<div style="color:red;">变更不存在，请提交变更附件</div>
	<form action="../submitSqlVerify.action">

		<label for="submitter">提交人</label> <input type="text" id="submitter"
			name="submitter" /> <br> 
			
			<label for="changeName">变更名称</label> <input
			type="text" id="changeName" name="changeName" /><br>
			
			
		<label for="changeFile">变更附件</label> <input type="file" id="changeFile"
			name="changeFile" /> <br> 
			<label for="optimizer">优化器模式</label>
			<select id="optimizer">
				<option>RULE</option>
				<option>CHOOSE</option>
				<option>FIRST_ROWS</option>
				<option>ALL_ROWS</option>
			</select> <br> 
			
			<input type="submit" value="提交">
		<br>
	</form>
</body>
</html>