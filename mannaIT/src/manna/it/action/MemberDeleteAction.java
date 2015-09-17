package manna.it.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manna.it.bean.MemberDAO;

public class MemberDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		boolean result = false;
		boolean check = false;

		try {
			String m_code = request.getParameter("m_code");
			System.out.println("m_code"+m_code);
			MemberDAO memberDao = new MemberDAO();
			/*
			 * check = eqdao.isEquiment(num);
			 * 
			 * if(check == false){
			 * response.setContentType("text/html;charset=utf-8"); PrintWriter
			 * out = response.getWriter(); out.println("<script>");
			 * out.println("alert('삭제할 데이터가없습니다.');");
			 * out.println("location.href='/equipment_list.eq';");
			 * out.println("</script>"); out.close(); return null;
			 * 
			 * }
			 */
			result = memberDao.memberDelete(m_code);

			if (result == false) {
				System.out.println("요청 삭제 실패");
				return null;
			}
			System.out.println("요청 삭제 성공");
			forward.setRedirect(true);
			forward.setPath("/member_list.rq");
			return forward;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}
}