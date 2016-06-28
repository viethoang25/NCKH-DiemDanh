<%@page import="model.bean.Student"%>
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
<script type="text/javascript" src="js/draw-rectangle-by-mouse.js"></script>
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
		String imagePath = (String) request.getSession().getAttribute("imagepath");
		List<Student> listStudent = (List<Student>) request.getAttribute("liststudent");
	%>
	
	<div id="container">
		<img class="img" id="image" src="<%=request.getContextPath()%>/<%=imagePath%>" alt="image" width="1024" height="768">
		<canvas id="canvas" width="1024" height="768" onload="init()"></canvas>
	</div>
	
	<form action="<%=request.getContextPath()%>/ProcessTeacherSetPosition" method="POST">
		<button type="submit" name="action" class="btn btn-primary" value="apply">Xác nhận</button>
		<select name="choosestudent">
			<%
				for(Student student : listStudent) {
			%>
				<option value="<%=student.getId()%>"><%=student.getId()%>-<%=student.getName()%></option>
			<%
				}
			%>
		</select>
		<input type="text" class="form-control" id="x1" name="x1">
		<input type="text" class="form-control" id="y1" name="y1">
		<input type="text" class="form-control" id="x2" name="x2">
		<input type="text" class="form-control" id="y2" name="y2">
	</form>
	
	<button type="submit" name="action" class="btn btn-primary" value="setposition" onclick="clearCanvas(document.getElementById('canvas'))">Xóa</button>

	<script>
		document.getElementById('canvas').innerHTML=initDraw(document.getElementById('canvas'), 
				document.getElementById('x1'), document.getElementById('y1'),
				document.getElementById('x2'), document.getElementById('y2'));
	</script>
</body>
</html>