<%@page import="model.bean.Person"%>
<%@page import="model.bean.Account"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.Unit"%>
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
		Account account = (Account) request.getSession().getAttribute("account");
		Person person = (Person) request.getAttribute("person");
	%>
	
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
			<div class="mdl-layout__header-row">
            	<!-- Title -->
            	<span class="mdl-layout-title">Thông tin tài khoản</span>
            	<!-- Add spacer, to align navigation to the right -->
            	<div class="mdl-layout-spacer"></div>
				<label class="mdl-checkbox__label"><%=account.getId() %></label>
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
      	</header>
    
    	<main class="mdl-layout__content mdl-color--grey-200">
    		<div class="mdl-grid">
    			<div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">
    				<div class="mdl-card__title">
               			<h2 class="mdl-card__title-text"><%=person.getId()%></h2>
            		</div>
					<div class="mdl-card__supporting-text">
						Họ và tên : <%=person.getName()%><br>
               			Username: <%=account.getUsername()%><br>
               			Password: <%=account.getPassword() %><br>
            		</div>
         		</div>
			</div>
		</main>
   	</div>

</body>
</html>