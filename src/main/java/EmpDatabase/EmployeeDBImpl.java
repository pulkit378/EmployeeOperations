package EmpDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import commonFiles.Employee;
import commonFiles.customExceptions;

	

public class EmployeeDBImpl extends AbstractDAOImpl implements EmployeeDBInterface  {
	
		Scanner scanner = new Scanner(System.in);
		
		public EmployeeDBImpl () {
		}

		public List<Employee> loadEmployees() throws SQLException {
			List<Employee> empList = new ArrayList<>();
			try (PreparedStatement pst = getConnection().prepareStatement("select number, name, salary, age from emp")) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Employee emp = new Employee();
					emp.setNumber(rs.getInt("number"));
					emp.setName("name");
					emp.setSalary(rs.getInt("salary"));
					emp.setAge(rs.getInt("age"));
					empList.add(emp);
				}
			}
			return empList;
		}

		@Override
		public Employee loadEmployee(String number) throws SQLException {
			System.out.println("Displaying Records...");
			Employee emp = null;
			ResultSet rs = null;
			try (PreparedStatement pst = getConnection().prepareStatement("select number, name, salary,age from emp where number = ?");){
				pst.setInt(1, Integer.parseInt(number));
				rs =	pst.executeQuery();
				if(rs.next()) {
					emp = new Employee();
					emp.setNumber(rs.getInt(1));
					emp.setName(rs.getString(2));
					emp.setSalary(rs.getInt(3));
					emp.setAge(rs.getInt(4));
				}
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
			try (PreparedStatement pst = getConnection().prepareStatement("update emp set name = ? , salary = ? where age = ?");){
				
				pst.setString(1, emp.getName());
				pst.setInt(2, (int) emp.getSalary());
				pst.setInt(3, emp.getAge());
				int noOfRecords = pst.executeUpdate();
				return noOfRecords > 0; 
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}

		@Override
		public boolean deleteEmployee(String number) {
			System.out.println("Deleting emp...");
			try (PreparedStatement pst = getConnection().prepareStatement("delete from emp where number = ?");){
				
				pst.setInt(1, Integer.parseInt(number));
				int noOfRecords = pst.executeUpdate();
				return noOfRecords > 0; 
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}



		@Override
		public boolean addEmployee(Employee emp) {
			System.out.println("Inserting emp...");
			try (Connection con = getConnection();
					PreparedStatement pst = con.prepareStatement("insert into emp (number, name, salary,age) values (?,?, ?, ?)");){
				con.setAutoCommit(false);
				pst.setInt(1, emp.getNumber());
				pst.setString(2, emp.getName());
				pst.setFloat(3, (float) emp.getSalary());
				pst.setInt(4, emp.getAge());
				
				int noOfRecords = pst.executeUpdate();
				con.commit();
				return noOfRecords > 0; 
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}

		@Override
		public List<Employee> sortDataDB(String column) throws SQLException {
			List<Employee> empList = new ArrayList<>();
			try (PreparedStatement pst = getConnection().prepareStatement("Select number, name, salary, age from emp "
																			+ "order by " + column + " desc")) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Employee emp = new Employee();
					emp.setNumber(rs.getInt("number"));
					emp.setName(rs.getString("name"));
					emp.setSalary(rs.getInt("salary"));
					emp.setAge(rs.getInt("age"));
					empList.add(emp);
				}
			}
			return empList;

			}
			
		
		public Employee createEmployee() throws customExceptions, InputMismatchException {

			int number, salary, age;
			String name;
			System.out.println("Please enter the employee details for employee ");
			System.out.print("Name: ");
			name = scanner.next();
			System.out.print("Employee Number: ");
			number = scanner.nextInt();
			System.out.print("Salary: ");
			salary = scanner.nextInt();
			if (salary <= 5000) {
				throw new customExceptions("Salary should be greater than 5000");
			}
			System.out.print("Age: ");
			age = scanner.nextInt();
			Employee emp = new Employee(name, number, salary, age);
			return emp;
		}

}		
		
		
		
	/*	
		
		
		
		
		
		

		
		public static void main(String[] args) throws SQLException {
			
			
			EmployeeDaoImpl edi = new EmployeeDaoImpl();
			List<Employee> empList = new ArrayList<>();
			empList = edi.sortDataDB("age");
			System.out.println(empList);
			
		}
	*/	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
