<%@page import="model.bean.Coordinate"%>
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
body{text-align: center;background: #f2f6f8;}
.img{position:absolute;z-index:1;}

#container{
    display:inline-block;
    margin: 0 auto; 
    position:relative; 
  	}
  	
#canvas{
    position:relative;
    z-index:20;
	}
	.rectangle {
        border: 1px solid #FF0000;
        position: absolute;
    }
</style>
</head>
<body>

	<%
		String teacherId = (String) request.getSession().getAttribute("teacherid");
	%>
	
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
			<div class="mdl-layout__header-row">
            	<!-- Title -->
            	<span class="mdl-layout-title">Giảng viên xác nhận</span>
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
      	</header>
	
		<main class="mdl-layout__content mdl-color--grey-200">
	
		<%
			String imagePath = (String) request.getSession().getAttribute("imagepath");
		%>
	
			<div id="container">
				<img class="img" id="image" src="<%=request.getContextPath()%>/<%=imagePath%>" alt="image" width="1024" height="768">
				<canvas id="canvas" width="1024" height="768" onload="init()"></canvas>
			</div>
	
			<form action="<%=request.getContextPath()%>/ProcessTeacherApprove" method="POST">
				<button type="submit" name="action" class="btn btn-primary" value="apply">Xác nhận</button>
				<button type="submit" name="action" class="btn btn-primary" value="setposition">Chọn lại vị trí</button>
			</form>
    	</main>
    </div>
	<script>
		function drawRectangle(x1, y1, x2, y2) {
  			var canvas = document.getElementById('canvas');
  			var context = canvas.getContext('2d');

  			context.beginPath();
  			context.rect(x1, y1, x2 - x1, y2 - y1);
  			context.lineWidth = 2;
  			context.strokeStyle = 'black';
  			context.stroke();
		}

		'<%
			List<Coordinate> list = (List<Coordinate>) request.getAttribute("listcoordinate");
			for(Coordinate coor : list) {
		%>'
				document.getElementById('canvas').innerHTML = drawRectangle('<%=coor.getX1()%>', '<%=coor.getY1()%>', '<%=coor.getX2()%>', '<%=coor.getY2()%>');
		'<%
			}
		%>'
</script>

</body>
</html>