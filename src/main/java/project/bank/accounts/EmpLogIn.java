package project.bank.accounts;

import java.util.InputMismatchException;
import java.util.Scanner;

import project.bank.dao.EmployeeDAO;
import project.bank.dao.EmployeeDAOImpl;
import project.bank.pages.EmpPersonalPage;

public class EmpLogIn {
	Scanner scanner = new Scanner(System.in);
	EmpPersonalPage e = new EmpPersonalPage();
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	int id = 0;//Id for signing in
	String pw = null;//Password for signing in
	String filler = "";//filler text
	
	public void employeeLogIn() {
		while(id == 0)
			id = enterId();
		
		while(pw == null)
			pw = enterPw();
		
		e.empPage(id);
	}
	
	public int enterId() {
		System.out.println("Please enter your Employee id: ");
		try {
			id = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Your ID must contain only letters, please try again.\n");
			filler = scanner.nextLine();
			return id;
		}
		if (employeeDAO.doesEmpExist(id))
			return id;
		else {
			System.out.println("ERROR: This id is not in the system, plese try again.\n");
			id = 0;
			return id;
		}
	}
	
	public String enterPw() {
		System.out.println("Enter your password: ");
		pw = scanner.next();
		if(employeeDAO.checkUserCred(id, pw))
			return pw;
		else {
			System.out.println("ERROR: Incorrect password, please try again.\n");
			pw = null;
			return pw;	
		}
	}
}
