<%@ page import="manna.it.bean.RequestBean, java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="req" scope="page" class="manna.it.bean.RequestDAO" />

<!-- 	<div id="title"
		style="float: center; margin-top: 15px; font-family: 굴림; font-size: 10pt;">
 -->
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
					<th>요청자명</th>
					<td class="member">
						<select name="m_code" id="m_code">
								<option>선택하세요</option>
								<c:forEach var="mem" items="<%=req.getMemberList()%>">
									<option value="${mem.m_code}">${mem.m_name}</option>
								</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<th>제목</th>
					<td style="text-align: left" colspan=3><input type="text" id="req_subject" name="req_subject"
						style=" width: 98.5%; margin-left: 3px;"></td>
				</tr>
				<tr>
					<th>요청내용</th>
					<td colspan=3><TEXTAREA rows="10" cols="100%" id="req_content"
							name="req_content"></TEXTAREA></td>
				</tr>
				<tr>
					<th>처리내용</th>
					<td colspan=3><TEXTAREA rows="20" cols="100%" id="req_report"
							name="req_report"></TEXTAREA></td>
				</tr>


				<tr>
					<td colspan=11 style="text-align: center"><input type="submit"
						value="작성완료">
					</td>
				</tr>
			</table>
		</form>
<!-- 	</div> -->
