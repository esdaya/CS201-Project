package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Classes
 */
@WebServlet("/api/classes")
public class Classes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String JDBCString = "";
	private JDBC jdbc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Classes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		jdbc = new JDBC(JDBCString);
//		out.println("{\"name\":\"cream\",\"age\":3}");
		ArrayList<HashMap<String, Object>> classes = getClasses();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private static String[] classes_class_info = {"class_id", "class_name", "class_loc_id"};
	private static String[] classes_cp_info = {"cp_id", "cp_name"};
	private static String[] classes_student_info = {"student_id", "student_name"};

	private static String classes_get_cps = 
			"SELECT cc.class_id, c.class_name, c.class_loc_id, cc.cp_id, cp.cp_name " + 
			"FROM current_cps cc " + 
			"JOIN classes c " + 
			"	ON cc.class_id = c.class_id " + 
			"JOIN course_producers cp " + 
			"	ON cc.cp_id = cp.cp_id " + 
			"GROUP BY cc.class_id;";
	private static String classes_get_students = 
			"SELECT sq.student_id, sq.student_name, sq.class_id " + 
			"FROM student_queue sq " + 
			"ORDER BY sq.student_id;";
	
	
	public ArrayList<HashMap<String, Object>> getClasses() {
		jdbc.openConnection();
		ArrayList<HashMap<String, Object>> cpResults = jdbc.doQuery(classes_get_cps, null);
		ArrayList<HashMap<String, Object>> studentResults = jdbc.doQuery(classes_get_students, null);
		jdbc.closeConnection();
		
		ArrayList<ArrayList<HashMap<String, Object>>> resultList = new ArrayList<ArrayList<HashMap<String, Object>>>();
		resultList.add(cpResults);
		resultList.add(studentResults);
		
		String[][] info_list = {classes_cp_info, classes_student_info};
		
		String[] name_list = {"cps", "students"};
		
		ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();
		
		HashMap<Integer, HashMap<String, Object>> classMap = new HashMap<Integer, HashMap<String, Object>>();
		
		for(int i = 0; i < 2; i++) {
			for(HashMap<String, Object> row : resultList.get(i)) {
				if(!classMap.containsKey(row.get("class_id"))) { // for new class
					HashMap<String, Object> newClass = new HashMap<String, Object>();
					for(String info : classes_class_info) {
						newClass.put(info, row.get(info));
					}
					newClass.put("cps", new ArrayList<HashMap<String, Object>>());
					newClass.put("students", new ArrayList<HashMap<String, Object>>());
					classMap.put((int)row.get("class_id"), newClass);
				}
				// at this point the class definitely exists
				HashMap<String, Object> currentClass = classMap.get((Integer)row.get("class_id"));
				ArrayList<HashMap<String, Object>> person_list = (ArrayList<HashMap<String, Object>>) currentClass.get(name_list[i]);
				HashMap<String, Object> newPerson = new HashMap<String, Object>();
				for(String person_info : info_list[i]) {
					newPerson.put(person_info, row.get(person_info));
				}
				person_list.add(newPerson);
			}
		}
		for(
			HashMap.Entry<Integer, HashMap<String, Object>> entry : classMap.entrySet()) {
			output.add(entry.getValue());
		}
		return output;
	}

}
