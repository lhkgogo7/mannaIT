package manna.it.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manna.it.bean.RequestBean;
import manna.it.bean.RequestDAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class RequestListSearchAction implements Action{

	@SuppressWarnings("unchecked")
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
	
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		RequestDAO requestDao = new RequestDAO();

		Vector<RequestBean> req_vector = new Vector<RequestBean>();
		JSONArray req_list = new JSONArray();
		
		try{
			System.out.println("getRequestList()");
			req_vector = requestDao.getRequestList();
			System.out.println("req_vector"+req_vector);
			for(int i=0;i<req_vector.size(); i++){
				JSONObject obj= new JSONObject();
				obj.put("req_code", req_vector.get(i).getReq_code());
				obj.put("Ca_name", req_vector.get(i).getCa_name());
				obj.put("M_name", req_vector.get(i).getM_name());
				obj.put("Req_subject", req_vector.get(i).getReq_subject());
				obj.put("Req_content", req_vector.get(i).getReq_content());
				obj.put("Req_date", req_vector.get(i).getReq_date());
				obj.put("Req_report", req_vector.get(i).getReq_report());
				obj.put("Res_name", req_vector.get(i).getRes_name());
				System.out.println("obj"+i+obj);
				req_list.add(obj);
			}
			
		/*	response.setContentType("application/json");
			Gson gson = new Gson();
			String data = gson.toJson(req_vector);
			System.out.println(data);
			response.getWriter().write(data);*/
			request.setAttribute("req_list", req_list);
			
			forward.setRedirect(true);
			forward.setPath("/request.rq");
			return forward;
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	
}
