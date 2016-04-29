<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.EquipmentDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.EquipmentBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	EquipmentDAO equipmentDao = new EquipmentDAO();

	Vector<EquipmentBean> rt_vector = new Vector<EquipmentBean>();
	JSONArray rt_list = new JSONArray();
	
	try{
		int eq_ca_code=0;
		System.out.println("getrentalList();");
		if( request.getParameter("eq_ca_code")!=null){
			System.out.println("getEquipmentList("+Integer.parseInt(request.getParameter("eq_ca_code")));
			eq_ca_code = Integer.parseInt(request.getParameter("eq_ca_code"));
			
		}
		System.out.println("dsssssgetEquipmentList("+eq_ca_code+")");
		
		if(eq_ca_code==0){
			rt_vector = equipmentDao.getRentalList();
		}
		 else{
			rt_vector = equipmentDao.getRentalList(eq_ca_code);
		} 
		
		System.out.println("rt_vector"+rt_vector);
		for(int i=0;i<rt_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("rt_code", rt_vector.get(i).getRt_code());
			obj.put("eq_name", rt_vector.get(i).getEq_name());
			obj.put("rt_m_name", rt_vector.get(i).getRt_m_name());
			obj.put("rs_name", rt_vector.get(i).getRs_name());
			obj.put("rt_sdate", rt_vector.get(i).getRt_sdate_s());			
			obj.put("rt_edate", rt_vector.get(i).getRt_edate_s());
			obj.put("rt_purpose", rt_vector.get(i).getRt_purpose());
			obj.put("rt_duedate", rt_vector.get(i).getRt_duedate_s());
			System.out.println("obj"+i+obj);
			rt_list.add(obj);
			
		}
		out.print("rt_list:"+rt_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>
