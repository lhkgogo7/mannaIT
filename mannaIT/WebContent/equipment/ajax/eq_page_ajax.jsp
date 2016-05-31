<%@ page language="java" contentType='application/json; charset=utf-8'
	pageEncoding="UTF-8"%>
<%@ page
	import="manna.it.bean.EquipmentDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>
<%@ page import="java.util.Vector"%>
<%@ page import="manna.it.bean.EquipmentBean"%>


<%
	request.setCharacterEncoding("UTF-8");
	EquipmentDAO eqDao = new EquipmentDAO();

	Vector<EquipmentBean> eq_vector = new Vector<EquipmentBean>();

	int cur_page = 1; //현재 페이지 초기화
	int total_eq = 0; // 총 장비갯수
	int eq_limit = 1; // 화면에 보일 장비 갯수
	int limit_page = 5; //페이지 숫자 최대 갯수
	int total_page = 1; // 총페이지 갯수
	int eq_ca_code=0;

	try {
		if (request.getParameter("cur_page") != null) {
			cur_page = Integer.parseInt(request
					.getParameter("cur_page"));
		}
		if(request.getParameter("eq_limit")!=null){
			eq_limit = Integer.parseInt(request.getParameter("eq_limit"));		
			System.out.println("eq_page_ajax.jsp::: eq_limit:::"+eq_limit);
		}
		
		if(request.getParameter("eq_ca_code")!=null){
			
			eq_ca_code = Integer.parseInt(request.getParameter("eq_ca_code"));		
			if(eq_ca_code!=0){
				System.out.println("eq_page_ajax.jsp:::eqDao.getEquipmentTotal(eq_ca_code)"+eq_ca_code);
				total_eq = eqDao.getEquipmentTotal(eq_ca_code);
				System.out.println("eq_page_ajax.jsp:total_eq"+total_eq);
			}else{
				total_eq = eqDao.getEquipmentTotal();
				System.out.println("eq_page_ajax.jsp:::eqDao.getEquipmentTotal(eq_ca_code)");
			}
		}
		//System.out.println("eq_page_ajax.jspcur_page="	+ cur_page);
		
		
		
		//System.out.println("total_eq" + total_eq);
		total_page = total_eq / eq_limit; //전체 페이지 갯수
		if (total_eq % eq_limit != 0)
			total_page++;

		int start_page = ((cur_page - 1) / limit_page) * limit_page + 1; //페이지 시작번호
		int end_page = start_page + limit_page - 1; //페이지 끝번호 
		if (end_page > total_page)
			end_page = total_page;

		JSONObject page_obj = new JSONObject();
		page_obj.put("total_eq", total_eq);
		page_obj.put("eq_limit", eq_limit);
		page_obj.put("start_page", start_page);
		page_obj.put("end_page", end_page);
		page_obj.put("total_page", total_page);
		//System.out.println("page_obj" + page_obj);

		out.print(page_obj);

	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>