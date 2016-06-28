package br.com.rescuebots_web;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rescuebots_brain.database.Database;


/**
 * Servlet implementation class CommandServlet
 */
@WebServlet("/CommandServlet")
public class CommandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Database database = new Database(); 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandServlet() {
        super();
        database.initDatabase();
        // TODO Auto-generated constructor stub
    }

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String method = request.getParameter("method").trim();
        String result = "";
        if(method == null || "".equals(method)){
        	result = "Method not found";
        }else{
        	if(method.equalsIgnoreCase("getlocation")){
        		String robotid = request.getParameter("robotid").trim();
        		result = database.getLastCoordinate(robotid);
        	}else if(method.equalsIgnoreCase("update")){
        		String robotid = request.getParameter("robotid").trim();
        		database.update(robotid);
        	}else if(method.equalsIgnoreCase("getlfirstocation")){
        		String robotid = request.getParameter("robotid").trim();
        		result = database.getFirstCoordinate(robotid);
        	}else if(method.equalsIgnoreCase("getsaviourfoundcoordinate")){
        		result = database.getSaviourFoundCoordinate();
        	}else if(method.equalsIgnoreCase("listrobots")){
        		result = database.getRobots();
        	}else{
        		result = "Method not found";
        	}
        }
         
        //String greetings = "Hello " + userName;
         
        response.setContentType("text/plain");
        if(result!=null && !result.equalsIgnoreCase("")){
        	response.getWriter().write(result.toString());
        }else{
        	response.getWriter().write("");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
