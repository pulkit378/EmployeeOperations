package EmpDatabase;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import commonFiles.Employee;
import commonFiles.customExceptions;

public interface EmployeeDBInterface {
	
	
	List<Employee> loadEmployees() throws SQLException;
	
	Employee loadEmployee(String number) throws SQLException;
	
	boolean addEmployee(Employee emp);

	boolean update(Employee emp);
	
	boolean deleteEmployee(String  number);
	
	public List<Employee> sortDataDB(String column) throws SQLException;
	
	public Employee createEmployee() throws customExceptions, InputMismatchException;
	
	

}
