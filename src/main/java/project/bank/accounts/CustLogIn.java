package project.bank.accounts;

import java.util.InputMismatchException;
import java.util.Scanner;

import project.bank.dao.CustomerDAO;
import project.bank.dao.CustomerDAOImpl;
import project.bank.pages.CustPersonalPage;

public class CustLogIn {
	Scanner scanner = new Scanner(System.in);
	CustPersonalPage c = new CustPersonalPage();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	int id = 0;//Id for signing in
	String pw = null;//Password for signing in
	String filler = "";//Filler text

	public void customerLogIn() {
		while (id == 0)
			id = enterId();

		while (pw == null)
			pw = enterPw(id);
		
		c.custPage(id);
	}

	public int enterId() {
		System.out.println("Please enter your customer id: ");
		try {
			id = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Your ID must contain only letters, please try again.\n");
			filler = scanner.nextLine();
			return id;
		}
		if (customerDAO.doesCustExist(id))
			return id;
		else {
			System.out.println("ERROR: This id is not in the system, plese try again.\n");
			id = 0;
			return id;
		}

	}

	public String enterPw(int id) {
		System.out.println("Enter your password: ");
		pw = scanner.next();
		if(customerDAO.checkUserCredentials(id, pw))
			return pw;
		else {
			System.out.println("ERROR: Incorrect password, please try again.\n");
			pw = null;
			return pw;	
		}
		
	}

}
