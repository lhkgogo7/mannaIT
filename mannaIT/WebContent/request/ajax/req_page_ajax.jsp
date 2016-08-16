<%@ page language="java" contentType='application/json; charset=utf-8'
	pageEncoding="UTF-8"%>
<%@ page
	import="manna.it.bean.RequestDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>
<%@ page import="java.util.Vector"%>
<%@ page import="manna.it.bean.MemberBean"%>


<%
	request.setCharacterEncoding("UTF-8");
 	RequestDAO requestDao = new RequestDAO();

	Vector<MemberBean> mem_vector = new Vector<MemberBean>();

	int cur_page = 1; //현재 페이지 초기화
	int total_req = 0; // 총 장비갯수
	int req_limit = 1; // 화면에 보일 장비 갯수
	int limit_page = 5; //페이지 숫자 최대 갯수
	int total_page = 1; // 총 요청 갯수
	
	
	int res_code=0;
	String req_code="";
	String req_search="";
	
	try {
		if (request.getParameter("cur_page") != null) {
			cur_page = Integer.parseInt(request.getParameter("cur_page"));
		}
		if(request.getParameter("req_limit")!=null){
			req_limit = Integer.parseInt(request.getParameter("req_limit"));		
			System.out.println("req_page_ajax.jsp::: req_limit:::1111"+req_limit);
		}
		

		System.out.println("req_page_ajax.jsp::: req_limit1:::22222"+req_limit);
		
		System.out.println("req_page_ajax.jsp cur_page=33333"+cur_page);
		
		if(request.getParameter("res_search")!=null){
			req_search =  request.getParameter("req_search");		
			total_req = requestDao.getRequestSearchTotal(req_search);
		}else{
		
			if( request.getParameter("res_code")!=null){
				res_code = Integer.parseInt(request.getParameter("res_code"));
				req_code= request.getParameter("req_code");
			}
			
			if(res_code==400){
				total_req =requestDao.getRequestTotal(req_code);
			}else if(res_code==0){
				total_req = requestDao.getRequestTotal();
			}else{
				total_req = requestDao.getRequestTotal(res_code,req_code);
			}
		}
		
		
		
		System.out.println("total_req444444" + total_req);
		total_page = total_req / req_limit; //전체 페이지 갯수
		if (total_req % req_limit != 0)
			total_page++;

		int start_page = ((cur_page - 1) / limit_page) * limit_page + 1; //페이지 시작번호
		int end_page = start_page + limit_page - 1; //페이지 끝번호 
		if (end_page > total_page)
			end_page = total_page;

		JSONObject page_obj = new JSONObject();
		page_obj.put("total_req", total_req);
		page_obj.put("req_limit", req_limit);
		page_obj.put("start_page", start_page);
		page_obj.put("end_page", end_page);
		page_obj.put("total_page", total_page);
		System.out.println("page_obj" + page_obj);

		out.print(page_obj);

	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>