<%@ page import="manna.it.bean.EquipmentBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>


<!doctype html>
<html>
<head>
<script type="text/javascript" src="../jquery/jquery-2.1.3.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<!-- // jQuery 기본 js파일  -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- //jQuery UI 라이브러리 js파일 -->
 <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>



<script type="text/javascript">
	 $(document).ready(function(){
		
	    $("#back").click(function(){
	     	history.go(-1);	
	    });
	    $(".modify").click(function(){
	    	 var num = $(this).parent().parent().parent().children().children('.no').html();
	    	 alert(num);
				var loc ="/equipmentModifyView.eq?num="+num;
				location.href(loc); 
	    });
	 });
	 
	 
</script>
<style type="text/css">
		table {
			border-collapse: collapse;
			border : 1px;
			text-align: center;
			margin:0px;
			padding :0px;
			cellpadding:5px;
			cellspacing:0px;
		}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장비수정</title>

</head>
<body>
	<div class="equipment">


		<div id="title"	style="float: center; margin-top: 15px; font-family: 굴림; font-size: 20pt;">
			<h2>장비view</h2>
		</div>
		<div id="title" style="float: center; margin-top: 15px; font-family: 굴림; font-size: 10pt;">
			
			

				<table border=1 class="add">
					<tr>
						<th>장비코드</th>
						<td class="no">${ev.num}</td>
					</tr>
					<tr>
						<th>장비명</th>
						<td>${ev.eq_name}</td>
					</tr>
					<tr>
						<th>제조사</th>
						<td>${ev.manufacturer}</td>
					</tr>
					<tr>
						<th>장비분류</th>
						<td>
							${ev.eq_category}
						</td>
					</tr>
					<tr>
						<th>구입날짜</th>
						<td>
							${ev.date}
						</td>
					</tr>
					<tr>
						<td colspan=11 style="text-align: center">
							<input type="button" class= "modify" value="수정">
							<input type="button" id = "back" value="뒤로">
						</td>
					</tr>
				</table>
			
		</div>
	</div>
</body>
</html>