<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.EquipmentDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.EquipmentBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	EquipmentDAO equipmentDao = new EquipmentDAO();

	Vector<EquipmentBean> eq_vector = new Vector<EquipmentBean>();
	JSONArray eq_list = new JSONArray();
	
	try{
		int eq_ca_code=0;
		System.out.println("getEquipmentList("+request.getParameter("eq_ca_code"));
		if( request.getParameter("eq_ca_code")!=null){
			System.out.println("getEquipmentList("+Integer.parseInt(request.getParameter("eq_ca_code")));
			eq_ca_code = Integer.parseInt(request.getParameter("eq_ca_code"));
			
		}
		System.out.println("dsssssgetEquipmentList("+eq_ca_code+")");
		
		if(eq_ca_code==0){
			eq_vector = equipmentDao.getEquipmentList();
		}else{
			eq_vector = equipmentDao.getEquipmentList(eq_ca_code);
		}
		
		System.out.println("eq_vector"+eq_vector);
		for(int i=0;i<eq_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("eq_code", eq_vector.get(i).getEq_code());
			obj.put("eq_name", eq_vector.get(i).getEq_name());
			obj.put("manufacturer", eq_vector.get(i).getManufacturer());
			obj.put("eq_ca_code", eq_vector.get(i).getEq_ca_code());
			obj.put("eq_ca_name", eq_vector.get(i).getEq_ca_name());			
			obj.put("eq_date_s", eq_vector.get(i).getEq_date_s());
			System.out.println("obj"+i+obj);
			eq_list.add(obj);
			
		}
		out.print("eq_list:"+eq_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>
