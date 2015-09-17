<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="manna.it.bean.MemberBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="Member" scope="page" class="manna.it.bean.MemberDAO" />

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/common/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/common/jquery/mem_jquery.js"></script>
<!-- <script type="text/javascript" src="/common/jquery/jquery-ui.js"></script> -->
<link rel="stylesheet" type="text/css" href="/common/css/common.css">
<!-- <link rel="stylesheet" type="text/css" href="/common/css/jquery-ui.css"> -->

<title>member</title>

<style>
tr.title{
	text-align: center;
	background-color:  #E9DDDD;
}
td {
 	padding: 3px;
}
table.mem_add td {
	text-align:left;
}

table tr.title{
	font-size:10px;
}
</style>
</head>
<body>
	<div class="main">
		<div class="search_box">
			<button id="mem_add">추가</button>
			<button id="mem_list_all">전체보기</button>
			<select name="dep_search" id="dep_search">
				<option value="0" selected>부서별</option>
				<c:forEach var="dl" items="<%=Member.getdepartmentList()%>"
					varStatus="status">
					<option value="${dl.m_depcode}">${dl.m_depname}</option>
				</c:forEach>
			</select>
			 <select name="pos_search" id="pos_search">
				<option value="0" selected>직위별</option>
				<c:forEach var="pl" items="<%=Member.getPositionList()%>"
					varStatus="status">
					<option value="${pl.m_poscode}">${pl.m_posname}</option>
				</c:forEach>
			</select>
		</div>




		<div id="content_body" class="content_body"></div>

		<div id="content_list">
<!-- 			<div>
				<table>
					<tr id="title">
						<td>No</td>
						<td>이름</td>
						<td>부서명</td>
						<td>내선번호</td>
						<td>전화번호</td>
						<td >모바일</td>
						<td>메일</td>
						<td>직위</td>
						<td>아이디</td>
					</tr>
				</table>
			</div> -->
			<div id="tab_container"></div>
			<div id="tab1" class="tab_content"></div>

		</div>
	</div>


</body>
</html>