package project.bank.accounts;

import java.util.InputMismatchException;
import java.util.Scanner;

import project.bank.dao.CustomerDAO;
import project.bank.dao.CustomerDAOImpl;
import project.bank.howard.BankApp;
import project.bank.model.Customer;

public class CustSignUp {
	Scanner scanner = new Scanner(System.in);
	int id = -1;// Default customer id
	String fname = "";// Customer First name
	String lname = "";// Customer Last Name
	String password = "";// Customer password
	int balance = 0;// Customer starting balance
	String askBalance = "";// Ask for starting balance
	String approved = "No";// To check if customer is approved
	String filler = "";// Filler text
	boolean accSuc = false;// Account success check
	Customer customer;
	CustomerDAO customerDAO = new CustomerDAOImpl();
	BankApp App = new BankApp();

	public void customerSignUp() {
		System.out.println("\n=============================================================");
		System.out.println("Customer Sign Up Page\n");
		while (accSuc == false)
			custSignUp();

		while (!askBalance.toLowerCase().equals("y")) {
			System.out.println("Would you like to add a starting balance? (Y or N) ");
			askBalance = scanner.next();
			switch (askBalance.toLowerCase()) {
			case "y":
				while (balance == 0)
					balance = startBalance();
				break;
			case "n":
				askBalance = "y";
				balance = 0;
				break;

			default:
				System.out.println("ERROR: You must enter Y or N, please try again.\n");
			}
		}

		customer = new Customer(id, fname, lname, password, balance, approved);
		customerDAO.addCustomer(customer);
	}

	/*
	 * This method will check if there is already a user with the same first name,
	 * last name, and password as another user, and will prompt the user to change
	 * their credentials.
	 */
	public void custSignUp() {
		System.out.println("Please enter first name: ");
		fname = scanner.next();// Taking user first name
		System.out.println("Please enter last name: ");
		lname = scanner.next();// Taking user last name
		System.out.println("Please enter password: ");
		password = scanner.next();// Taking user password
		if (customerDAO.doesCustCredExist(password, fname, lname))// Account is unique
			System.out.println(
					"ERROR: An account has already been made with these credentials, " + "please try again.\n");
		else
			accSuc = true;
	}

	/*
	 * This method sets the starting balance of the customer If the customer inputs
	 * an incorrect input, the balance will automatically be set to zero
	 */
	public int startBalance() {
		boolean error1 = true;
		int amount = 0;// Starting Amount
		System.out.println("How much would you like to deposit?");
		try {
			amount = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: you must enter numbers for your starting balance, " + "Please try again\n");
			error1 = false;
			filler = scanner.nextLine();
		}
		if (error1 == true) {
			if (amount == 0)
				System.out.println("ERROR: Amount can not be zero, please try again. ");
			else if (amount < 0) {
				System.out.println("ERROR: Deposit amount can not be negative, please try again.\n");
				amount = 0;
			}
		}

		return amount;

	}
}
