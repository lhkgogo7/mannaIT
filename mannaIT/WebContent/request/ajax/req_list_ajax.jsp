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
		if( request.getParameter("res_code")!=null){
			res_code = Integer.parseInt(request.getParameter("res_code"));
			req_code= request.getParameter("req_code");
		}
		
		System.out.println("getRequestList("+res_code+","+req_code+")");
		
		if(res_code==400){
			req_vector = requestDao.getRequestList(req_code);
		}else if(res_code==0){
			req_vector = requestDao.getRequestList();
		}else{
			req_vector = requestDao.getRequestList(res_code,req_code);
		}
		System.out.println("req_vector"+req_vector);
		for(int i=0;i<req_vector.size(); i++){
			JSONObject obj= new JSONObject();
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
