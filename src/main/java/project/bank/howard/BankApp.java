package project.bank.howard;

import java.util.InputMismatchException;
import java.util.Scanner;

import project.bank.accounts.LogInPage;
import project.bank.accounts.SignUpPage;

public class BankApp {
	Scanner scanner = new Scanner(System.in);
	int choice1 = 0;// Choice for start of banking app
	String filler = "";// For input errors
	public void startApp() {
		System.out.println("\n=============================================================");
		welcome();
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. About");
		System.out.println("9. Exit Application");
		System.out.println("Please enter your choice: ");
		try {
			choice1 = scanner.nextInt();
		} catch (InputMismatchException a) {
			filler = scanner.nextLine();
		}

		switch (choice1) {

		case 1:
			LogInPage l = new LogInPage();
			l.logInInfo();
			choice1 = 0;//Reset
			break;

		case 2:
			SignUpPage s = new SignUpPage();
			s.signUpInfo();
			choice1 = 0;//Reset
			break;

		case 3:
			aboutBank();
			choice1 = 0;//Reset
			break;

		case 9:
			System.out.println("Thanks for using the program");
			System.exit(0);

		default:
			System.out.println("ERROR: Invalid option, please enter (1-4) or 9 to exit\n");
			choice1 = 0;//Reset
			break;
		}
		startApp();

	}

	public void welcome() {
		System.out.println("Welcome to Howard's banking app!");
	}

	public void aboutBank() {
		System.out.println("\nThis bank app simulates banking operations.\n"
				+ "You can either sign up as a customer or employee.\n"
				+ "As a customer, you can withdraw, deposit, or transfer money."
				+ "\n\nAs an employee, you can approve or reject an account.\n"
				+ "You can view a customer's bank account and view a log of all transactions.");
	}
}
