package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;


public class RequestAddAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		RequestDAO requestDao = new RequestDAO();
		RequestBean requestBean = new RequestBean();
		
		boolean result = false;
		String m_name =  request.getParameter("m_name");
		//String m_code = requestDao.findMember(m_name);
		String m_code=request.getParameter("m_code");
		requestBean.setM_code(m_code);
		
		System.out.println("m_code"+m_code);
		requestBean.setCa_code(Integer.parseInt(request.getParameter("rq_category")));
		requestBean.setReq_subject(request.getParameter("req_subject"));
		requestBean.setReq_content(request.getParameter("req_content"));
		requestBean.setReq_report(request.getParameter("req_report"));

		result = requestDao.insertRequest(requestBean);
		
		if(result==false){
			System.out.println("입력실패");
			return null;
		}
		forward.setRedirect(true);
		forward.setPath("/request.rq");
		return forward;
		
		
	}
	

}
