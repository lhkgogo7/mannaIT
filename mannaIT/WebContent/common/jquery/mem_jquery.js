var cur_page=1;
var prev_page=1;
var next_page=6;
var mem_limit=20;
var dep_code = 0;
var pos_code = 0;
$(document).ready(function(){
	
	mem_list_ajax(dep_code, pos_code,cur_page, mem_limit) ;
	mem_add();
	dep_search();
	pos_search();
	pageList(dep_code, pos_code,cur_page, mem_limit);
	view_num_change();
	
});
//mem_list 불러오는 ajax
function mem_list_ajax(dep_code, pos_code, cur_page, mem_limit) {
	$.ajax({
		url : "/memberListSearchAjax.mb",
		dataType : "text",
		data : {
			dep_code : dep_code,
			pos_code : pos_code,
			cur_page : cur_page,
			mem_limit : mem_limit
		},
		success : function(mem_list) {
			var memList = eval(mem_list);
			var table = '<table class="m_table"><tr class="title">'
						+'<td>No</td><td>이름</td><td>부서명</td><td>내선번호</td><td>전화번호</td><td >모바일</td><td>메일</td><td>직위</td><td>아이디</td><td>패스워드</td><td>수정</td><td>삭제</td>'
						+'</tr>';
			$.each(memList, function(index) {
				var mem_obj = memList[index];
				table += "<tr>";
				table += "<td>" + mem_obj.rnum + "<input type='hidden' name='m_code' class='m_code'value='"+mem_obj.m_code+"'></td>";
				/* table += "<td class='no'>" + mem_obj.m_code + "</td>"; */
				table += "<td ><input class='m_name' type='text' value='" + mem_obj.m_name + "'/></td>";
				table += "<td><select name='m_depcode' class='m_deplist'>" 
					+"<option value="+ + mem_obj.m_depcode +" selected>"+ mem_obj.m_depname + "</option></td>";
				table += "<td><input class='m_extension' type='text' value='" + mem_obj.m_extension + "'/></td>";
				table += "<td><input class='m_phone'type='text' value='" + mem_obj.m_phone + "'/></td>";
				table += "<td><input class='m_mobile'type='text' value='" + mem_obj.m_mobile + "'/></td>";
				table += "<td><input class='m_mail'type='text' value='" + mem_obj.m_mail + "'/></td>";
				table += "<td><select name='m_poscode' class='m_poslist'>" 
							+"<option value="+ mem_obj.m_poscode+" selected>"+ mem_obj.m_posname + "</option></td>";
							
				table += "<td><input class='m_id' type='text' value='" + mem_obj.m_id + "'/></td>";
				table += "<td><input class='m_pwd' type='password' value='" + mem_obj.m_pwd + "'/></td>";
				table += "<td ><button class='mod'><img  src='/common/img/ok.png'></button></td>"
							+"<td><button class='delete'><img src='/common/img/delete.png'></button></td></tr>";
			});

			table += "</table>";
			$("#tab_container").empty();
			$("#tab_container").append(table);
			//res_ajax();
			//mem_view();
			mem_delete();			
			mem_modify();
			pos_ajax();
			dep_ajax();
		},
		error : function(err) {
			alert(err + "--->오류발생");
		}
	});
};
function mem_modify(){
	
	$(".mod").click(function(){

		//alert("modify");

		alert("modify");
		var m_code = $(this).parent().parent().children().children('.m_code').val();
		
		var m_name = $(this).parent().parent().children().children('.m_name').val();
		var m_depcode = $(this).parent().parent().children().children('.m_deplist').val();
		var m_extension = $(this).parent().parent().children().children('.m_extension').val();
		var m_phone = $(this).parent().parent().children().children('.m_phone').val();
		var m_mobile = $(this).parent().parent().children().children('.m_mobile').val();
		var m_poscode = $(this).parent().parent().children().children('.m_poslist').val();
		var m_mail = $(this).parent().parent().children().children('.m_mail').val();
		var m_id = $(this).parent().parent().children().children('.m_id').val();
		var m_pwd = $(this).parent().parent().children().children('.m_pwd').val();
		//alert(m_code+"/"+m_name+"/"+m_depcode+"/"+m_extension+"/"+m_phone+"/"+  m_mobile+"/"+m_poscode +"/"+m_mail +"/"+m_id+"/"+m_pwd  );
		alert(m_code+"/"+m_name+"/"+m_depcode+"/"+m_extension+"/"+m_phone+"/"+  m_mobile+"/"+m_poscode +"/"+m_mail +"/"+m_id+"/"+m_pwd  );
	
	
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
					var pos_search = $("#pos_search").val();
					var dep_search = $("#dep_search").val();
					alert(pos_serch+","+dep_search);
					mem_list_ajax(pos_search, dep_search);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("Status: " + textStatus);
				},
				timeout : 3000
		
		});
	});
	
};
function mem_delete(){
	$(".delete").click(function(){
		var m_code = $(this).parent().parent().children().children('.m_code').val();
		alert(m_code);
		$.ajax({
			type : "POST",
			data : {
				m_code : m_code
			},
			url : "/memberDeleteAction.mb",
			success : function(result) {			
				var pos_search = $("#pos_search").val();
				var dep_search = $("#dep_search").val();
				alert(pos_serch+","+dep_search);
				mem_list_ajax(pos_search, dep_search);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("Status: " + textStatus);
			},
			timeout : 3000
		});
	});
	
};
function mem_add(){
	$("#mem_add").click(function(){
		$("#content_body").load("/memberAdd.mb");
	});	
}
function checkSubmit(){
	 //alert("checkSubmit()");
	var m_name= $("#m_name");
	//alert("m_name"+m_name.val());
	var m_dep= $("#m_depcode");
	//alert("m_dep"+m_dep.val());
	var m_pos= $("#m_poscode");
	//alert("m_pos"+m_pos.val());
	if( m_name.val() == "") {
		    alert("값을 입력해 주세요.");
		    m_name.focus();
		    return false;
		  }
		  else if(m_dep.val()=="400") {
		    alert("값을 입력해 주세요.");
		    
		    return false;
		  }
		  else if(m_pos.val()=="0") {
			    alert("직위선택 필수.");
			    
			    return false;
			  }
 	else return true;
}


