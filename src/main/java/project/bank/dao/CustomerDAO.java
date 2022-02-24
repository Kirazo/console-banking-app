package project.bank.dao;

import project.bank.model.Customer;

public interface CustomerDAO {
	public boolean addCustomer(Customer customer);

	public boolean doesCustExist(int custId);

	public boolean doesCustCredExist(String custPw, String fn, String ln);

	public boolean checkUserCredentials(int id, String pw);

	public String getCustomerfn(int id);

	public String getCustomerln(int id);
	
	public int getCustomerBalance(int id);
	
	public void deposit(int id, int amount);
	
	public void withdraw(int id, int amount);
	
	public void transfer(int id1, int id2, int amount);
	
	public boolean custStatus(int id);
}
