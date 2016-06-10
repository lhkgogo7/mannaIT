<%@ page import="manna.it.bean.EquipmentBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="Equipment" scope="page"
	class="manna.it.bean.EquipmentDAO" />
<jsp:useBean id="Member" scope="page" class="manna.it.bean.MemberDAO" />
<!doctype html>
<html>
<head>
<script type="text/javascript" src="../common/jquery/jquery-2.1.3.js"></script>

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

	    $("#back").click(function(){
	     	history.go(-1);	
	    });
	    
	 });
	 

	};
</script>

</head>
<body>
	<div class="equipment">


		<div id="title"
			style="float: center; margin-top: 15px; font-family: 굴림; font-size: 10pt;">
			<%-- <c:set var="co" value='<%=Equipment.getEquipmentCategoryList()%>' /> --%>
			<form action="/rentalAddAction.eq" method="post" >

				<table border=1 class="add">
					<tr>
						<th>대여장비</th>
						<td><input type="text" id="rent_eqcode" name="eq_code" ></td>
					</tr>
					<tr>
						<th>사용자(대여자)</th>
						<td>
							<select name="rent_mname" id="rent_mname" >
							 	<option>선택하세요</option>
								<c:forEach var="ms" items="<%=Member.getMemberSelect()%>" varStatus="status">
		 							<option value="${ms.m_code}" >${ms.m_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>등록일</th>
						<td>
							<input type="date" id="rent_date" name= "rent_date">
						</td>
					</tr>
					<tr>
						<td colspan=11 style="text-align: center">
							<input type="submit" value="작성완료">
							<input type="button" id = "back" value="뒤로">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>