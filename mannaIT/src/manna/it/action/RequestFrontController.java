package manna.it.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EquipmentFrontController
 */
public class RequestFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/request.rq")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/request/request_list.jsp");
		}else if(command.equals("/requestListSearchAjax.rq")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/request/ajax/req_list_ajax.jsp");
		
		}else if(command.equals("/requestAdd.rq")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/request/request_add.jsp");		
		}else if(command.equals("/requestAddAction.rq")){
			action = new RequestAddAction();
			try{
				forward=action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/requestDeleteAction.rq")){
			action = new RequestDeleteAction();
			try{
				forward =action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/requestModifyAction.rq")){
			action = new RequestModifyAction();
			try{
				forward= action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/requestView.rq")){
			action = new RequestViewAction();
			try{
				forward = action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/requestResult.rq")){
			action = new RequestResultAction();
			try{
				forward= action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/requestListSearch.rq")){
			action = new RequestListSearchAction();
			try{
				forward= action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/resultListAjax.rq")){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/request/ajax/res_list_ajax.jsp");
		}
		
		
		if(forward !=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher= request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request,response);
			}
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
