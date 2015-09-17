<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="manna.it.bean.RequestBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<jsp:useBean id="Request" scope="page" class="manna.it.bean.RequestDAO" />

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/common/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/common/jquery/req_jquery.js"></script>

<link rel="stylesheet" type="text/css" href="/common/css/common.css">
<link rel="stylesheet" type="text/css" href="/common/css/req_css.css">

</head>


<body>
	<div class="main">
		<div class="search_box">
			<button id="add">추가</button>
			<button id="list_all">전체보기</button>
			<select name="res_search" id="res_search">
				<option value="400">전체</option>
				<c:forEach var="rrl" items="<%=Request.getRequestResultList()%>"
					varStatus="status">
					<option value="${rrl.res_code}">${rrl.res_name}</option>
				</c:forEach>
			</select> <select name="req_search" id="req_search">
				<c:forEach var="rcu" items="<%=Request.getRequestCategoryList()%>"
					varStatus="status">
					<option value="${rcu.ca_code}">${rcu.ca_name}</option>
				</c:forEach>
			</select>
		</div>
	
	
	
	
		<div id="content_body"></div>
	
		<div id="content_list">
			<div>
				<table>
					<tr id="title">
						<td width="25px">No</td>
						<td width="100px">요청코드</td>
						<td width="100px">요청분류명</td>
						<td width="200px">요청제목</td>
						<td width="100px">요청자명</td>
						<!-- 				<td width="100px">요청내용</td> -->
						<td width="100px">요청날짜</td>
						<!-- 				<td width="100px">요청처리내용</td> -->
						<td width="100px">요청결과</td>

					</tr>
				</table>
			</div>
			<div id="tab_container"></div>
			<div id="tab1" class="tab_content"></div>
	
		</div>
	</div>


</body>
</html>