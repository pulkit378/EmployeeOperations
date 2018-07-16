package EmpDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commonFiles.Employee;


public class EmployeeDBUtil {

	static Scanner scanner = new Scanner(System.in);

	public static void displayMenu() {

		System.out.println("1. Add Employee");
		System.out.println("2. Read(View) Employee");
		System.out.println("3. View all Employees");
		System.out.println("4. Update Employee");
		System.out.println("5. Delete Employee");
		System.out.println("6. Sort Employee Data");
		System.out.println("7. Exit");
	}

	public static void operateMenu(EmployeeDBImpl empl) throws Exception {

		int opt = 0;
		while (opt != 7) {
			System.out.print("Select an Option: ");
			opt = scanner.nextInt();

			while (opt < 0 || opt > 7) {
				System.out.println("Please enter a valid input");
				opt = scanner.nextInt();
			}

			switch (opt) {

			case 1:
				Employee emp = new Employee();
				emp = empl.createEmployee();
				empl.addEmployee(emp);
				break;

			case 2:

				System.out.print("Enter the Employee number which you wish to view: ");
				int num = scanner.nextInt();
				System.out.println(empl.loadEmployee(Integer.toString(num)));
				break;

			case 3:

				List<Employee> temp = new ArrayList<Employee>();
				temp = empl.loadEmployees();
				for (Employee e : temp) {
					System.out.println(e);
				}
				break;

			case 4:
	
				System.out.print("Enter  employee do you wish to update: ");
				Employee emp3 = new Employee();
				empl.update(emp3);
				break;

			case 5:

				System.out.print("Enter the Employee number which you wish to delete: ");
				int del = scanner.nextInt();
				empl.deleteEmployee(Integer.toString(del));
				break;

			case 6:
				List<Employee> temp2 = new ArrayList<>();
				System.out.print("How do you want to sort your data: Number, name, salary or age ");
				String hold = scanner.nextLine();
				temp2 = empl.sortDataDB(hold);
				for (Employee e : temp2) {
					System.out.println(e);
				}
				break;

			case 7:

				System.out.println("The program will end now ");
				break;

			}

		}
	}

}
