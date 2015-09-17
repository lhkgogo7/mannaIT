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
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontController() {
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
		
		if(command.equals("/member.mb")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/member_list.jsp");
			
		}else if(command.equals("/memberListSearchAjax.mb")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/ajax/mem_list_ajax.jsp");
			
		}else if(command.equals("/memberAdd.mb")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/member_add.jsp");
			
		}else if(command.equals("/memberAddAction.mb")){
			action = new MemberAddAction();
			try{
				forward=action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/memberNameAjax.mb")){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/member/ajax/member_name_ajax.jsp");
		}else if(command.equals("/memberPositionAjax.mb")){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/member/ajax/pos_list_ajax.jsp");
		}else if(command.equals("/memberDepartmentjax.mb")){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/member/ajax/dep_list_ajax.jsp");
		}else if(command.equals("/memberDeleteAction.mb")){
			action = new MemberDeleteAction();
			try{
				forward =action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberModifyAction.mb")){
			action = new MemberModifyAction();
			try{
				forward =action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
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
