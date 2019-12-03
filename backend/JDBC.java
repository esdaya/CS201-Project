package api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.ResultSetMetaData;

public class JDBC {
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private String connectUrl = null;
	
	public JDBC(String dbUrl) {
		connectUrl = dbUrl;
		try {
			conn = DriverManager.getConnection(connectUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}
	public void openConnection() {
		try {
			conn = DriverManager.getConnection(connectUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public ArrayList<HashMap<String, Object>> doQuery(String statement, String[] params){
		
		ArrayList<HashMap<String, Object>> resultList = null;
		boolean closeConn = true;
		try {
			if(conn == null) {
				openConnection();
			}
			else {
				closeConn = false; // connection is already opened and will be manually closed
			}
			ps = conn.prepareStatement(statement);
			if(params != null) {
				for(int i = 1; i < params.length + 1; i++) {
					ps.setString(i, params[i-1]);
				}
			}
			rs = ps.executeQuery();
			
			resultList = new ArrayList<HashMap<String, Object>>();
		    HashMap<String, Object> row = null;

		    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		    Integer columnCount = metaData.getColumnCount();

		    while (rs.next()) {
		        row = new HashMap<String, Object>();
		        for (int i = 1; i <= columnCount; i++) {
		            row.put(metaData.getColumnName(i), rs.getObject(i));
		        }
		        resultList.add(row);
		    }
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} finally {
			try {
				if(closeConn) {
					closeConnection(); // conn
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return resultList;
	}
	
	public void doUpdate(String statement, String[] params){
		boolean closeConn = true;
		try {
			if(conn == null) {
				openConnection();
			}
			else {
				closeConn = false; // connection is already opened and will be manually closed
			}
			ps = conn.prepareStatement(statement);
			if (params != null) { 
				for(int i = 1; i < params.length + 1; i++) {
					ps.setString(i, params[i-1]);
				}
			}
			ps.executeUpdate(); 
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} finally {
			try {
				if(closeConn) {
					closeConnection();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}
}
