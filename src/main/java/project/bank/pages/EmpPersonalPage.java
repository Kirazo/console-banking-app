package project.bank.pages;

import java.util.InputMismatchException;
import java.util.Scanner;

import project.bank.dao.CustomerDAO;
import project.bank.dao.CustomerDAOImpl;
import project.bank.dao.EmployeeDAO;
import project.bank.dao.EmployeeDAOImpl;
import project.bank.dao.TransactionDAO;
import project.bank.dao.TransactionDAOImpl;
import project.bank.howard.BankApp;

public class EmpPersonalPage {
	Scanner scanner = new Scanner(System.in);
	BankApp App = new BankApp();
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	TransactionDAO transactionDAO = new TransactionDAOImpl();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	int choice = 0;// Choice for choosing type
	String filler = "";// For input errors
	boolean loggedOut = false;

	public void empPage(int id) {
		System.out.println("\n=============================================================");
		System.out.println("Personal page for Associate " + employeeDAO.getEmpfn(id) + " " + employeeDAO.getEmpln(id));
		System.out.println("1. Approve or Reject accounts");
		System.out.println("2. View customer bank account");
		System.out.println("3. View log of all transactions");
		System.out.println("8. Logout");
		System.out.println("9. Exit");
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException a) { // Catches error if input is invalid
			filler = scanner.nextLine();
		}

		switch (choice) {
		case 1:
			approveRejAcc();
			choice = 0;
			break;
			
		case 2:
			custView();
			choice = 0;
			break;
			
		case 3:
			transView();
			choice = 0;
			break;

		case 8:
			System.out.println("Logged out\n");
			choice = 0;
			loggedOut = true;
			App.startApp();
			break;

		case 9:
			System.out.println("Thanks for using the program");
			System.exit(0);
			break;

		default:
			System.out.println("ERROR: Invalid choice, please try again");
			choice = 0;
			empPage(id);
		}
		if (loggedOut == false)
			empPage(id);
	}

	public void approveRejAcc() {
		int choice1 = 0;// Choice for approving or rejecting
		int custId = 0;
		System.out.println("Select (1) to approve a customer account, and (2) to reject");
		try {
			choice1 = scanner.nextInt();
		} catch (InputMismatchException a) { // Catches error if input is invalid
			filler = scanner.nextLine();
		}
		switch (choice1) {
		case 1:
			System.out.println("Enter customer ID to approve: ");
			try {
				custId = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("ERROR: ID must be entered in numbers.\n" + "Returning to main menu.");
				filler = scanner.nextLine();
				break;
			}
			if (custId < 0)
				System.out.println("ERROR: ID Can not be negative, returning to main menu.");
			else if (customerDAO.doesCustExist(custId)) {
				if (employeeDAO.hasAppr(custId))
					System.out.println("ERROR: Customer has already been approved.\n" + "Returning to main menu");
				else if (!employeeDAO.hasAppr(custId))
					employeeDAO.approveCust(custId);
			} else
				System.out.println("ERROR: Customer does not exist.\n" + "Returning to main menu.");

			break;
		case 2:
			System.out.println("Enter customer ID to reject: ");
			try {
				custId = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"ERROR: ID has already been approved or does not exist.\n" + "Returning to main menu.");
				filler = scanner.nextLine();
				break;
			}
			if (custId < 0)
				System.out.println("ERROR: ID Can not be negative, returning to main menu.");
			else if (customerDAO.doesCustExist(custId)) {
				if (employeeDAO.hasAppr(custId))
					System.out.println("ERROR: Customer has already been approved.\n"
							+ "Can not reject customer, returning to main menu.");
				else if (!employeeDAO.hasAppr(custId))
					employeeDAO.removeCust(custId);
			} else
				System.out.println("ERROR: Customer does not exist.\n" + "Returning to main menu.");
			break;

		default:
			System.out.println("ERROR: Invalid input, returning to main menu.");
		}

	}

	public void custView() {
		int choice1 = 0;
		int custId = 0;
		boolean invId = false;// Invalid ID
		System.out.println("Enter ID of customer account you would like to view: ");
		try {
			custId = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: ID must be entered in numbers.\n" + "Returning to main menu.");
			invId = true;
			filler = scanner.nextLine();
		}
		if (!invId) {
			if (custId < 0)
				System.out.println("ERROR: ID Can not be negative, returning to main menu.");
			else if (customerDAO.doesCustExist(custId))
				employeeDAO.empView(custId);
			else
				System.out.println("ERROR: Customer does not exist, returning to main menu.");
		}

	}
	
	public void transView() {
		if(!transactionDAO.viewTransLog())
			System.out.println("ERROR: There are no transactions at the moment.");
	}

}
