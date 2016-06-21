<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống điểm danh</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/login-style.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<%
		String message = (String) request.getAttribute("message");  
	%>
	<div class="login">
  		<div class="login-triangle"></div>
  		<h2 class="login-header">Log in</h2>
			<form class="login-container" action="<%=request.getContextPath()%>/ProcessLogin" method="POST">
				<p><input type="text" class="form-control" placeholder="Tài khoản" name="username"></p>
				<p><input type="password" class="form-control" placeholder="Mật khẩu" name="password"></p>
				<%
					if(message != null) {	
				%>
					<p><font color=red size=2px><%=message%></font><p>
				<%
					}
				%>
				<p><input type="submit" class="btn btn-primary" value="Đăng nhập"></p>
			</form>
	</div>
</body>
</html>