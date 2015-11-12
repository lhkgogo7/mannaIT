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
<link rel="stylesheet" type="text/css" href="/common/css/common.css">
<script type="text/javascript">
	$(document).ready(function() {
		add_click();
		rt_list_ajax();
		eq_ca_search();
		eq_list_all();
		
		$(".delete").click(function() {
			var num = $(this).parent().parent().children('.no').html();
			var loc = "/equipmentDeleteAction.eq?eq_code=" + num;
			location.href(loc);
		});
		$(".modify").click(function() {
			var num = $(this).parent().parent().children('.no').html();
			var loc = "/equipmentModifyView.eq?eq_code=" + num;
			location.href(loc);
		});
	


	});

			
		/* function eq_list_ajax(eq_ca_code) {
			$.ajax({
				url : "/equipmentListAjax.eq",
				dataType : "text",
				data : {
					eq_ca_code : eq_ca_code
				},
				success : function(eq_list) {
					var eqList = eval(eq_list);
					var table = '<table class="m_table"><tr class="title">'
								+'<td>No</td><td>장비코드</td><td>장비명</td><td >제조사</td><td>장비분류</td><td>날짜</td><td>첨부파일</td><td>수정</td><td>삭제</td>'
								+'</tr>';
					$.each(eqList, function(index) {
						var eq_obj = eqList[index];
						table += "<tr>";
						table += "<td>" + (index + 1) + "<input type='hidden' name='eq_code' class='eq_code'value='"+eq_obj.eq_code+"'></td>";
						table += "<td >" + eq_obj.eq_code + "</td>";
						table += "<td ><input class='eq_name' type='text' value='" + eq_obj.eq_name + "'/></td>";
						table += "<td><input class='manufacturer' type='text' value='" + eq_obj.manufacturer + "'/></td>";
						table += "<td><select name='eq_ca_list' class='eq_ca_list'>" 
									+"<option value="+ eq_obj.eq_ca_code+" selected>"+ eq_obj.eq_ca_name+ "</option></td>";
						table += "<td ><input class='eq_date' type='text' value='" + eq_obj.eq_date_s + "'/></td>";
						table += "<td>"+eq_obj.eq_picture+"</td>";
						
						table += "<td ><button class='mod'><img  src='/common/img/ok.png'></button></td>"
									+"<td><button class='delete'><img src='/common/img/delete.png'></button></td></tr>";
					});
					
				}
			});
		}; */

	function add_click() {

		$("#eq_add").click(function() {
			alert('dd');
			add_load();
		});

	};
	function add_load() {
		$("#content_body").load("/equipmentAdd.eq");
		$("#content_body").slideDown(100);
		// div_slide();

	};
	// 요청결과에selector 변경에 따라 실시간 list 재호출
	function eq_ca_search() {
		$("#eq_ca_search").change(function() {
			var eq_ca_search = $("#eq_ca_search").val();
			alert(eq_ca_search);
			eq_list_ajax(eq_ca_search);

		});
	}
	function rt_list_ajax() {
		$
				.ajax({
					url : "/rentalListAjax.eq",
					dataType : "text",
				/* 	data : {
						eq_ca_code : eq_ca_code
					}, */
					success : function(rt_list) {
						var rtList = eval(rt_list);
						var table = '<table class="m_table">'
								+ '	<tr><td width="20px"></td>	<td width="150px"></td>	<td width="100px"></td><td width="100px"></td><td width="130px"></td> <td width="130px"></td> <td width="140px"></td> <td width="130px"></td>		<td width="40px"></td><td width="40px"></td></tr>';
						$.each(
										rtList,
										function(index) {
											var rt_obj = rtList[index];
											table += "<tr height='20px'>";
											table += "<td>"
													+ (index + 1)
													+ "<input type='hidden' name='rt_code' class='eq_code1'value='"+rt_obj.rt_code+"'></td>";
											table += "<td  ><div class='eq_name'>" + rt_obj.eq_name	+ "</div></td>";
											table += "<td >" + rt_obj.rt_m_name + "</td>";
											table += "<td>" + rt_obj.rs_name + "</td>";
											table += "<td>" + rt_obj.rt_sdate + "</td>";
											table += "<td>" + rt_obj.rt_edate + "</td>";
											table += "<td>" + rt_obj.rt_purpose + "</td>";
											table += "<td>" + rt_obj.rt_duedate + "</td>";

											table += "<td ><button class='mod'><img  src='/common/img/ok.png'></button></td>"
													+ "<td><button class='delete'><img src='/common/img/delete.png'></button></td></tr>";
										});

						table += "</table>";
						$("#tab_container").empty();
						$("#tab_container").append(table);

	/* 					eq_delete();
						//eq_modify();
						eq_view();
						eq_category_ajax();
						eq_front_modify();
						//pos_ajax();
						//dep_ajax(); */
					},
					error : function(err) {
						alert(err + "--->오류발생");
					}
				});
	};
	function eq_delete() {
		$(".delete").click(
				function() {
					var eq_code = $(this).parent().parent().children()
							.children('.eq_code').val();
					alert(eq_code);
					$.ajax({
						type : "POST",
						data : {
							eq_code : eq_code
						},
						url : "/equipmentDeleteAction.eq",
						success : function(result) {
							eq_list_ajax();
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							console.log("Status: " + textStatus);
						},
						timeout : 3000
					});
				});

	};
	function eq_modify() {
		$(".mod").click(
				function() {
					alert("modify");
					var eq_code = $(this).parent().parent().children()
							.children('.eq_code').val();

					
					alert(eq_code);
					var eq_mod = "/equipmentView.eq?eq_code=" + eq_code;
					
					window.open(eq_mod, "_blank", "width=450, height=400, toolbar=no,location=no, menubar=no, scrollbars=no, resizable=yes" );

				});

	};
	function eq_front_modify(){
		
		$(".mod").click(function(){
			//alert("modify eq_front_modify(");
			/* var eq_code = $(this).parent().parent().children().children('.eq_code').val(); */
			var eq_code = $(this).parent().parent().children('.eq_code').html(); 
			var eq_name = $(this).parent().parent().children().children('.eq_name').val();
			var eq_manufacturer = $(this).parent().parent().children().children('.manufacturer').val();
			var eq_date = $(this).parent().parent().children().children('.eq_date').val();
			var eq_ca_code = $(this).parent().parent().children().children('.eq_ca_list').val();
			
			//alert(eq_code+"/"+eq_name+"/"+eq_manufacturer+"/"+eq_date+"/"+eq_ca_code);
		
		
				$.ajax({
					type : "POST",
					data : {
						eq_code : eq_code,
						eq_name : eq_name,
						eq_manufacturer : eq_manufacturer,
						eq_date : eq_date,
						eq_ca_code : eq_ca_code	
					},
					url : "/equipmentFrontModifyAction.eq",
					success : function(result) {				
						eq_list_ajax();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("Status: " + textStatus);
					},
					timeout : 3000
			
			});
		});
		
	};
	
	function eq_view(){
		$(".eq_code").click(function() {
			alert("click");
			var eq_code = $(this).parent().children('.eq_code').html(); 
			alert(eq_code);
		
			var eq_mod = "/equipmentView.eq?eq_code=" + eq_code;
			
			window.open(eq_mod, "_blank", "width=450, height=400, toolbar=no,location=no, menubar=no, scrollbars=no, resizable=yes" );
		});
	}
				
	function eq_list_all(){
		$("#eq_list_all").click(function(){
			alert("all");
			eq_list_ajax();
			
		});
	}
	


	function eq_category_ajax() {
		$.ajax({
			url : "/equipmentCaListAjax.eq",
			dataType : "text",
			success : function(eq_ca_list) {
				var ca_list = eval(eq_ca_list);
				var select = "";
				
				$.each(ca_list, function(index) {
					var ca_obj = ca_list[index];

					select += '<option value="' + ca_obj.eq_ca_code + '">'
							+ ca_obj.eq_ca_name + '</option>';
				});
				select += "</select>";
				//dep_change();
				$('.eq_ca_list').append(select);

			},
			error : function(err) {
				alert(err + "--->오류발생res_ajax()");
			}
		});
	};
</script>

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
div.eq_name {
	overflow:hidden; 
	text-overflow:ellipsis;
	 width:140px;

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
						<td width="180px">장비명</td>
						<td width="100px">대여자</td>
						<td width="100px">대여상태</td>
						<td width="130px">대여시작일</td>
						<td width="130px">대여종료일</td>
						<td width="140px">대여목적</td>
						<td width="130px">반납일</td>				
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