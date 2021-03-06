$(document).ready(function() {
	req_search();
	add_click();
	res_search();
	req_search();
	req_list_ajax(res_code, req_code, cur_page, req_limit);
	res_change();
	list_all();
	view_num_change();
	pageList(res_code, req_code, cur_page, req_limit);
	
});

var cur_page=1;
var prev_page=1;
var next_page=6;
var req_limit=20;
var res_code = 0;
var req_code= 0;


function req_search(){
	$('#search_button').click(function() {
		aleart('searchbutton');
		var search= $('#search_text').val();
		aleart(search);
	});
};
//req_list 불러오는 ajax
function req_list_ajax(res_code, req_code, cur_page, req_limit) {
	$.ajax({
		url : "/requestListSearchAjax.rq",
		dataType : "text",
		data : {
			res_code : res_code,
			req_code : req_code,
			cur_page : cur_page,
			req_limit : req_limit
		},
		success : function(req_list) {
			var reqList = eval(req_list);
			var table = "<table> <td width='30px'></td><td width='80px'></td><td width='100px'></td><td width='290px'></td><td width='100px'></td><td width='100px'></td><td></td>";
			$.each(reqList, function(index) {
				var req_obj = reqList[index];
				table += "<tr>";
				table += "<td>" + req_obj.rnum + "</td>";
				table += "<td class='no'>" + req_obj.req_code + "</td>";
				table += "<td>" + req_obj.Ca_name + "</td>";
				table += "<td class='subject'>" + req_obj.Req_subject + "</td>";
				table += "<td>" + req_obj.M_name + "</td>";
				table += "<td>" + req_obj.Req_date + "</td>";
				table += "<td><select name='req_result' class='req_result'>"
						+ "<option>" + req_obj.Res_name + "</option></td>";
				table += "</tr>";

			});

			table += "</table>";
			$("#tab_container").empty();
			$("#tab_container").append(table);
			res_ajax();
			req_view();
			req_list_css();

		},
		error : function(err) {
			alert(err + "--->오류발생req_list_ajax(res_code, req_code)");
		}
	});
};

function req_search_list_ajax(res_code, req_code, cur_page, req_limit) {
	$.ajax({
		url : "/requestListSearchAjax.rq",
		dataType : "text",
		data : {
			res_code : res_code,
			req_code : req_code,
			cur_page : cur_page,
			req_limit : req_limit
		},
		success : function(req_list) {
			var reqList = eval(req_list);
			var table = "<table> <td width='30px'></td><td width='80px'></td><td width='100px'></td><td width='290px'></td><td width='100px'></td><td width='100px'></td><td></td>";
			$.each(reqList, function(index) {
				var req_obj = reqList[index];
				table += "<tr>";
				table += "<td>" + req_obj.rnum + "</td>";
				table += "<td class='no'>" + req_obj.req_code + "</td>";
				table += "<td>" + req_obj.Ca_name + "</td>";
				table += "<td class='subject'>" + req_obj.Req_subject + "</td>";
				table += "<td>" + req_obj.M_name + "</td>";
				table += "<td>" + req_obj.Req_date + "</td>";
				table += "<td><select name='req_result' class='req_result'>"
						+ "<option>" + req_obj.Res_name + "</option></td>";
				table += "</tr>";

			});

			table += "</table>";
			$("#tab_container").empty();
			$("#tab_container").append(table);
			res_ajax();
			req_view();
			req_list_css();

		},
		error : function(err) {
			alert(err + "--->오류발생req_list_ajax(res_code, req_code)");
		}
	});
};
//전체보기 버튼 클릭시 전체 List불러옴
function list_all() {
	$('#list_all').click(function() {
		req_list_ajax(res_code, req_code, cur_page, req_limit);
		pageList(res_code, req_code, cur_page, req_limit);
	});
}
// 요청결과에selector 변경에 따라 실시간 list 재호출
function res_search() {
	$("#res_search").change(function() {
		var req_code = $("#req_search").val();
		var res_code = $(this).val();
		req_list_ajax(res_code, req_code, cur_page, req_limit);
		pageList(res_code, req_code, cur_page, req_limit);

	});
}

// 요청카테고리 selector 변경에 따라 검색된 실시간 list재 호출 / select box로 search된 list 보기
function req_search() {
	$("#req_search").change(function() {
		var res_code = $("#res_search").val();
		var req_code = $(this).val();
		req_list_ajax(res_code, req_code, cur_page, req_limit);
		pageList(res_code, req_code, cur_page, req_limit);
	});
}


//추가 버튼 클릭하면 추가 창 load하면서  닫기 버튼으로 바뀜
function add_click() {
	$("#add").click(function() {
		add_load();
		exit_button_load();
		
		//m_name_ajax();
	});
};

//추가 버튼 삭제하고 닫기버튼 추가 .
function exit_button_load() {

	$("#list_all").before('<button id="exit">닫기</button>');
	$("#add").remove();
	exit_click();
}

//닫기버튼 옆에 삭제 버튼 추가
function delete_button_load() {
	$("#delete").remove();
	$("#exit").before('<button id="delete">삭제</button>');

}

//닫기버튼 누르면 loading 된 창 닫기 
function exit_click() {
	$("#exit").click(function() {
		exit_remove_add();
		$("#delete").remove();
	});
}

//loading 된 창 닫고  닫기버튼 삭제하고 추가 버튼 돌려놓기
function exit_remove_add() {
	content_body_remove();
	$("#exit").before('<button id="add">추가</button>');
	$("#exit").remove();
	add_click();
}

// 추가 창 불러오기 
function add_load() {
	$("#content_body").load("/requestAdd.rq");
	//$("#content_body").slideDown(100);
	// div_slide();
	
}

