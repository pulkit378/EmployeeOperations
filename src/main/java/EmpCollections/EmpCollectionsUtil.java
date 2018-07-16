package EmpCollections;

import java.util.Scanner;

public class EmpCollectionsUtil {

	static Scanner scanner = new Scanner(System.in);

	public static void displayMenu() {

		System.out.println("1. Create Employee");
		System.out.println("2. Read(View) Employee");
		System.out.println("3. View all Employees");
		System.out.println("4. Update Employee");
		System.out.println("5. Delete Employee");
		System.out.println("6. Calculate HRA");
		System.out.println("7. Calculate Gross Salary");
		System.out.println("8. Sort List");
		System.out.println("9. Exit");
	}

	public static void operateMenu( EmpCollectionsOperationsImpl empl) throws Exception {

		int opt = 0;
		while (opt != 9) {
			System.out.print("Select an Option: ");
			opt = scanner.nextInt();

			while (opt < 0 || opt > 9) {
				System.out.println("Please enter a valid input");
				opt = scanner.nextInt();
			}

			switch (opt) {

			case 1:
				empl.addEmployee();
				break;

			case 2:

				System.out.print("Enter the Employee number which you wish to view: ");
				int num = scanner.nextInt();
				empl.getEmployee(num);
				break;

			case 3:

				empl.displayAllEmployees();
				break;

			case 4:
				System.out.print("Enter the Employee number which you wish to view: ");
				int number = scanner.nextInt();
				empl.updateEmployee(number);
				break;

			case 5:

				System.out.print("Enter the Employee number which you wish to delete: ");
				int del = scanner.nextInt();
				empl.delEmployee(del);
				break;

			case 6:

				System.out.print("Enter the Employee number for which you want to calculate the HRA: ");
				int indexHRA = scanner.nextInt();
				System.out.println(empl.HRA(indexHRA));
				break;

			case 7:

				System.out.print("Enter the Employee number for which you want to calculate the Gross Salary: ");
				int indexGS = scanner.nextInt();
				System.out.println(empl.grossSal(indexGS));
				break;

			case 8:
				int val = 0;
				while (val != 5) {
					System.out.println("How do you want to sort your list: ");
					System.out.println("1. By Salary | 2. By Name and Salary | 3. Age |  4. By ID 5. Exit : ");
					val = scanner.nextInt();
					empl.empSort(val);
				}
				break;

			case 9:

				System.out.println("The program will end now ");
				break;

			}

		}
	}

}