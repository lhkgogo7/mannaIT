package manna.it.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import manna.it.bean.MemberBean;
import manna.it.bean.MemberDAO;



public class MemberAddAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		MemberDAO memberDao = new MemberDAO();
		MemberBean memberBean = new MemberBean();
		
		boolean result = false;
		
		System.out.println(request.getParameter("m_name")+"depcode"+(request.getParameter("m_depcode"))+"m_extension"+request.getParameter("m_extension"));
		memberBean.setM_name(request.getParameter("m_name"));
		memberBean.setM_depcode(Integer.parseInt(request.getParameter("m_depcode")));
		memberBean.setM_extension(request.getParameter("m_extension"));
		memberBean.setM_phone(request.getParameter("m_phone"));
		memberBean.setM_mobile(request.getParameter("m_mobile"));
		memberBean.setM_poscode(Integer.parseInt(request.getParameter("m_poscode")));
		if( request.getParameter("m_mail")==null){memberBean.setM_mail("no data");}
		else{	memberBean.setM_mail(request.getParameter("m_mail"));}
		
		if(request.getParameter("m_id")==null){memberBean.setM_id("no data");}
		else{memberBean.setM_id(request.getParameter("m_id"));}
		
		if((request.getParameter("m_pwd"))==null){memberBean.setM_pwd("no data");}
		else{memberBean.setM_pwd(request.getParameter("m_pwd"));}
		
		
		

		result = memberDao.insertMember(memberBean);
		
		if(result==false){
			System.out.println("입력실패");
			return null;
		}
		forward.setRedirect(true);
		forward.setPath("member.mb");
		return forward;
		
		
	}
	

}
