<%@ page language="java" contentType='application/json; charset=utf-8' pageEncoding="UTF-8"%>
    <%@ page import="manna.it.bean.MemberDAO,org.json.simple.JSONArray,org.json.simple.JSONObject"%>   
<%@ page import="java.util.Vector"%>
    <%@ page import="manna.it.bean.MemberBean"%>


<%

	request.setCharacterEncoding("UTF-8");
	MemberDAO memberDao = new MemberDAO();

	Vector<MemberBean> mem_vector = new Vector<MemberBean>();
	JSONArray mem_list = new JSONArray();
	
	try{
		int dep_code=0;
		int pos_code=0;
		if( request.getParameter("dep_code")!=null){
			dep_code = Integer.parseInt(request.getParameter("dep_code"));
			pos_code = Integer.parseInt(request.getParameter("pos_code"));
		}
		
		System.out.println("getMemberList("+dep_code+","+pos_code+")");
		
		if(dep_code==0){
			if(pos_code==0){
				mem_vector = memberDao.getMemberList();
			}else{
				mem_vector = memberDao.getMemberList(pos_code);
			}
		}else if(pos_code==0){
			mem_vector = memberDao.getMemberList(dep_code);
		}else{
			mem_vector = memberDao.getMemberList(dep_code,pos_code);
		}
		System.out.println("mem_vector"+mem_vector);
		for(int i=0;i<mem_vector.size(); i++){
			JSONObject obj= new JSONObject();
			obj.put("m_code", mem_vector.get(i).getM_code());
			obj.put("m_name", mem_vector.get(i).getM_name());
			obj.put("m_depname", mem_vector.get(i).getM_depname());
			obj.put("m_depcode", mem_vector.get(i).getM_depcode());
			obj.put("m_extension", mem_vector.get(i).getM_extension());
			obj.put("m_phone", mem_vector.get(i).getM_phone());
			obj.put("m_mobile", mem_vector.get(i).getM_mobile());
			obj.put("m_mail", mem_vector.get(i).getM_mail());
			obj.put("m_posname", mem_vector.get(i).getM_posname());
			
			obj.put("m_poscode", mem_vector.get(i).getM_poscode());
			obj.put("m_id", mem_vector.get(i).getM_id());
			obj.put("m_pwd", mem_vector.get(i).getM_pwd());
			System.out.println("obj"+i+obj);
			mem_list.add(obj);
			
		}
		out.print("mem_list:"+mem_list);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>
