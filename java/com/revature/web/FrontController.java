package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.ReimbursementController;

/**
 * Servlet implementation class FrontController
 */
//use /* if there is no url pattern clashing with the other servlets
@WebServlet(urlPatterns = {"/rest/*"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeController employeeController;
    private ReimbursementController reimbursementController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        employeeController = new EmployeeController();
        reimbursementController = new ReimbursementController();
        // TODO Auto-generated constructor stub
    }
    public FrontController(EmployeeController eController, ReimbursementController rController ) {
    	super();
    	employeeController = eController;
    	reimbursementController = rController;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Get our URI. As our RESTapi, our URI is important to determine what resource is being requested.
		 * You neeed to manipulat the URI to find the resource it is that you are requesting
		 */
		//with .replace, you are removing the unecessary part of the URI that asks for a resource
		final String URI = request.getRequestURI().replace("/Project_1_Christopher_Castrillon/rest", "");
		
		System.out.println(URI);
		//you have to remove the first slash
		if(URI.charAt(0) != '/') {
			/*
			 * If there is not / in /Submissions eg: then send back a bad request error
			 * the URL is not structured correctly. If it is, then replace the first slash so it's not needed
			 */
			response.setStatus(400);
			return;
		}
		final String resource = URI.replaceFirst("/", "").split("/")[0];
		//You don't always know the length of the URI so you should check to see how long it is and then delegate that request
		//you split the uri into portions, the combination of the portions determines what resource you are looking for.
		//the lenght of portions will determine how many slashes you have
		//how many portions is too many portions? you can validate for that
		String[] portions = URI.replaceFirst("/","").split("/");
		//this could change as you develop further bc maybe you can support more complex URI patterns in the future.
		
		if(portions.length >= 3) {
			response.setStatus(400);
			return;
		}
		
		switch(resource) {
			case("employees"):
				System.out.println("YOU CALLED FOR THE EMPLOYEE RESOURCE");
				//rather than processing the request
				//rather than putting the logic here, delegate this to another class/helper.
				//you can have an employee controller that responds to this
				employeeController.process(request, response, portions);
				
				break;
			case("reimbursements"):
				System.out.println("YOU CALLED FOR THE REIMBURSEMENTS RESOURCE");
				reimbursementController.process(request, response, portions);
				break;
			default:
				response.setStatus(400);
				return;
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/Project_1_Christopher_Castrillon/rest", "");
		System.out.println(URI);
		//you have to remove the first slash
		if(URI.charAt(0) != '/') {
			/*
			 * If there is not / in /Submissions eg: then send back a bad request error
			 * the URL is not structured correctly. If it is, then replace the first slash so it's not needed
			 */
			response.setStatus(400);
			return;
		}
		final String resource = URI.replaceFirst("/", "").split("/")[0];
		String[] portions = URI.replaceFirst("/","").split("/");
		//this could change as you develop further bc maybe you can support more complex URI patterns in the future.
		if(portions.length >= 3) {
			response.setStatus(400);
			return;
		}
		switch(resource) {
		case("reimbursements"):
			System.out.println("YOU CALLED FOR THE REIMBURSEMENTS RESOURCE");
			reimbursementController.process(request, response, portions);
			return;
		default:
			response.setStatus(400);
			return;
			
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

}
