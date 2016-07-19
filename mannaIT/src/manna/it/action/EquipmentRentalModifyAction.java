package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import manna.it.bean.EquipmentBean;
import manna.it.bean.EquipmentDAO;

public class EquipmentRentalModifyAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		
		
		boolean result = false;

		
		EquipmentDAO equipmentDao = new EquipmentDAO();
		System.out.println("modify 진입");
		String e_date = null;
		


		try{
			

			int rt_code=Integer.parseInt(request.getParameter("rt_code"));
			e_date = request.getParameter("e_date");

			System.out.println("rt_code::::::::"+rt_code);
			System.out.println("e_date:::::::"+e_date);
			result = equipmentDao.updateRentalDate(rt_code,e_date);
			
			if(result==false){
				System.out.println("수정실패");
				return null;
			}
			
			System.out.println("게시판 수정완료");
			
			forward.setRedirect(true);
			forward.setPath("/index.jsp?content=/rental.eq");
			return forward;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	

}
