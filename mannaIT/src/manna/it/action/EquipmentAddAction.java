package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import manna.it.bean.EquipmentBean;
import manna.it.bean.EquipmentDAO;

public class EquipmentAddAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		EquipmentDAO equipmentDao = new EquipmentDAO();
		EquipmentBean equipment = new EquipmentBean();
		MultipartRequest multi =null;
		boolean result = false;
		Date date = null;
		String savePath = request.getRealPath("/upload");
		int size = 10 * 1024 * 1024;
		
		/*
		 * 
		 * 
		 * equipment.setEq_name(request.getParameter("eq_name"));
		 * System.out.println(request.getParameter("eq_name"));
		 * equipment.setManufacturer(request.getParameter("eq_manufacturer"));
		 * equipment
		 * .setEq_ca_code(Integer.parseInt(request.getParameter("eq_category"
		 * ))); equipment.setEq_date(date);
		 * equipment.setEq_picture(request.getParameter("eq_pic"));
		 */
		try {
			multi = new MultipartRequest(request, savePath,size, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		date = equipmentDao.stringToDateConversion(multi.getParameter("eq_date"));
		equipment.setEq_name(multi.getParameter("eq_name"));
		System.out.println(multi.getParameter("eq_name"));
		equipment.setManufacturer(multi.getParameter("eq_manufacturer"));
		equipment.setEq_ca_code(Integer.parseInt(multi.getParameter("eq_category")));
		equipment.setEq_date(date);
		equipment.setEq_picture(multi.getFilesystemName("eq_picture"));
		result = equipmentDao.insertEquipment(equipment);

		if (result == false) {
			System.out.println("입력실패");
			return null;
		}
		forward.setRedirect(true);
		forward.setPath("/equipment_list.eq");
		return forward;
	}

}
