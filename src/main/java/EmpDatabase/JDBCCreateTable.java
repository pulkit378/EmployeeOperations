package EmpDatabase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * This class does the the following:
 * Create table in DB using JDBC API
 *  
 * @author Manohar
 *
 */
public class JDBCCreateTable {


	public static void main(String[] args) throws ClassNotFoundException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","cricket2403");
				Statement st = con.createStatement();) {
			boolean success = st.execute("CREATE TABLE emp ( " +
						  " number int(11) NOT NULL, " +
						  " name varchar(45) DEFAULT NULL, " +
						  " salary int(11) DEFAULT NULL, " +
						  " age int(11) DEFAULT NULL, " +
						  " PRIMARY KEY (number) " +
						  " );  ") ;
			
			if (success) {
				System.out.println("Table created sucessfully");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
