<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.MemberDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.MemberBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	MemberDAO memberDao = new MemberDAO();

	Vector<MemberBean> pos_vector = new Vector<MemberBean>();
	JSONArray pos_list = new JSONArray();
	
	try{
		System.out.println("getResultList()");
		pos_vector = memberDao.getPositionList();
		System.out.println("pos_vector"+pos_vector);
		for(int i=0;i<pos_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("m_poscode", pos_vector.get(i).getM_poscode());
			obj.put("m_posname", pos_vector.get(i).getM_posname());
			System.out.println("obj"+i+obj);
			pos_list.add(obj);
			
		}
		out.print("pos_list:"+pos_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>