package manna.it.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

public class RequestListSearch {

	public Vector<RequestBean> RequestResultUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		RequestDAO requestDao = new RequestDAO();

		Vector<RequestBean> req_list = new Vector<RequestBean>();
		
		try{
			req_list = requestDao.getRequestList();
			System.out.println("리스트보기 성공");
			
			
			return req_list;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("리스트 호출 실패");
		return null;
		


	}
	
	
	
}
