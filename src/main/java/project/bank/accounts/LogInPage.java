package project.bank.accounts;

import java.util.Scanner;

public class LogInPage {
	Scanner scanner = new Scanner(System.in);
	String accType = "";// Choice for entering account type
	
	public void logInInfo() {
		System.out.println("\n=============================================================");
		System.out.println("Please enter the type of login (C - Customer / E - Employee): ");
		String accType = scanner.next();
		
		switch(accType.toLowerCase()) {
		case "c":
			CustLogIn c = new CustLogIn();
			c.customerLogIn();
			break;
			
		case "e":
			EmpLogIn e = new EmpLogIn();
			e.employeeLogIn();
			break;

		default:
			System.out.println("ERROR: Incorrent account type, please enter again\n");
			logInInfo();
			break;
		}
	}
}