function pageList(dep_code, pos_code,cur_page, mem_limit){
	$.ajax({
		url : "/memberPage.mb",
		dataType : "text",
		data : {
			
			dep_code : dep_code,
			pos_code : pos_code,
			cur_page : cur_page,
			mem_limit : mem_limit
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
		
		mem_list_ajax(dep_code, pos_code,cur_page, mem_limit);
		pageList(dep_code, pos_code,cur_page, mem_limit);
		
	});
	
}
function prev_page_click(){
	$("#prev_page").click(function(){
		//alert("prev_page");
		$("#page_section").empty();
		pageList(dep_code, pos_code,prev_page,mem_limit);
		cur_page=prev_page;
		//alert(" prev_page_click("+cur_page);
		mem_list_ajax(dep_code, pos_code,cur_page, mem_limit);
	});
}
function next_page_click(){
	$("#next_page").click(function(){
		//alert("next_page");
		$("#page_section").empty();
		pageList(dep_code, pos_code,next_page,mem_limit);
		cur_page=next_page;
		//alert("next_page_click"+cur_page);
		mem_list_ajax(dep_code, pos_code, cur_page, mem_limit);
	});
}


function pos_ajax() {
		$.ajax({
			url : "/memberPositionAjax.mb",
			dataType : "text",
			success : function(pos_list) {
				var posList = eval(pos_list);
				var select = "";

				$.each(posList, function(index) {
					var pos_obj = posList[index];

					select += '<option value="' + pos_obj.m_poscode + '">'
							+ pos_obj.m_posname + '</option>';
				});
				select += "</select>";
				//pos_change();
				$('.m_poslist').append(select);

			},
			error : function(err) {
				alert(err + "--->오류발생res_ajax()");
			}
		});

	
	
};

function dep_ajax() {
	$.ajax({
		url : "/memberDepartmentjax.mb",
		dataType : "text",
		success : function(dep_list) {
			var depList = eval(dep_list);
			var select = "";

			$.each(depList, function(index) {
				var dep_obj = depList[index];

				select += '<option value="' + dep_obj.m_depcode + '">'
						+ dep_obj.m_depname + '</option>';
			});
			select += "</select>";
			//dep_change();
			$('.m_deplist').append(select);

		},
		error : function(err) {
			alert(err + "--->오류발생res_ajax()");
		}
	});
};

function view_num_change(){
	$("#view_num").change(function(){
		mem_limit=$(this).val();
		pageList(dep_code, pos_code,next_page,mem_limit);
		mem_list_ajax(dep_code, pos_code, cur_page, mem_limit);
	});
	
}


function dep_search() {
	$("#dep_search").change(function() {
		pos_code = $("#pos_search").val();
		dep_code = $(this).val();
		mem_list_ajax(dep_code, pos_code,cur_page, mem_limit);
		//alert("pos:"+pos_search+",dep:"+dep_search);
		cur_page=1;
		pageList(dep_code, pos_code,next_page,mem_limit);
	});
}
function pos_search() {
	$("#pos_search").change(function() {
		pos_code = $(this).val();
		dep_code = $("#dep_search").val();
		mem_list_ajax(dep_code, pos_code,cur_page, mem_limit);
		alert("pos:"+pos_code+",dep:"+dep_code);
		cur_page=1;
		
		pageList(dep_code, pos_code,next_page,mem_limit);

	});
}

