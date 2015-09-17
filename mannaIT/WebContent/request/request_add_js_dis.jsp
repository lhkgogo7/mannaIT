<%@ page import="manna.it.bean.RequestBean, java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="req" scope="page" class="manna.it.bean.RequestDAO" />
<!-- <!doctype html>
<html>
<head>
<script type="text/javascript" src="../jquery/jquery-2.1.3.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
// jQuery 기본 js파일 
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
//jQuery UI 라이브러리 js파일
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>



<script type="text/javascript">
	$(document).ready(function() {
		$('#rq_category').change(function() {
			alert(this.value);
		});

		$("#back").click(function() {
			history.go(-1);
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장비추가</title>

</head>
<body> -->


	<div id="title"
		style="float: center; margin-top: 15px; font-family: 굴림; font-size: 10pt;">

		<form action="/requestAddAction.rq" method="post">

			<table border=1>

				<tr>
					<th>요청분류</th>
					<td><select name="rq_category" id="rq_category">
							<option>선택하세요</option>
							<c:forEach var="rc" items="<%=req.getRequestCategoryList()%>">
								<option value="${rc.ca_code}">${rc.ca_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>요청자명</th>
					<td><input type="text" id="m_name" name="m_name"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" id="req_subject" name="req_subject"
						style="width: 800px;"></td>
				</tr>
				<tr>
					<th>요청내용</th>
					<td><TEXTAREA rows="10" cols="150" id="req_content"
							name="req_content"></TEXTAREA></td>
				</tr>
				<tr>
					<th>처리내용</th>
					<td><TEXTAREA rows="20" cols="150" id="req_report"
							name="req_report"></TEXTAREA></td>
				</tr>


				<tr>
					<td colspan=11 style="text-align: center"><input type="submit"
						value="작성완료"> <input type="button" id="back" value="뒤로">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<!-- </html> -->