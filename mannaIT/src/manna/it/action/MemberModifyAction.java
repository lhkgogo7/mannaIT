package manna.it.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manna.it.bean.MemberBean;
import manna.it.bean.MemberDAO;



public class MemberModifyAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		
		
		boolean result = false;

		
		MemberDAO memDao = new MemberDAO();
		MemberBean memBean = new MemberBean();
		/*
		Date date = null;
		date = reqDao.stringToDateConversion(request.getParameter("eq_date"));*/
		
		try{
			memBean.setM_code(request.getParameter("m_code"));
			memBean.setM_name(request.getParameter("m_name"));
			memBean.setM_depcode(Integer.parseInt(request.getParameter("m_depcode")));			
			memBean.setM_extension(request.getParameter("m_extension"));
			memBean.setM_phone(request.getParameter("m_phone"));
			memBean.setM_mobile(request.getParameter("m_mobile"));
			memBean.setM_mail(request.getParameter("m_mail"));
			memBean.setM_poscode(Integer.parseInt(request.getParameter("m_poscode")));
			memBean.setM_id(request.getParameter("m_id"));
			memBean.setM_pwd(request.getParameter("m_pwd"));
			result = memDao.modifyMember(memBean);
			if(result==false){
				System.out.println("수정실패");
				return null;
			}
			
			System.out.println("게시판 수정완료");
			
			forward.setRedirect(true);
			forward.setPath("/member.mb");
			return forward;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	

}
