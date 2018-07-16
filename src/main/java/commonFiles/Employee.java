package commonFiles;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

	private String name;
	private int number;
	private double salary;
	private int age;

	static final double start_salary;

	static {
		start_salary = 10000;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Employee(String name, int number, double salary, int age) {
		super();
		this.name = name;
		this.number = number;
		this.salary = salary;
		this.age = age;
	}

	public void setInfo(String name, int number, int salary, int age) {

		this.name = name;
		this.number = number;
		this.salary = salary;
		this.age = age;
	}

	public void getInfo(Employee e) {

		System.out.println("Name: " + this.name + "   Employee Number: " + this.number + "   Salary: " + this.salary
				+ "   Age: " + this.age);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", number=" + number + ", salary=" + salary + ", age=" + age + "]";
	}

	public int compareTo(Employee arg0) {

		int value1 = arg0.getName().compareTo(this.getName());
		if (value1 == 0) {
			return Double.valueOf(arg0.getSalary()).compareTo(Double.valueOf(this.getSalary()));
		} else {
			return value1;
		}
	}

	public static class salaryComparator implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return Double.valueOf(e1.getSalary()).compareTo(Double.valueOf(e2.getSalary()));
		}
	}

	public static class ageComparator implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return Integer.valueOf(e1.getAge()).compareTo(Integer.valueOf(e2.getAge()));
		}
	}


}
