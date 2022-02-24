package project.bank.accounts;

import java.util.Scanner;

public class SignUpPage {
	Scanner scanner = new Scanner(System.in);
	String accType = "";// Choice for entering account type

	public void signUpInfo() {
		System.out.println("\n=============================================================");
		System.out.println("Please enter the type of login (C - Customer / E - Employee): ");
		String accType = scanner.next();
		
		switch(accType.toLowerCase()) {
		case "c":
			CustSignUp c = new CustSignUp();
			c.customerSignUp();
			break;
		case "e":
			EmpSignUp e = new EmpSignUp();
			e.EmployeeSignUp();
			break;
			
		default:
			System.out.println("ERROR: Incorrent account type, please enter again\n");
			signUpInfo();
		}

	}
}
