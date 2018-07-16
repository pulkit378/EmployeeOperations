package EmpCollections;


import commonFiles.Employee;
import commonFiles.customExceptions;

public interface EmployeeCollectionsInterface {
	
	public Employee findByID(int number) throws Exception;
	public int findIndex(int number) throws customExceptions;
	public void addEmployee() throws customExceptions ;
	public void displayAllEmployees() throws Exception;
	public void getEmployee(int number) throws Exception;
	public void delEmployee(int number) throws customExceptions;
	public void updateEmployee(int number) throws Exception;
	public double HRA(int number) throws Exception;
	public double grossSal(int number) throws Exception;
	public void empSort(int number);
}
