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
		int cur_page=1; //현재페이지 기본값설정
		int eq_limit=1; 
		
		if(request.getParameter("cur_page")!=null){ //현재페이지 넘어오면 
			cur_page= Integer.parseInt(request.getParameter("cur_page"));
			System.out.println("cur_page== >"+cur_page);
		
		}
		if( request.getParameter("eq_ca_code")!=null){
			
			eq_ca_code = Integer.parseInt(request.getParameter("eq_ca_code"));
			System.out.println("getEquipmentList(eq_ca_code-->"+eq_ca_code);
		}
		//System.out.println("ajax request getEquipmentList(page:"+cur_page+",limit"+eq_limit);
		if(request.getParameter("eq_limit")!=null){
			eq_limit = Integer.parseInt(request.getParameter("eq_limit"));		
			System.out.println("eq_list_ajax.jsp::: eq_limit:::"+eq_limit);
		}
		
		if(eq_ca_code==0){			
			eq_vector = equipmentDao.getEquipmentList(cur_page,eq_limit);
			System.out.println("ajax.jsp :: equipmentDao.getEquipmentList(cur_page,limit), eq_ca_code=0");
		}else{
			eq_vector = equipmentDao.getEquipmentList(eq_ca_code,cur_page,eq_limit);
		}
		
		System.out.println("eq_vector"+eq_vector);
		for(int i=0;i<eq_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("eq_rnum", eq_vector.get(i).getEq_rnum());
			obj.put("eq_code", eq_vector.get(i).getEq_code());
			obj.put("eq_name", eq_vector.get(i).getEq_name());
			obj.put("manufacturer", eq_vector.get(i).getManufacturer());
			obj.put("eq_ca_code", eq_vector.get(i).getEq_ca_code());
			obj.put("eq_ca_name", eq_vector.get(i).getEq_ca_name());			
			obj.put("eq_date_s", eq_vector.get(i).getEq_date_s());
			obj.put("eq_picture", eq_vector.get(i).getEq_picture());
			obj.put("eq_user", eq_vector.get(i).getEq_user());
			//System.out.println("obj"+i+obj);
			eq_list.add(obj);
			
		}
		out.print("eq_list:"+eq_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>
