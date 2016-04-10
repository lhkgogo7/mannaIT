<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>만나전산관리시스템</title>
<script type="text/javascript" src="/common/jquery/jquery-2.1.3.js"></script>

<style>
#div_root {
	margin: auto;
	width: 1000px;
}

#div_top {
	width: 100%;
	height: 100px;
	background-color: #E9DDDD;
	text-align: center;
}

#div_menu {
	width: 15%;
	height: 300px;
	float: left;
	/* background-color: #E9DDDD; */
	text-align: left;
}

#div_menu ul {
	list-style-type: none;
	font-size: 20px;
	color: #E5dddd;
	padding-left: 5px;
}

#div_menu ul li {
	padding: 10px;
	border-bottom: dotted 2px #E9DDDD;
	cursor: pointer;
}

#div_con {
	width: 85%;
	height: 100%;
	float: right;
	/* backfoutnd-image:url("image/bg_image02.jpg"); */
	backgound-size: 100% 100%;
	text-align: center;
}

#div_bottom {
	width: 100%;
	height: 100px;
	clear: both;
	/* background-color: #C8FE2E; */
	text-align: center;
}

.P1 {
	font-size: 50px;
	color: #BCF5A9;
}

.P2 {
	font-size: 8px;
	color: #E9DDDD;
}
</style>

<script>
var content = "/equipment.eq";
<%
if(request.getParameter("content")!=null){ String cont =request.getParameter("content"); System.out.println(cont);
	%>content ="<%=cont%>"; alert(content); <%
};

%>


$(document).ready(function(){
 	$("#div_con").load("/equipment.eq"); 
	
 	
	$("#eq").click(function(){
		content="/equipment.eq";
		$("#div_con").load(content);
		
	});
	$("#req").click(function(){
		content="/request.rq";
		$("#div_con").load(content);
		
	});

	$("#mem").click(function(){
		content="/member.mb";
		$("#div_con").load(content);
		
	});
	

	$("#rt").click(function(){
		content="/rental.eq";
		$("#div_con").load(content);



		
	});
	
	$("#div_menu ul li").hover(function(){
		$(this).css('color','#E9d11D');
	},function(){
		$(this).css('color','#E9Dddd');
	}
	);
	
});
</script>
</head>
<body>

	<div id="div_root">
		<div id="div_top">
			<!-- div_top -->
		</div>
		<nav id="div_menu">
			<!-- div_menu -->
			<ul>
				<li id="eq">H/W</li>
				<li id="s/w">S/W</li>
				<li id="req">요청</li>
				<li id="rt">장비대여</li>
				<li id="req">지출결의서</li>
				<li id="req">기안서</li>
				<li id="rep">업무일지</li>
				<li id="mem">Member</li>
				
			</ul>

		</nav>
		<div id="div_con">
			<font class="P1"> <!-- div_con -->
			</font>
		</div>
		<div id="div_bottom">
			<font class="P2">ⓒ 2015. mannaIT all rights reserved.</font>
		</div>

	</div>

</body>
</html>