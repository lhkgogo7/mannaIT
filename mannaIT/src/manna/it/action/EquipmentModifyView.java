package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import manna.it.bean.EquipmentBean;
import manna.it.bean.EquipmentDAO;

public class EquipmentModifyView implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		EquipmentDAO equipmentDao = new EquipmentDAO();
		EquipmentBean equipment = new EquipmentBean();
		
		int eq_code = Integer.parseInt(request.getParameter("eq_code"));
		equipment = equipmentDao.getEquipmentView(eq_code);
		
		if(equipment==null){
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기성공");
		
		request.setAttribute("ev", equipment);
		forward.setRedirect(false);
		forward.setPath("/equipment/equipment_modify.jsp");
		return forward;
		
		
	}
	

}
