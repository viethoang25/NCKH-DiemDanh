<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
</head>
<body>

	<%
		String teacherId = (String) request.getSession().getAttribute("teacherid");
		List<String> listImagePath = (List<String>) request.getAttribute("listimagepath");
		request.setAttribute("listimagepath", listImagePath);
	%>
	
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
		<header class="mdl-layout__header">
			<div class="mdl-layout__header-row">
            	<!-- Title -->
            	<span class="mdl-layout-title">Danh sách học phần</span>
            	<!-- Add spacer, to align navigation to the right -->
            	<div class="mdl-layout-spacer"></div>
				<label class="mdl-checkbox__label"><%=teacherId %></label>
				<button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
					<i class="material-icons">more_vert</i>
				</button>
				<ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
					<li class="mdl-menu__item">Thông tin tài khoản</li>
					<a href="<%=request.getContextPath()%>/ProcessLogout">
						<li class="mdl-menu__item">Đăng xuất</li>
					</a>
				</ul>
         	</div>
			<!-- Tabs -->
			<div class="mdl-layout__tab-bar mdl-js-ripple-effect">
            	<a href="#scroll-tab-1" class="mdl-layout__tab is-active">Upload</a>
            	<a href="#scroll-tab-2" class="mdl-layout__tab">Approve</a>
         	</div>
      	</header>
    
    	<main class="mdl-layout__content mdl-color--grey-200">
    		<section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
            	<div class="page-content">
            		<jsp:include page="uploadImage.jsp"/>
				</div>
         	</section>
         	<section class="mdl-layout__tab-panel" id="scroll-tab-2">
            	<div class="page-content">
            		<jsp:include page="teacherImages.jsp"/>
				</div>
         	</section>
		</main>
   	</div>

</body>
</html>