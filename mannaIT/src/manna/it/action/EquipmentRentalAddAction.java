package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import manna.it.bean.EquipmentBean;
import manna.it.bean.EquipmentDAO;

public class EquipmentRentalAddAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		EquipmentDAO equipmentDao = new EquipmentDAO();
		EquipmentBean equipment = new EquipmentBean();

		boolean result = false;

		Date date = null;

		System.out.println("equipmentRentalAddAction.java")	;


		if(request.getParameter("eq_code")!=null){
			equipment.setEq_code(Integer.parseInt(request.getParameter("eq_code")));
			System.out.println("eq_code::::"+Integer.parseInt(request.getParameter("eq_code")));
		}
		equipment.setRt_m_code(request.getParameter("rent_mname"));
		System.out.println("rent_mname::::"+request.getParameter("rent_mname"));
		
		equipment.setRt_sdate_s(request.getParameter("rent_date"));
		System.out.println("rent_date::::"+request.getParameter("rent_date"));

		
		result = equipmentDao.insertEquipmentRental(equipment);
		
		

		if (result == false) {
			System.out.println("입력실패");
			return null;
		}
		forward.setRedirect(true);
		
		forward.setPath("/index.jsp?content=/equipment.eq");
		return forward;
	}

}
