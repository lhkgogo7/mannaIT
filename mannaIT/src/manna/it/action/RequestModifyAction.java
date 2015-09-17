package manna.it.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

public class RequestModifyAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		
		
		boolean result = false;

		
		RequestDAO reqDao = new RequestDAO();
		RequestBean reqBean = new RequestBean();
		/*
		Date date = null;
		date = reqDao.stringToDateConversion(request.getParameter("eq_date"));*/
		
		try{
			reqBean.setCa_code(Integer.parseInt(request.getParameter("rq_category")));
			System.out.println(request.getParameter("rq_category"));
			reqBean.setReq_code(request.getParameter("req_code"));
			System.out.println(request.getParameter("req_code"));
			reqBean.setReq_subject(request.getParameter("req_subject"));
			System.out.println(request.getParameter("req_subject"));
			reqBean.setReq_content(request.getParameter("req_content"));
			System.out.println(request.getParameter("req_content"));
			reqBean.setReq_report(request.getParameter("req_report"));
			System.out.println(request.getParameter("req_report"));
			/*reqBean.setDate(date);*/
			result = reqDao.modifyRequest(reqBean);
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
