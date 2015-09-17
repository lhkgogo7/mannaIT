<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="Member" scope="page" class="manna.it.bean.MemberDAO" />

<form action="/memberAddAction.mb"  method="post" onsubmit="return checkSubmit()">
	<table class="mem_add">
		<tr>
			<th>부서명</th>
			<td><select name="m_depcode" id="m_depcode">
				<option value="400" selected>부서선택</option>
				<c:forEach var="dl" items="<%=Member.getdepartmentList()%>"
					varStatus="status">
					<option value="${dl.m_depcode}">${dl.m_depname}</option>
				</c:forEach>
			</select> </td>
			<th>직위</th>
			<td> <select name="m_poscode" id="m_poscode">
				<option value="0" selected>직위선택</option>
				<c:forEach var="pl" items="<%=Member.getPositionList()%>"
					varStatus="status">
					<option value="${pl.m_poscode}">${pl.m_posname}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td colspan ='3'><input type="text" name="m_name" id="m_name"></td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td colspan ='3'><input type="text" name="m_extension" id="m_extension"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td colspan ='3'><input type="text" name="m_phone" id="m_phone"></td>
		</tr>
		<tr>
			<th>모바일</th>
			<td colspan ='3'><input type="text" name="m_mobile" id="m_mobile"></td>
		</tr>
		<tr>
			<th>메일</th>
			<td colspan ='3'><input type="text" name="m_mail" id="m_mail"></td>
		</tr>

		<tr>
			<th>아이디</th>
			<td colspan ='3'><input type="text" name="m_id" id="m_id"></td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td colspan ='3'><input type="text" name="m_pw" id="m_pw"></td>
		</tr>
		<tr>
			<td colspan ='4'>
				<button id="add_end" >추가완료</button>
			</td>
		</tr>
	</table>
</form>
