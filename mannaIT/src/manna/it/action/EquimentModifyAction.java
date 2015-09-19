package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import manna.it.bean.EquipmentBean;
import manna.it.bean.EquipmentDAO;

public class EquimentModifyAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		
		
		boolean result = false;

		
		EquipmentDAO equipmentDao = new EquipmentDAO();
		EquipmentBean equipment = new EquipmentBean();
		MultipartRequest multi =null;
		System.out.println("modify 진입");
		Date date = null;
		
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "eqPicture";
		int size = 10 * 1024 * 1024;
		try {
			multi = new MultipartRequest(request, savePath,size, "utf-8", new DefaultFileRenamePolicy());
			System.out.println("multi");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		try{
			date = equipmentDao.stringToDateConversion(multi.getParameter("eq_date"));

			int eq_code=Integer.parseInt(multi.getParameter("eq_code"));
			System.out.println(eq_code);
			equipment.setEq_code(eq_code);
			equipment.setEq_name(multi.getParameter("eq_name"));
			System.out.println(multi.getParameter("eq_name"));
			equipment.setManufacturer(multi.getParameter("eq_manufacturer"));
			equipment.setEq_ca_code(Integer.parseInt(multi.getParameter("eq_category")));
			equipment.setEq_date(date);
			equipment.setEq_picture(multi.getFilesystemName("eq_picture"));
			result = equipmentDao.modifyEquipment(equipment);
			if(result==false){
				System.out.println("수정실패");
				return null;
			}
			
			System.out.println("게시판 수정완료");
			
			forward.setRedirect(true);
			forward.setPath("/equipmentView.eq?eq_code="+eq_code);
			return forward;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	

}
