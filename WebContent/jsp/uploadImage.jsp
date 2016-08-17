<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống điểm danh</title>
<link href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="css/material.min.css">
<link rel="stylesheet" href="css/styles.css">
<script src="js/material.min.js"></script>
</head>
<body>

	<%
		String message = (String) request.getAttribute("message");  
	%>
	
	<form method="post"
		action="${ pageContext.request.contextPath}/ProcessUpload"
		encType="multipart/form-data">
		<input type="file" name="file" value="select images..." /> 
		<input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit" value="start upload" />
	</form>

	<%
		if(message != null) {	
	%>
		<p><font color=red size=5px><%=message%></font><p>
	<%
		}
	%>
	
	<form method="post" action="${pageContext.request.contextPath}/ProcessSync">
		<input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit" value="Sync" />
	</form>
	
</body>
</html>