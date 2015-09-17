<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.RequestDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.RequestBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	RequestDAO requestDao = new RequestDAO();

	Vector<RequestBean>m_vector = new Vector<RequestBean>();
	JSONArray m_list = new JSONArray();
	
	try{
		System.out.println("getMemberList()");
		m_vector = requestDao.getMemberList();
		System.out.println("res_vector"+m_vector);
		for(int i=0;i<m_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("m_code", m_vector.get(i).getM_code());
			obj.put("m_name", m_vector.get(i).getM_name());
			System.out.println("obj"+i+obj);
			m_list.add(obj);
			
		}
		out.print("m_list:"+m_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>