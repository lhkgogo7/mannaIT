<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.RequestDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.RequestBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	RequestDAO requestDao = new RequestDAO();

	Vector<RequestBean> res_vector = new Vector<RequestBean>();
	JSONArray res_list = new JSONArray();
	
	try{
		System.out.println("getResultList()");
		res_vector = requestDao.getRequestResultList();
		System.out.println("res_vector"+res_vector);
		for(int i=0;i<res_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("res_code", res_vector.get(i).getRes_code());
			obj.put("res_name", res_vector.get(i).getRes_name());
			System.out.println("obj"+i+obj);
			res_list.add(obj);
			
		}
		out.print("res_list:"+res_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>