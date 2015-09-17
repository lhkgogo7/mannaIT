package manna.it.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manna.it.bean.EquipmentDAO;

public class EquipmentDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		boolean result= false;
		boolean check = false;
		int eq_code = Integer.parseInt(request.getParameter("eq_code"));
		
		EquipmentDAO eqdao = new EquipmentDAO();
		/*check = eqdao.isEquiment(num);
		
		if(check == false){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 데이터가없습니다.');");
			out.println("location.href='/equipment_list.eq';");
			out.println("</script>");
			out.close();
			return null;
			
		}*/
		result=eqdao.equipmentDelete(eq_code);
		
		if(result==false){
			System.out.println("게시판 삭제 실패");
			return null;
		}
		
		System.out.println("게시판 삭제 성공");
		forward.setRedirect(true);
		forward.setPath("/equipment_list.eq");		
		return forward;
		
	}

}
