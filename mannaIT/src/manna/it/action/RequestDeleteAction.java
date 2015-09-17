package manna.it.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manna.it.bean.RequestDAO;

public class RequestDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		boolean result = false;
		boolean check = false;

		try {
			int req_code = Integer.parseInt(request.getParameter("req_code"));
			System.out.println("req_code"+req_code);
			RequestDAO rqdao = new RequestDAO();
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
			result = rqdao.requestDelete(req_code);

			if (result == false) {
				System.out.println("요청 삭제 실패");
				return null;
			}
			System.out.println("요청 삭제 성공");
			forward.setRedirect(true);
			forward.setPath("/request_list.rq");
			return forward;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}
}