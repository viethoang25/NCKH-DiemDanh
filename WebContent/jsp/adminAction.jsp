<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống điểm danh</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

	<%
		String message = (String) request.getAttribute("message");  
	%>
	
	<form action="<%=request.getContextPath()%>/ProcessAdminAction" method="POST">
		<button type="submit" name="action" class="btn btn-primary" value="create">Tạo các file</button>
		<button type="submit" name="action" class="btn btn-primary" value="delete">Xóa</button>
		<button type="submit" name="action" class="btn btn-primary" value="logout">Đăng xuất</button>
	</form>
	
	<%
		if(message != null) {	
	%>
		<p><font color=red size=5px><%=message%></font><p>
	<%
		}
	%>


</body>
</html>