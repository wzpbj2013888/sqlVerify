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
	<div style="color:red;">��������ڣ����ύ�������</div>
	<form action="../submitSqlVerify.action">

		<label for="submitter">�ύ��</label> <input type="text" id="submitter"
			name="submitter" /> <br> 
			
			<label for="changeName">�������</label> <input
			type="text" id="changeName" name="changeName" /><br>
			
			
		<label for="changeFile">�������</label> <input type="file" id="changeFile"
			name="changeFile" /> <br> 
			<label for="optimizer">�Ż���ģʽ</label>
			<select id="optimizer">
				<option>RULE</option>
				<option>CHOOSE</option>
				<option>FIRST_ROWS</option>
				<option>ALL_ROWS</option>
			</select> <br> 
			
			<input type="submit" value="�ύ">
		<br>
	</form>
</body>
</html>