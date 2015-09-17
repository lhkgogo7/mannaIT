package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

public class RequestResultAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		
		
		boolean result = false;

		
		RequestDAO reqDao = new RequestDAO();
		RequestBean reqBean = new RequestBean();
		
/*		Date date = null;
		date = reqDao.stringToDateConversion(request.getParameter("eq_date"));
		*/
		try{
			reqBean.setRes_code(Integer.parseInt(request.getParameter("res_code")));
			reqBean.setReq_code(request.getParameter("req_code"));
			
			result = reqDao.updateResult(reqBean);
			if(result==false){
				System.out.println("수정실패");
				return null;
			}
			
			System.out.println("게시판 수정완료");
			
			forward.setRedirect(true);
			forward.setPath("/request_list.rq");
			return forward;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	

}
