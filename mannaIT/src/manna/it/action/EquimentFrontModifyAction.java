package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import manna.it.bean.EquipmentBean;
import manna.it.bean.EquipmentDAO;

public class EquimentFrontModifyAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		
		
		boolean result = false;

		
		EquipmentDAO equipmentDao = new EquipmentDAO();
		EquipmentBean equipment = new EquipmentBean();
		/*
		Date date = null;
		date = equipmentDao.stringToDateConversion(request.getParameter("eq_date"));*/
		
		try{
			equipment.setEq_code(Integer.parseInt(request.getParameter("eq_code")));
			equipment.setEq_name(request.getParameter("eq_name"));
			System.out.println(request.getParameter("eq_name"));
			equipment.setManufacturer(request.getParameter("eq_manufacturer"));
			
			equipment.setEq_ca_code(Integer.parseInt(request.getParameter("eq_ca_code")));
			equipment.setEq_date_s(request.getParameter("eq_date"));
			result = equipmentDao.modifyFrontEquipment(equipment);
			if(result==false){
				System.out.println("수정실패");
				return null;
			}
			
			System.out.println("게시판 수정완료");
			
			forward.setRedirect(true);
			forward.setPath("/equipment.eq");
			return forward;
		}catch(Exception ex){
			ex.printStackTrace();
		}
			
		
		
		return null;
		
		
	}
	

}
