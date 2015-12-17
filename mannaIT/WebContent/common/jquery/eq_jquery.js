$(document).ready(function() {
	add_click();
	eq_list_ajax();
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
function eq_list_ajax(eq_ca_code) {
	$
			.ajax({
				url : "/equipmentListAjax.eq",
				dataType : "text",
				data : {
					eq_ca_code : eq_ca_code
				},
				success : function(eq_list) {
					var eqList = eval(eq_list);
					var table = '<table class="m_table">'
							+ '<tr class="title"><td width="20px"></td><td width="140px"></td>	<td width="180px"></td><td width="150px"></td><td width="130px"></td><td width="130px"></td><td width="40px"></td><td width="40px"></td></tr>';
					$
							.each(
									eqList,
									function(index) {
										var eq_obj = eqList[index];
										table += "<tr>";
										table += "<td>"
												+ (index + 1)
												+ "<input type='hidden' name='eq_code' class='eq_code1'value='"
												+ eq_obj.eq_code + "'></td>";
										table += "<td class='eq_code'>"
												+ eq_obj.eq_code + "</td>";
										table += "<td ><input class='eq_name' type='text' value='"
												+ eq_obj.eq_name + "'/></td>";
										table += "<td><input class='manufacturer' type='text' value='"
												+ eq_obj.manufacturer
												+ "'/></td>";
										table += "<td><select name='eq_ca_list' class='eq_ca_list'>"
												+ "<option value="
												+ eq_obj.eq_ca_code
												+ " selected>"
												+ eq_obj.eq_ca_name
												+ "</option></td>";
										table += "<td ><input class='eq_date' type='text' value='"
												+ eq_obj.eq_date_s + "'/></td>";
										/*
										 * table += "<td>" + eq_obj.eq_picture + "</td>";
										 */

										table += "<td ><button class='mod'><img  src='/common/img/ok.png'></button></td>"
												+ "<td><button class='delete'><img src='/common/img/delete.png'></button></td></tr>";
									});

					table += "</table>";
					$("#tab_container").empty();
					$("#tab_container").append(table);

					eq_delete();
					// eq_modify();
					eq_view();
					eq_category_ajax();
					eq_front_modify();
					// pos_ajax();
					// dep_ajax();
				},
				error : function(err) {
					alert(err + "--->오류발생");
				}
			});
};
function eq_delete() {
	$(".delete").click(
			function() {
				var eq_code = $(this).parent().parent().children().children(
						'.eq_code').val();
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
function eq_modify() {
	$(".mod")
			.click(
					function() {
						alert("modify");
						var eq_code = $(this).parent().parent().children()
								.children('.eq_code').val();

						alert(eq_code);
						var eq_mod = "/equipmentView.eq?eq_code=" + eq_code;

						window
								.open(
										eq_mod,
										"_blank",
										"width=450, height=400, toolbar=no,location=no, menubar=no, scrollbars=no, resizable=yes");

					});

};
function eq_front_modify() {

	$(".mod").click(
			function() {
				// alert("modify eq_front_modify(");
				/*
				 * var eq_code =
				 * $(this).parent().parent().children().children('.eq_code').val();
				 */
				var eq_code = $(this).parent().parent().children('.eq_code')
						.html();
				var eq_name = $(this).parent().parent().children().children(
						'.eq_name').val();
				var eq_manufacturer = $(this).parent().parent().children()
						.children('.manufacturer').val();
				var eq_date = $(this).parent().parent().children().children(
						'.eq_date').val();
				var eq_ca_code = $(this).parent().parent().children().children(
						'.eq_ca_list').val();

				// alert(eq_code+"/"+eq_name+"/"+eq_manufacturer+"/"+eq_date+"/"+eq_ca_code);

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

function eq_view() {
	$(".eq_code")
			.click(
					function() {
						alert("click");
						var eq_code = $(this).parent().children('.eq_code')
								.html();
						alert(eq_code);

						var eq_mod = "/equipmentView.eq?eq_code=" + eq_code;

						window
								.open(
										eq_mod,
										"_blank",
										"width=450, height=400, toolbar=no,location=no, menubar=no, scrollbars=no, resizable=yes");
					});
}

function eq_list_all() {
	$("#eq_list_all").click(function() {
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
			// dep_change();
			$('.eq_ca_list').append(select);

		},
		error : function(err) {
			alert(err + "--->오류발생res_ajax()");
		}
	});
};