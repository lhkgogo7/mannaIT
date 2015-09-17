<%@ page import="manna.it.bean.EquipmentBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="Equipment" scope="page" class="manna.it.bean.EquipmentDAO"/>
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
		$('#eq_category').change(function(){
			alert(this.value);
		});
		
	    $( "#eq_date" ).datepicker({
	    	dateFormat: "yymmdd"
	    });
	    $("#back").click(function(){
	     	history.go(-1);	
	    });
	 });
	 
	 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장비수정</title>

</head>
<body>
	<div class="equipment">


		<div id="title"
			style="float: center; margin-top: 15px; font-family: 굴림; font-size: 20pt;">
			<h2>장비수정</h2>
		</div>
		<div id="title"
			style="float: center; margin-top: 15px; font-family: 굴림; font-size: 10pt;">
			
			<form action="/equipmentModifyAction.eq" method="post">

				<table border=1 class="add">
					<tr>
						<th>장비코드</th>
						<td><input type="text" id="eq_code" name="eq_code" value="${ev.num}"></td>
					</tr>
					<tr>
						<th>장비명</th>
						<td><input type="text" id="eq_name" name="eq_name" value="${ev.eq_name}"></td>
					</tr>
					<tr>
						<th>제조사</th>
						<td><input type="text" id="eq_manufacturer" name="eq_manufacturer"  value="${ev.manufacturer}"></td>
					</tr>
					<tr>
						<th>장비분류</th>
						<td>
							<select name="eq_category" id="eq_category" >
									<option selected value="${ev.eq_ca_code}" >${ev.eq_category}</option>
							 	<c:forEach var="ec" items="<%=Equipment.getEquipmentCategoryList()%>" varStatus="status">
		 							<option value="${ec.eq_ca_code}" >${ec.eq_category}</option>
								</c:forEach>
								
							</select>
						</td>
					</tr>
					<tr>
						<th>구입날짜</th>
						<td>
							<input type="text" id="eq_date" name= "eq_date" value="${ev.date}">
						</td>
					</tr>
					<tr>
						<td colspan=11 style="text-align: center">
							<input type="submit" value="수정">
							<input type="button" id = "back" value="뒤로">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>