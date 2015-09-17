package manna.it.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

/**
 * Servlet implementation class RequestResultUpdate
 */
public class RequestResultUpdate {

	public boolean RequestResultUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		boolean result = false;

		RequestDAO reqDao = new RequestDAO();
		RequestBean reqBean = new RequestBean();

		/*
		 * Date date = null; date =
		 * reqDao.stringToDateConversion(request.getParameter("eq_date"));
		 */
		try {
			reqBean.setRes_code(Integer.parseInt(request.getParameter("res_code")));
			reqBean.setReq_code(request.getParameter("req_code"));

			result = reqDao.updateResult(reqBean);

			if (result == false) {
				System.out.println("수정실패");
				return false;
			}

			System.out.println("게시판 수정완료");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

}
