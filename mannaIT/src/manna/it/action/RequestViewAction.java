package manna.it.action;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

public class RequestViewAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		

		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		RequestDAO requestDao = new RequestDAO();
		RequestBean reqBean = new RequestBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		reqBean = requestDao.getRequestView(num);
		
		if(reqBean==null){
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기성공");
		
		request.setAttribute("rq", reqBean);
		forward.setRedirect(false);
		forward.setPath("/request/request_view.jsp");
		return forward;
		
		
	
		
	}
	

}
