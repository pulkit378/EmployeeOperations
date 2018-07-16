package EmpCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import commonFiles.Employee;
import commonFiles.customExceptions;

public class EmpCollectionsOperationsImpl implements EmployeeCollectionsInterface {

	Scanner scanner = new Scanner(System.in);
	private List<Employee> employees;

	public EmpCollectionsOperationsImpl() {
		employees = new ArrayList<Employee>();
	}
	
	public EmpCollectionsOperationsImpl(List<Employee> emps) {
		employees = emps;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee findByID(int number) throws Exception {
		
		for(Employee e: employees) {
			if(e.getNumber() == number) {
				return e;
			}
		}
		throw new customExceptions("Please provide a valid employee id");
	}

	public int findIndex(int number) throws customExceptions {
		int count = 0;
		for(Employee e: employees) {
			if(e.getNumber() == number) {
				return count;
			}
			count++;
		}
		throw new customExceptions("Please provide a valid employee id");
	}
	
	public void addEmployee() throws customExceptions {
		int number, salary, age;
		String name;
		System.out.println("Please enter the employee details for employee ");
		System.out.print("Name: ");
		name = scanner.next();
		System.out.print("Employee Number: ");
		number = scanner.nextInt();
		System.out.print("Salary: ");
		salary = scanner.nextInt();
		if (salary <= 5000) {throw new customExceptions("Salary should be greater than 5000");}
		System.out.print("Age: ");
		age = scanner.nextInt();
		employees.add(new Employee(name, number, salary, age));
	}

	public void displayAllEmployees() throws Exception {
		for (Employee temp : employees) {
			System.out.println(temp);
		}
	}
	
	public void getEmployee(int number) throws Exception {
		Employee e = findByID(number);
		System.out.println(e);
	}

	public void delEmployee(int number) throws customExceptions {

		int index = findIndex(number);
		employees.remove(index);
	}
	
	public void updateEmployee(int number) throws Exception {
		System.out.println("Please enter the updated details");
		System.out.print("Name: ");
		String name = scanner.next();
		System.out.print("Salary: ");
		int salary = scanner.nextInt();
		if (salary <= 5000) {
			throw new customExceptions("Salary should be greater than 5000");
		}
		System.out.print("Age: ");
		int age = scanner.nextInt();
		findByID(number).setInfo(name, number, salary, age);
	}
	
	public void empSort(int number) {
		
		switch (number) {
		case 1:
			Collections.sort(employees,new Employee.salaryComparator());
			for(Employee emp1: employees) {
				System.out.println(emp1);
			}
			break;
			
		case 2:
			Collections.sort(employees);
			for(Employee emp2: employees) {
				System.out.println(emp2);
			}
			break;
			
		case 3:
			Collections.sort(employees,new Employee.ageComparator());
			for(Employee emp1: employees) {
				System.out.println(emp1);
			}
			break;
			
		case 4:
			
			Comparator<Employee> com2 = new Comparator<Employee>() {
				public int compare(Employee e1, Employee e2) {
					return Integer.valueOf(e1.getNumber()).compareTo(Integer.valueOf(e2.getNumber()));
				}
			};

			Collections.sort(employees, com2);

			for (Employee e : employees) {
				System.out.println(e);
			}

			break;

		}
	}	
	
	public double HRA(int number) throws Exception {
		return (0.2 * findByID(number).getSalary());
	}

	public double grossSal(int number) throws Exception {

		double sal = findByID(number).getSalary();
		double da, hra;

		if (sal < 10000) {
			da = 0.08 * sal;
			hra = 0.15 * sal;
		}
		else if (sal < 20000) {
			da = 0.10 * sal;
			hra = 0.20 * sal;
		}
		else if ((sal < 30000) && (findByID(number).getAge() >= 40)) {
			da = 0.15 * sal;
			hra = 0.27 * sal;
		}
		else if ((sal < 30000) && (findByID(number).getAge() < 40)) {
			da = 0.13 * sal;
			hra = 0.25 * sal;
		}
		else {
			da = 0.17 * sal;
			hra = 0.30 * sal;
		}
		double grossSalary = sal + da + hra;
		return grossSalary;
	}
}	
	
	
