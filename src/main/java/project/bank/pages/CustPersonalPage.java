package project.bank.pages;

import java.util.InputMismatchException;
import java.util.Scanner;

import project.bank.dao.CustomerDAO;
import project.bank.dao.CustomerDAOImpl;
import project.bank.dao.TransactionDAO;
import project.bank.dao.TransactionDAOImpl;
import project.bank.howard.BankApp;

public class CustPersonalPage {
	Scanner scanner = new Scanner(System.in);
    BankApp App = new BankApp();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	TransactionDAO transactionDAO = new TransactionDAOImpl();
	int choice = 0;//Choice for choosing type
	String filler = "";//For input errors
	String approved = "no";//Customer approval status
	int dAmount = -1;//Deposit amount
	int wAmount = -1;//Withdraw amount
	int tAmount = -1;//Transfer amount
	int id2 = 0;//Account to transfer to
	boolean askTransfer = false;//Check is transfer is valid
	boolean loggedOut = false;
	String transferInput = "";
	public void custPage(int id) {
		System.out.println("\n=============================================================");
		System.out.println("Personal page for " + customerDAO.getCustomerfn(id) + " " +
							customerDAO.getCustomerln(id));
		approvalStatus(id);
		System.out.println("1. View Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Transfer Amount");
		System.out.println("8. Logout");
		System.out.println("9. Exit");
		System.out.println("Please enter your choice: ");
		try {
		choice = scanner.nextInt();
		} catch (InputMismatchException a) {	//Catches error if input is invalid
			filler = scanner.nextLine();
		}
		
		
		switch (choice) {
		case 1:
			System.out.println("Your balance is $" + customerDAO.getCustomerBalance(id));
			choice = 0;
			break;
			
		case 2:
			dAmount = -1;
			while(dAmount < 0)
				custDeposit(id);
			choice = 0;
			
			break;
			
		case 3:
			wAmount = -1;
			while(wAmount < 0)
				custWithdraw(id);
			choice = 0;
			break;
			
		case 4:
			tAmount = -1;
			while(tAmount <= 0)
				custTransfer(id);
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
			System.out.println("ERROR: Invalid choice, please try again\n");
			choice = 0;
			custPage(id);
			break;
		}
		if(loggedOut == false)
			custPage(id);
		
	}
	
	public void approvalStatus(int id) {
		if(customerDAO.custStatus(id))
			System.out.println("Account Status: APPROVED\n");
		else
			System.out.println("Account Status: AWAITING APPROVAL\n");
	}
	public void custDeposit(int id) {
		boolean transfer = true;
		System.out.println("How much would you like to deposit? ");
		try {
		dAmount = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: You must enter a valid amount to deposit, please try again\n");
			filler = scanner.nextLine();
			transfer = false;
			dAmount = -1;
		}
		if(transfer == true) {
		if(dAmount == 0)
			System.out.println("You decided not to withdraw\n");
		else if(dAmount < 0)
			System.out.println("ERROR: You can not deposit a negative amount of money, please try again.\n");
		else 
			customerDAO.deposit(id, dAmount);
		}

	}
	
	public void custWithdraw(int id) {
		System.out.println("How much would you like to withdraw? Balance: $" + customerDAO.getCustomerBalance(id));
		try {
		wAmount = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: You must enter a valid amount to withdraw, please try again\n");
			filler = scanner.nextLine();
			wAmount = -1;
		}
		if(wAmount == 0)
			System.out.println("ERROR: You decided not to withdraw\n");
		else if(wAmount < 0)
			System.out.println("ERROR: You can not withdraw a negative amount of money, please try again.\n");
		else
			customerDAO.withdraw(id, wAmount);
		
	}
	
	public void custTransfer(int id1) {
		System.out.println("How much would you like to transfer? Balance: $" + customerDAO.getCustomerBalance(id1));
		try {
			tAmount = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: You must enter a valid amount to transfer, please try again.\n");
			filler = scanner.nextLine();
		}
		if(tAmount <= 0)
			System.out.println("ERROR: You can not transfer 0 or a negative amount of money, please try again.\n");
		else
			askTransfer = true;
		
		if(askTransfer == true) {
			if(tAmount <= customerDAO.getCustomerBalance(id1)) {
				askTransfer = true;
			}
			else {
				askTransfer = false;
				System.out.println("ERROR: You can not transfer an amount that " + 
									"would put you at negative balance.\nPlease try again.\n");
				tAmount = -1;
			}
		}
		
		
		if(askTransfer == true) {
			System.out.println("Enter the id of the account you would like to transfer funds to: ");
			try {
				id2 = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("ERROR: You must enter a valid account number, please try again.\n");
				askTransfer = false;
				filler = scanner.nextLine();
				tAmount = -1;
			}
			if(id2 < 0) {
				System.out.println("ERROR: id can not be a negative number, please try again.\n");
				id2 = 0;
				tAmount = -1;
				askTransfer = false;
			}
			else if(customerDAO.doesCustExist(id2)) {
				System.out.println("Are you sure you would like to transfer $" + tAmount + " to user "
										+ "with id: " + id2 + "?");
				System.out.println("Type 'Yes' to confirm or any other input to cancel.");
				transferInput = scanner.next();
				if(transferInput.equals("Yes")) {
					customerDAO.transfer(id1, id2, tAmount);
					transactionDAO.transLog(id1, id2, tAmount);
					askTransfer = false;
				}
				else {
					System.out.println("You have cancelled the transaction.");
					askTransfer = false;
				}
			}
			else {
				System.out.println("ERROR: User with id: " + id2 + " could not be found in the " +
									"system.\nPlease try again.\n");
				tAmount = -1;
				askTransfer = false;
			}
				
		}
		askTransfer = false;
		
	}
}
