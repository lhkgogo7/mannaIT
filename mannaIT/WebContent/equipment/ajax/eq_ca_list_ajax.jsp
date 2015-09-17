<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.EquipmentDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.EquipmentBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	EquipmentDAO eqDao = new EquipmentDAO();

	Vector<EquipmentBean> eq_vector = new Vector<EquipmentBean>();
	JSONArray eq_list = new JSONArray();
	
	try{
		System.out.println("getEquipmentCategoryList()");
		eq_vector = eqDao.getEquipmentCategoryList();
		System.out.println("eq_vector"+eq_vector);
		for(int i=0;i<eq_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("eq_ca_code", eq_vector.get(i).getEq_ca_code());
			obj.put("eq_ca_name", eq_vector.get(i).getEq_ca_name());
			System.out.println("obj"+i+obj);
			eq_list.add(obj);
			
		}
		out.print("eq_list:"+eq_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>