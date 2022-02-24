package project.bank.accounts;

import java.util.Scanner;

import project.bank.dao.EmployeeDAO;
import project.bank.dao.EmployeeDAOImpl;
import project.bank.howard.BankApp;
import project.bank.model.Employee;

public class EmpSignUp {
	Scanner scanner = new Scanner(System.in);
	String fname = "";//Employee first name
	String lname = "";//Employee last name
	String password = "";//Employe password
	int id = -1;//Default id
	boolean accSuc = false;//Account success check
	Employee employee;
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	BankApp App = new BankApp();
	
	public void EmployeeSignUp() {
		System.out.println("\n=============================================================");
		System.out.println("Employee Sign Up Page\n");
		while(accSuc == false)
			empSignUp();
		
		employee = new Employee(id, fname, lname, password);
		employeeDAO.addEmployee(employee);
	}
	
	public void empSignUp() {
		System.out.println("Please enter first name: ");
		fname = scanner.next();// Taking user first name
		System.out.println("Please enter last name: ");
		lname = scanner.next();// Taking user last name
		System.out.println("Please enter password: ");
		password = scanner.next();// Taking user password
		if(employeeDAO.doesEmpCredExist(fname, lname, password))
			System.out.println("ERROR: An account has already been made with these credentials, "
					+ "please try again.\n");
		else
			accSuc = true;
	}
}
