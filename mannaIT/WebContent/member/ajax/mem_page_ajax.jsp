
<%@ page language="java" contentType='application/json; charset=utf-8'
	pageEncoding="UTF-8"%>
<%@ page
	import="manna.it.bean.MemberDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>
<%@ page import="java.util.Vector"%>
<%@ page import="manna.it.bean.MemberBean"%>


<%
	request.setCharacterEncoding("UTF-8");
MemberDAO memberDao = new MemberDAO();

	Vector<MemberBean> mem_vector = new Vector<MemberBean>();

	int cur_page = 1; //현재 페이지 초기화
	int total_mem = 0; // 총 장비갯수
	int mem_limit = 1; // 화면에 보일 장비 갯수
	int limit_page = 5; //페이지 숫자 최대 갯수
	int total_page = 1; // 총페이지 갯수
	int dep_code=0;
	int pos_code=0;
	try {
		if (request.getParameter("cur_page") != null) {
			cur_page = Integer.parseInt(request.getParameter("cur_page"));
		}
		if(request.getParameter("mem_limit")!=null){
			mem_limit = Integer.parseInt(request.getParameter("mem_limit"));		
			System.out.println("mem_page_ajax.jsp::: mem_limit:::1111"+mem_limit);
		}
		

		System.out.println("mem_page_ajax.jsp::: mem_limit1:::22222"+mem_limit);
		
		System.out.println("mem_page_ajax.jsp cur_page=33333"+cur_page);
		
		
		if( request.getParameter("dep_code")!=null){
			dep_code = Integer.parseInt(request.getParameter("dep_code"));
			pos_code = Integer.parseInt(request.getParameter("pos_code"));
		}
		if(dep_code==0){
			if(pos_code==0){
				total_mem = memberDao.getMemberTotal();
			}else{
				total_mem = memberDao.getMemberTotal(pos_code);
			}
		}else if(pos_code==0){
			total_mem = memberDao.getMemberTotal(dep_code);
		}else{
			total_mem = memberDao.getMemberTotal(dep_code,pos_code);
		}
		
		
		System.out.println("total_mem444444" + total_mem);
		total_page = total_mem / mem_limit; //전체 페이지 갯수
		if (total_mem % mem_limit != 0)
			total_page++;

		int start_page = ((cur_page - 1) / limit_page) * limit_page + 1; //페이지 시작번호
		int end_page = start_page + limit_page - 1; //페이지 끝번호 
		if (end_page > total_page)
			end_page = total_page;

		JSONObject page_obj = new JSONObject();
		page_obj.put("total_mem", total_mem);
		page_obj.put("mem_limit", mem_limit);
		page_obj.put("start_page", start_page);
		page_obj.put("end_page", end_page);
		page_obj.put("total_page", total_page);
		System.out.println("page_obj" + page_obj);

		out.print(page_obj);

	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>