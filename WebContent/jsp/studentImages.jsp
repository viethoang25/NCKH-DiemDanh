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
a {
    text-decoration: none !important;
}
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
.image_grayscale{
    -webkit-filter: grayscale(100%);
    filter: grayscale(100%);
}
</style>
</head>
<body>
	<%
		String studentId = (String) request.getSession().getAttribute("studentid");
	%>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
			<a class="mdl-layout-icon mdl-button mdl-js-button mdl-button--icon" href="<%=request.getContextPath()%>/ShowStudentUnits">
				<i class="material-icons">arrow_back</i>
  			</a> 
			<div class="mdl-layout__header-row">
            	<!-- Title -->
            	<span class="mdl-layout-title">Danh sách hình ảnh</span>
            	<!-- Add spacer, to align navigation to the right -->
            	<div class="mdl-layout-spacer"></div>
				<label class="mdl-checkbox__label"><%=studentId %></label>
				<button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
					<i class="material-icons">more_vert</i>
				</button>
				<ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
					<a href="<%=request.getContextPath()%>/ProcessAccountDetails">
						<li class="mdl-menu__item">Thông tin tài khoản</li>
					</a>
					<a href="<%=request.getContextPath()%>/ProcessLogout">
						<li class="mdl-menu__item">Đăng xuất</li>
					</a>
				</ul>
         	</div>
      	</header>
      	
      	<main class="mdl-layout__content mdl-color--grey-200">
			<div class="mdl-grid">
			<%
				List<String> list = (List<String>) request.getAttribute("listimagepath");
				List<String> listDisable = (List<String>) request.getAttribute("listdisableimagepath");
				if (list != null) {
					for (String path : list) {
			%>
					<%if (!listDisable.contains(path)) { %>
					<div class="image-card mdl-card mdl-shadow--2dp mdl-cell mdl-cell--3-col"
						style="background: url('../<%=request.getContextPath()%>/<%=path%>') center / cover;">
						<div class="mdl-card__title mdl-card--expand"></div>
						<form action="<%=request.getContextPath()%>/ProcessStudentImages" method="POST">
							<a href="javascript:;" onclick="parentNode.submit();">
								<input type="hidden" name="imagepath" value="<%=path%>"/>
  								<div class="mdl-card__actions">
    								<span class="demo-card-image__filename"><%=path%></span>
  								</div>
							</a>
						</form>
					</div>
					<%} else { %>
					<div class="image-card mdl-card mdl-shadow--2dp mdl-cell mdl-cell--3-col image_grayscale"
						style="background: url('../<%=request.getContextPath()%>/<%=path%>') center / cover;">
						<div class="mdl-card__title mdl-card--expand"></div>
  						<div class="mdl-card__actions">
    							<span class="demo-card-image__filename"><%=path%></span>
  						</div>
					</div>
					<%} %>
			<%
					}
				}
		%>
	</div>
	</main>
	</div>
</body>
</html>