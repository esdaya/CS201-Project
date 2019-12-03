package login; 

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import jdbcPackage.jdbcName; //if there's another jdbc file
import login.CPdata;
/**
 * Servlet implementation class login
 */
@WebServlet("/cplogin")
public class cplogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cplogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//String nextPage = "home.html";	//to next page;
		
		
		String name = request.getParameter("cp_name");  //4 elements of the login
		String email = request.getParameter("cp_email");
		String cpClass = request.getParameter("class_id");
		String cpPassword = request.getParameter("cp_password");
		String classPass = request.getParameter("class_password");
		
		
		if(name=="" || email=="" || cpClass=="" || cpPassword=="" || classPass =="") {
			request.setAttribute("cp_name", name);	//re fill in the parameters
			request.setAttribute("cp_email", email);	//re fill in the parameters
			request.setAttribute("class_id", cpClass);	//re fill in the parameters
			//don't re-fill in the password
			request.setAttribute("class_pass", classPass);	//re fill in the parameters
			request.setAttribute("error", "You didn't fill in all of the fields"); //setting parameter error to display an error
			//nextPage = "login.html";
		}
		else {
			
			//for the email/password login
			String result = CPdata.addCP(name, email, cpPassword, cpClass, classPass, false);
			if (result.substring(0,1).equals("2")) {
				//good
				//nextPage = "home.html";
				
				//now create a cookie
				//Cookie cpCookie = new Cookie("CP_Email", email); //name, value
				/*to delete it: 
				 Cookie ck=new Cookie("cp_email","");//deleting value of cookie  
				 ck.setMaxAge(0);//changing the maximum age to 0 seconds  
				response.addCookie(ck);//adding cookie in the response  
				*/
					
				String[] arr = result.split(",", 0); 
				JsonObject jso = new JsonObject();
				jso.addProperty("cp_id", arr[1]);
				jso.addProperty("name", name);
				jso.addProperty("class_id", arr[2]);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(jso);
				//somehow send this string back
				request.setAttribute("data", json);
			}
			else if(result.equals("3")) {
				request.setAttribute("error", "email could not be found"); //setting parameter error to display an error
				//nextPage = "login.html";
			}
			else if(result.equals("4")) {
				request.setAttribute("error", "email did not match your personal password"); //setting parameter error to display an error
				//nextPage = "login.html";
			}
			else if(result.equals("5")) {
				request.setAttribute("error", "your class password was incorrect"); //setting parameter error to display an error
				//nextPage = "login.html";
			}
			else {
				request.setAttribute("error", "There was an error, try logging in again..."); //setting parameter error to display an error
				//nextPage = "login.html";
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	}

}
