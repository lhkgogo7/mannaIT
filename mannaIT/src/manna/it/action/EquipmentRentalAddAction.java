package manna.it.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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



		int eq_code=0;
		if(request.getParameter("eq_code")!=null){
			eq_code= Integer.parseInt(request.getParameter("eq_code"));
			equipment.setEq_code(eq_code);
		}
		String m_code = request.getParameter("rent_mname");
		equipment.setRt_m_code(m_code);		
		equipment.setRt_sdate_s(request.getParameter("rent_date"));

	
		
		result= equipmentDao.insertEquipmentRental(equipment);
		if (result == false) {
			System.out.println("EquipmentRental입력실패");
			return null;
		}else{
			String m_name = "";
			m_name = equipmentDao.getUserName(m_code);
			
			result =  equipmentDao.updateEquipmentUser(eq_code, m_name);
		
			if (result == false) {
				System.out.println("updateEquipmentUser실패");
				return null;
			}else	
				forward.setRedirect(true);			
				forward.setPath("/index.jsp?content=/equipment.eq");
				return forward;
		}
	
	}
}


