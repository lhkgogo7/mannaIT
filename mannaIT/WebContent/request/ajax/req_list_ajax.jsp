<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.RequestDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.RequestBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	RequestDAO requestDao = new RequestDAO();

	Vector<RequestBean> req_vector = new Vector<RequestBean>();
	JSONArray req_list = new JSONArray();
	
	try{
		int res_code=0;
		String req_code="";
		int cur_page=1; //현재페이지 기본값설정
		int req_limit=1; 
		String req_search="";
		if(request.getParameter("cur_page")!=null){ //현재페이지 넘어오면 
			cur_page= Integer.parseInt(request.getParameter("cur_page"));
			System.out.println("cur_page== >"+cur_page);
		
		}
		if(request.getParameter("req_limit")!=null){
			req_limit = Integer.parseInt(request.getParameter("req_limit"));		
			System.out.println("req_list_ajax.jsp::: req_limit:::"+req_limit);
		}
		
		int start_row = (cur_page-1)*req_limit+1;
		int end_row = cur_page*req_limit;
		
		if( request.getParameter("req_search")!=null){
			req_search =  request.getParameter("req_search");	
			System.out.println("req_search");
			req_vector = requestDao.getRequestSearchList(req_search,start_row,end_row);
			
		}else{
			if( request.getParameter("res_code")!=null){
				res_code = Integer.parseInt(request.getParameter("res_code"));
				req_code= request.getParameter("req_code");
			}
			
			
			System.out.println("getRequestList("+res_code+","+req_code+")");
			
			if(res_code==400){
				req_vector = requestDao.getRequestList(req_code,start_row,end_row);
			}else if(res_code==0){
				req_vector = requestDao.getRequestList(start_row,end_row);
			}else{
				req_vector = requestDao.getRequestList(res_code,req_code,start_row,end_row);
			}
		}
		System.out.println("req_vector"+req_vector);
		for(int i=0;i<req_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("rnum", req_vector.get(i).getRnum());
			obj.put("req_code", req_vector.get(i).getReq_code());
			obj.put("Ca_name", req_vector.get(i).getCa_name());
			obj.put("M_name", req_vector.get(i).getM_name());
			obj.put("Req_subject", req_vector.get(i).getReq_subject());
			obj.put("Req_content", req_vector.get(i).getReq_content());
			obj.put("Req_date", req_vector.get(i).getReq_date_s());
			obj.put("Req_report", req_vector.get(i).getReq_report());
			obj.put("Res_name", req_vector.get(i).getRes_name());
			System.out.println("obj"+i+obj);
			req_list.add(obj);
			
		}
		out.print("req_list:"+req_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>
