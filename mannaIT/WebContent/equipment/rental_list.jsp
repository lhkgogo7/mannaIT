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
		rt_add_click();
		rt_list_ajax();
		eq_ca_search();
		rt_list_all();
		
		var eq_ca_code =0;
		
		
		$(".modify").click(function() {
			var num = $(this).parent().parent().children('.no').html();
			var loc = "/equipmentModifyView.eq?eq_code=" + num;
			location.href(loc);
		});
	


	});
	function del_click(){
		$(".delete").click(function() {
			var rent_code = $(this).parent().parent().children().children('.rt_code').val();
			alert(rent_code);
			
			$.ajax({
				type : "POST",
				data : {
					rent_code : rent_code
				},
				url : "/eqRentalDelAction.eq",
				success : function(result) {
					rt_list_ajax();
				},
				error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					console.log("Status: " + textStatus);
				},
				timeout : 3000
			});
		});
				
	}

	function rt_add_click() {

		$("#rt_add").click(function() {
			alert('dd');
			add_load();
		});

	};
	function add_load() {
		$("#content_body").load("/rentalAdd.eq");
		$("#content_body").slideDown(100);
		// div_slide();

	};
	// 요청결과에selector 변경에 따라 실시간 list 재호출

	function rt_list_ajax(eq_ca_search) {
		$
				.ajax({
					url : "/rentalListAjax.eq",
					dataType : "text",
				 	data : {
						eq_ca_code : eq_ca_code
					}, 
					success : function(rt_list) {
						var rtList = eval(rt_list);
						var table = '<table class="m_table">'
								+ '	<tr><td width="20px"></td>	<td width="180px"></td>	<td width="100px"></td> <td width="130px"></td><td width="130px"></td><td width="40px"></td><td width="40px"></td></tr>';
						$.each(
										rtList,
										function(index) {
											var rt_obj = rtList[index];
											table += "<tr height='20px'>";
											table += "<td>"
													+ (index + 1)	+"</td>";
											table += "<td  ><div class='rt_name'>" + rt_obj.eq_name	
													+ "</div>"+ "<input type='hidden' name='rt_code' class='rt_code'value='"+rt_obj.rt_code+"'></td>";
											table += "<td >" + rt_obj.rt_m_name + "</td>";
											table += "<td>" + rt_obj.rt_sdate + "</td>";									
											table += "<td>" + rt_obj.rt_edate  + "</td>";

											table += "<td ><button class='mod'><img  src='/common/img/ok.png'></button></td>"
													+ "<td><button class='delete'><img src='/common/img/delete.png'></button></td></tr>";
										});

						table += "</table>";
						$("#tab_container").empty();
						$("#tab_container").append(table);
						rt_view();
						del_click();

					},
					error : function(err) {
						alert(err + "--->오류발생");
					}
				});
	};
	
	
	function eq_ca_search() {
		$("#eq_ca_search").change(function() {
			eq_ca_code = $("#eq_ca_search").val();
			alert(eq_ca_search);
			rt_list_ajax(eq_ca_code);

		});
	}
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
	
	function rt_view(){
		$(".rt_name").click(function() {
			alert("click");
			var rt_code = $(this).parent().children('.rt_code').val(); 
			alert(rt_code);
		
	/* 		var eq_mod = "/equipmentView.eq?eq_code=" + eq_code;
			
			window.open(eq_mod, "_blank", "width=450, height=400, toolbar=no,location=no, menubar=no, scrollbars=no, resizable=yes" ); */
		});
	}
				
	function rt_list_all(){
		$("#rt_list_all").click(function(){
			alert("all");
			eq_ca_code=0;
			rt_list_ajax(eq_ca_code);
			
			
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

</style>
</head>



<body>
	<div class="main">
		<div class="search_box">
			<button id="rt_add">추가</button>
			<button id="rt_list_all">전체보기</button>
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
						<td width="130px">대여시작일</td>
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