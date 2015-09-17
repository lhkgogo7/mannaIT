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
		$(document).ready(function(){
			 add_click();
			 eq_list_ajax();
			 eq_ca_search();
			 
			 $(".delete").click(function(){
				var num = $(this).parent().parent().children('.no').html();
				var loc ="/equipmentDeleteAction.eq?num="+num;
				location.href(loc);
			});
			 $(".modify").click(function(){
				 var num = $(this).parent().parent().children('.no').html();
				var loc ="/equipmentModifyView.eq?num="+num;
				location.href(loc); 
			 });
			$(".name").click(function(){
				var num = $(this).parent().children('.no').html();
				var loc ="/equipmentView.eq?num="+num;
				location.href(loc); 
			});
			
		});
		
		function add_click(){
			
			$("#eq_add").click(function(){
				alert('dd');
				add_load();
			});
			
			
		};
		function add_load(){
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
		function eq_list_ajax(eq_ca_code) {
			$.ajax({
				url : "/equipmentListAjax.eq",
				dataType : "text",
				data : {
					eq_ca_code : eq_ca_code
				},
				success : function(eq_list) {
					var eqList = eval(eq_list);
					var table = '<table class="m_table"><tr class="title">'
								+'<td>No</td><td>장비코드</td><td>장비명</td><td >제조사</td><td>장비분류</td><td>날짜</td><td>수정</td><td>삭제</td>'
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
						table += "<td ><button class='mod'><img  src='/common/img/ok.png'></button></td>"
									+"<td><button class='delete'><img src='/common/img/delete.png'></button></td></tr>";
					});

					table += "</table>";
					$("#tab1").empty();
					$("#tab1").append(table);
					
					eq_delete();
					//mem_modify();
					//pos_ajax();
					//dep_ajax();
				},
				error : function(err) {
					alert(err + "--->오류발생");
				}
			});
		};
		function eq_delete(){
			$(".delete").click(function(){
				var eq_code = $(this).parent().parent().children().children('.eq_code').val();
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
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("Status: " + textStatus);
					},
					timeout : 3000
				});
			});
			
		};
		function eq_modify(){
			
			$(".mod").click(function(){
				alert("modify");
				var eq_code = $(this).parent().parent().children().children('.eq_code').val();
				
				var eq_name = $(this).parent().parent().children().children('.eq_name').val();
				var manufacturer = $(this).parent().parent().children().children('.manufacturer').val();
				var eq_date = $(this).parent().parent().children().children('.eq_date').val();
				var eq_ca_code = $(this).parent().parent().children().children('.eq_ca_code').val();
				
				alert(eq_code+"/"+eq_name+"/"+manufacturer+"/"+eq_date+"/"+m_phone+"/"+  m_mobile+"/"+m_poscode +"/"+m_mail +"/"+m_id+"/"+m_pwd  );
			
			
					$.ajax({
						type : "POST",
						data : {
							m_code : m_code,
							m_name : m_name,
							m_depcode : m_depcode,
							m_extension : m_extension,
							m_phone : m_phone,
							m_mobile : m_mobile,
							m_poscode : m_poscode,
							m_mail : m_mail,
							m_id : m_id,
							m_pwd : m_pwd					
						},
						url : "/memberModifyAction.mb",
						success : function(result) {				
							mem_list_ajax();
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							console.log("Status: " + textStatus);
						},
						timeout : 3000
				
				});
			});
			
		};
		</script>
		
		<style type="text/css">
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
				<c:forEach var="el" items="<%=Equipment.getEquipmentCategoryList()%>"
					varStatus="status">
					<option value="${el.eq_ca_code}">${el.eq_ca_name}</option>
				</c:forEach>
			</select>
			
	</div>
	<div id="content_body" class="content_body"></div>
	<div id="content_list">
	<%-- <table>
				<tr id = "title">
					<td width="25px">No</td>
					<td width="150px">장비코드</td>
					<td width="200px">장비명</td>
					<td width="100px">제조사</td>
					<td width="100px">장비분류</td>
					<td colspan="1" width="150px">날짜</td>
					<td>수정</td>
					<td>삭제 </td>
				</tr>
				<c:forEach var="co" items="<%=Equipment.getEquipmentList()%>" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td class="no" >${co.num}</td>
						<td class="name">${co.eq_name}</td>
						<td>${co.manufacturer}</td>
						<td>${co.eq_category}</td>
						<td>${co.date}</td>
						
					
						<td><button class="modify"><img src="/common/img/ok.png"></button></td>
							<td><button class="delete"><img src="/common/img/delete.png"></button></td>
					</tr>
				</c:forEach> 
	

			</table> --%>
			<div id="tab_container"></div>
			<div id="tab1" class="tab_content"></div>
	</div>
</div>
</body>
</html>