<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	
</head>

<body>
	<s:form action="../submitSql.action">
		<s:textfield id="submitter" name="submitter" label="提交人" />
		<s:textfield id="changeId" name="changeId" label="变更号" />
		<s:textfield id="changeName" name="changeName" label="变更名称" />
		<s:file id="changeAttachment" name="changeAttachment" label="变更附件"></s:file>
		<s:select id="optimizer_mode" name="optimizer_mode" label="optimizer_mode" list="{'rule','choose','first_rows','all_rows'}" />
		<s:submit label="提交"></s:submit>
	</s:form>
</body>