// div 비우기 
function content_body_remove() {
	$("#content_body").empty();
	//$("#content_body").slideUp(1000);
	// div_slide();
}


////요청 제목 클릭하며 요청 상세보기 loading 
function req_view() {
	$(".subject").click(function() {
		$("#exit").remove();
		var num = $(this).parent().children('.no').html();
		var loc = "/requestView.rq?num=" + num;
		$("#content_body").load(loc);
		// div_slide();
		exit_button_load();
		delete_button_load();
		view_delete();
	});
}

//// 리스트 div 위치 조정
function div_slide() {
	var req_body_div = $("#content_body");
	var req_list_div = $("#content_list");
	var body_height = req_body_div.height();
	req_list_div.animate({
		top : 0
	}, 200, "easeOutQuint");
};







function req_list_css(){
	$('.subject').css('text-align','left');
	
	$('.subject').css('cursor','pointer');
	$('.subject').on("mouseover",function(){
		$(this).css('font-weignt','bold');
		$(this).css('font-color','red');
	});
		
}

// 요청 결과 실시간 변경 Ajax 사용
function res_change() {
	$('.req_result').change(function() {
		var res_code = $(this).val();
		var req_code = $(this).parent().parent().children('.no').html();
		$.ajax({
			type : "POST",
			data : {
				res_code : res_code,
				req_code : req_code
			},
			url : "/requestResult.rq",
			success : function(result) {
				alert("성공");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("Status: " + textStatus);
			},
			timeout : 3000
		});
	});
};

//상세보기 창에서 요청 삭제
function view_delete() {
	$("#delete").click(function() {
		alert("delete");
		var num = $('#no').html();
		alert(num);
		$.ajax({
			type : "POST",
			data : {
				req_code : num
			},
			url : "/requestDeleteAction.rq",
			success : function(result) {
				alert("성공");
				$("#delete").remove();
				exit_remove_add();
				req_list_ajax();

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("Status: " + textStatus);
			},
			timeout : 3000
		});

	});
};

//요청결과 select box 추가
function res_ajax() {
	$.ajax({
		url : "/resultListAjax.rq",
		dataType : "text",
		success : function(res_list) {
			var resList = eval(res_list);
			var select = "";

			$.each(resList, function(index) {
				var res_obj = resList[index];

				select += '<option value="' + res_obj.res_code + '">'
						+ res_obj.res_name + '</option>';
			});
			select += "</select>";
			res_change();
			$('.req_result').append(select);

		},
		error : function(err) {
			alert(err + "--->오류발생res_ajax()");
		}
	});
};

//요청자 이름 (m_name) selectbox 불러오기 
function m_name_ajax() {
	$.ajax({
		url : "/memberNameAjax.mb",
		dataType : "text",
		success : function(m_list) {
			var memList = eval(m_list);
			var select = "<select name='m_code'>";

			$.each(memList, function(index) {
				var m_obj = memList[index];

				select += '<option value="' + m_obj.m_code + '">'
						+ m_obj.m_name + '</option>';

			});
			select += "</select>";
			// $('.res').empty();
			$('.member').append(select);

		},
		error : function(err) {
			alert(err + "--->오류발생m_name_ajax()");
		}
	});
};

function pageList(res_code, req_code, cur_page, req_limit){
	$.ajax({
		url : "/requestPage.rq",
		dataType : "text",
		data : {
			res_code : res_code,
			req_code : req_code,
			cur_page : cur_page,
			req_limit : req_limit
		},
		
		success : function(page_obj) {
			var pageObj =  eval('('+page_obj+')');
			//alert(cur_page+"prev_page"+prev_page);
			prev_page=pageObj.start_page-1;
			next_page = pageObj.end_page+1;
			//alert(cur_page+"prev_page::"+prev_page+"next_page::"+next_page);
			var page_section = ""; 
			if(prev_page>1){
				page_section += '<input type="button" class="prev_page" id="prev_page"  value="[이전]">';
			}
			for(var i=pageObj.start_page;i<pageObj.end_page+1; i++){
					page_section += '<input type="button" class="page_num" id="page'+i+'" value='+i+'>';	
				}
				
			if(next_page<pageObj.total_page){
				page_section += '<input type="button" class="next_page" id="next_page" value="[다음]">';
			}
				//alert("page_section:"+page_section);
				$("#page_section").empty();
			$("#page_section").append(page_section);
			prev_page_click();
			next_page_click();
			page_num_click();
		}
		
		
	});
}

function page_num_click(){
	$(".page_num").click(function(){
		var page_num = $(this).val();
		cur_page=page_num;		
		//alert("click page_num ->> curPage: "+cur_page);
		
		req_list_ajax(res_code, req_code, cur_page, req_limit);
		pageList(res_code, req_code, cur_page, req_limit);
		
	});
	
}
function prev_page_click(){
	$("#prev_page").click(function(){
		//alert("prev_page");
		$("#page_section").empty();
		pageList(res_code, req_code, cur_page, req_limit);
		cur_page=prev_page;
		//alert(" prev_page_click("+cur_page);
		req_list_ajax(res_code, req_code, cur_page, req_limit);
	});
}
function next_page_click(){
	$("#next_page").click(function(){
		//alert("next_page");
		$("#page_section").empty();
		pageList(res_code, req_code, cur_page, req_limit);
		cur_page=next_page;
		//alert("next_page_click"+cur_page);
		req_list_ajax(res_code, req_code, cur_page, req_limit);
	});
}
function view_num_change(){
	$("#view_num").change(function(){
		req_limit=$(this).val();
		pageList(res_code, req_code, cur_page, req_limit);
		req_list_ajax(res_code, req_code, cur_page, req_limit);
	});
	
}

