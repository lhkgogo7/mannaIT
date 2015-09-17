package manna.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ReqListServlet
 */
@WebServlet("/ReqListServlet")
public class ReqListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReqListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Text/html);charset=utf-8");
		PrintWriter out = response.getWriter();
		RequestDAO requestDao = new RequestDAO();

		Vector<RequestBean> req_vector = new Vector<RequestBean>();
		JSONArray req_list = new JSONArray();

		try {
			System.out.println("getRequestList()");
			req_vector = requestDao.getRequestList();
			System.out.println("req_vector" + req_vector);
			for (int i = 0; i < req_vector.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("req_code", req_vector.get(i).getReq_code());
				obj.put("Ca_name", req_vector.get(i).getCa_name());
				obj.put("M_name", req_vector.get(i).getM_name());
				obj.put("Req_subject", req_vector.get(i).getReq_subject());
				obj.put("Req_content", req_vector.get(i).getReq_content());
				obj.put("Req_date", req_vector.get(i).getReq_date());
				obj.put("Req_report", req_vector.get(i).getReq_report());
				obj.put("Res_name", req_vector.get(i).getRes_name());
				System.out.println("obj" + i + obj);
				req_list.add(obj);
			}
			out.print(req_list);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
