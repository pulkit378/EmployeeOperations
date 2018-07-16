package EmpDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import com.mysql.jdbc.Statement;

import commonFiles.Employee;
import commonFiles.customExceptions;

public class UserDaoImpl extends AbstractDAOImpl implements EmployeeDBInterface {
	
	
	public UserDaoImpl () {
	}

	@Override
	public List<Employee> loadEmployees() {
		return null;
		// TODO Auto-generated method stub
	}

	public Employee loadEmployee(String number) throws SQLException{
		System.out.println("Displaying Records...");
		Employee emp = null;
		ResultSet rs = null;
		try {
			rs =getStatement().executeQuery("select * from user where number = " + number);
			emp = new Employee();
			if(rs.next()) {
				emp.setNumber(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getInt(3));
				emp.setAge(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		
		return emp;
	}

	@Override
	public boolean update(Employee emp) {
		System.out.println("Updatng Records...");
		boolean flag = false;
		try {
			StringBuilder insertQuery = new StringBuilder();
			
			insertQuery.append("update emp set name = '")
					.append(emp.getName()).append("'")
					.append(", salary = '").append(emp.getSalary())
					.append(", age = '").append(emp.getAge())
					.append("'").append(" where number = ").append(emp.getNumber());
					
			int noOfRecords = getStatement().executeUpdate(insertQuery.toString());
			return noOfRecords > 0; 
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			
		}
		
		return flag;
	}

	private Statement getStatement() throws SQLException {
		return (Statement) getConnection().createStatement();
	}

	@Override
	public boolean deleteEmployee(String number) {
		return true;
	}


	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> sortDataDB(String column) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee createEmployee() throws customExceptions, InputMismatchException {
		// TODO Auto-generated method stub
		return null;
	}	
	

}
