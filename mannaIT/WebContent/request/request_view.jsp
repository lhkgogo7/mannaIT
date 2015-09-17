<%@ page import="manna.it.bean.RequestBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="req" scope="page"	class="manna.it.bean.RequestDAO" />

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />


<style type="text/css">
table {
	border-collapse: collapse;
	border: 1px;
	text-align: center;
	margin: 0px;
	padding: 0px;
	cellpadding: 5px;
	cellspacing: 0px;
}

table th {
	width: 100px;
}
table td {
	padding : 5px 2px 5px 2px;
	
}

textarea {
	border : 0;
	width:99%;
	height:90%;
}


</style>

			<form action="/requestModifyAction.rq" method="post">

				<table border=1 class="add">
					<tr>
						<th>요청코드</th>
						<td id="no">${rq.req_code}</td>
						<input type="hidden" name = "req_code" value="${rq.req_code}"/>
						<th>요청분류</th>
						<td><select name="rq_category" id="rq_category" >
							 	<option value="${rq.ca_code}" selected>${rq.ca_name}</option>
								<c:forEach var="rc" items="<%=req.getRequestCategoryList()%>">
		 							<option value="${rc.ca_code}" >${rc.ca_name}</option>
								</c:forEach>
							</select></td>
						<th>요청날짜</th>
						<td>${rq.req_date}</td>
						<th>요청처리상황</th>
						<td>${rq.res_name}</td>
						
					</tr>
					<tr>
						<th>요청자명</th>
						<td colspan="7">${rq.m_name}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td  colspan="7" ><input type ="text" name = "req_subject" value="${rq.req_subject}"></td>
						
					</tr>
					<tr>
						<th>요청내용</th>
						<td colspan="7"><TEXTAREA rows="10" cols="150"
								id="req_content" name="req_content">${rq.req_content}</TEXTAREA></td>
					</tr>
					<tr>
						<th>처리내용</th>
						<td colspan="7"><TEXTAREA rows="20" cols="150"
								id="req_report" name="req_report">${rq.req_report}</TEXTAREA></td>
					</tr>

					<tr>
						<td colspan=11 style="text-align: center" colspan="3">
							<input type="submit" value="수정"> 
							<input type="button"	id="delete" value="삭제">
						</td>
					</tr>
				</table>
			</form>
