<%@page import="java.util.List"%>
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
<style>
.demo-card-image > .mdl-card__actions {
  height: 52px;
  padding: 16px;
  background: rgba(0, 0, 0, 0.2);
}
.demo-card-image__filename {
  color: #fff;
  font-size: 14px;
  font-weight: 500;
}
</style>
</head>
<body>
	<h3>Danh sách hình ảnh</h3>
	<div class="mdl-grid">
	<%
		List<String> list = (List<String>) request.getAttribute("listimagepath");
		if (list != null) {
				for (String path : list) {
	%>
					<div class="image-card mdl-card mdl-shadow--2dp mdl-cell mdl-cell--3-col"
						style="background: url('../<%=request.getContextPath()%>/<%=path%>') center / cover;">
						<div class="mdl-card__title mdl-card--expand"></div>
						<form action="<%=request.getContextPath()%>/ProcessTeacherImages" method="POST">
							<a href="javascript:;" onclick="parentNode.submit();">
								<input type="hidden" name="imagepath" value="<%=path%>"/>
  								<div class="mdl-card__actions">
    								<span class="demo-card-image__filename"><%=path%></span>
  								</div>
							</a>
						</form>
					</div>
	<%
				}
		}
	%>
	</div>
</body>
</html>