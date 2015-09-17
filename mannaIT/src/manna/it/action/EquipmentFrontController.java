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
public class EquipmentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquipmentFrontController() {
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
		
		if(command.equals("/equipment.eq")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./equipment/equipment_list.jsp");
			
		}else if(command.equals("/equipmentAdd.eq")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./equipment/equipment_add.jsp");
			
		}else if(command.equals("/equipmentAddAction.eq")){
			action = new EquipmentAddAction();
			try{
				forward=action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/equipmentDeleteAction.eq")){
			action = new EquipmentDeleteAction();
			try{
				forward =action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/equipmentModifyView.eq")){
			action = new EquipmentModifyView();
			try{
				forward = action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/equipmentModifyAction.eq")){
			action = new EquimentModifyAction();
			try{
				forward= action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/equipmentView.eq")){
			action = new EquipmentViewAction();
			try{
				forward = action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/equipmentListAjax.eq")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/equipment/ajax/eq_list_ajax.jsp");
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
