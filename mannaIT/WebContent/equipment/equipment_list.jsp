<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="manna.it.bean.EquipmentBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="Equipment" scope="page"
	class="manna.it.bean.EquipmentDAO" />

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장비리스트</title>
<script type="text/javascript" src="/common/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/common/jquery/eq_jquery.js"></script>
<link rel="stylesheet" type="text/css" href="/common/css/common.css">


<style type="text/css">
#modal_back {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: none;
	background: rgba(0, 0, 0, 0.5);
}

#modal {
	position: absolute;
	background: #FFF;
	color: #000;
}

#modal a {
	color: #000;
}
/* table {
			border-collapse: collapse;
			border : 1px;
			text-align: center;
			margin:0px;
			padding :0px;
			cellpadding:5px;
			cellspacing:0px;
		}
		
		a {
			text-decoration: overline;
		}
		
		.input_box {
			height: 20px;
			width: 150px;
			border: 1px solid #333;
			border-radius: 10px;
			background-color: #FEFDED;
		}
		
		.pulldown_box {
			height: 20px;
			width: 55px;
			border: 1px solid #333;
			border-radius: 3px;
		}
		#title {
			border:0;
			text-align : center;
			height:27px;
			width:670px;
			background-color :#f5bf78;
			
		}
		.name:hover{
			font-weight: bold;
			cursor: pointer;
		}
		 */
</style>
</head>



<body>
	<div class="main">
		<div class="search_box">
			<button id="eq_add">추가</button>
			<button id="eq_list_all">전체보기</button>
			<select name="eq_ca_search" id="eq_ca_search">
				<option value="0" selected>장비 분류별</option>
				<c:forEach var="el"
					items="<%=Equipment.getEquipmentCategoryList()%>"
					varStatus="status">
					<option value="${el.eq_ca_code}">${el.eq_ca_name}</option>
				</c:forEach>
			</select>

		</div>
		<div id="content_body" class="content_body"></div>
		<div id="content_list">
			<div>
				<table>
					<tr class="title">
						<td width="20px">No</td>
						<td width="140px">장비코드</td>
						<td width="180px">장비명</td>
						<td width="150px">제조사</td>
						<td width="130px">장비분류</td>
						<td width="130px">날짜</td>
						<!-- <td width="100px">첨부파일</td> -->
						<td width="40px">수정</td>
						<td width="40px">삭제</td>
					</tr>

				</table>
			</div>

			<div id="tab_container" class="tab_content"></div>
			<div id="modal_back">
				<div id="modal">
					<a href="javascript:closeModal()">Close</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>