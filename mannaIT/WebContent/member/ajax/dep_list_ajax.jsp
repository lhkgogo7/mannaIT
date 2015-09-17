<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.MemberDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.MemberBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	MemberDAO memberDao = new MemberDAO();

	Vector<MemberBean> dep_vector = new Vector<MemberBean>();
	JSONArray dep_list = new JSONArray();
	
	try{
		System.out.println("getResultList()");
		dep_vector = memberDao.getdepartmentList();
		System.out.println("dep_vector"+dep_vector);
		for(int i=0;i<dep_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("m_depcode", dep_vector.get(i).getM_depcode());
			obj.put("m_depname", dep_vector.get(i).getM_depname());
			System.out.println("obj"+i+obj);
			dep_list.add(obj);
			
		}
		out.print("dep_list:"+dep_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>