package project.bank.model;

import java.util.Objects;

public class Customer {
	private int custId;
	private String custfName;
	private String custlName;
	private String custPw;
	private int balance;
	private String custApproved;

	public Customer() {

	}

	public Customer(int custId, String custfName, String custlName, String custPw, int balance, String custApproved) {
		super();
		this.custId = custId;
		this.custfName = custfName;
		this.custlName = custlName;
		this.custPw = custPw;
		this.balance = balance;
		this.custApproved = custApproved;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustfName() {
		return custfName;
	}

	public void setCustfName(String custfName) {
		this.custfName = custfName;
	}

	public String getCustlName() {
		return custlName;
	}

	public void setCustlName(String custlName) {
		this.custlName = custlName;
	}

	public String getCustPw() {
		return custPw;
	}

	public void setCustPw(String custPw) {
		this.custPw = custPw;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCustApproved() {
		return custApproved;
	}

	public void setCustApproved(String custApproved) {
		this.custApproved = custApproved;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, custApproved, custId, custPw, custfName, custlName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return balance == other.balance && Objects.equals(custApproved, other.custApproved) && custId == other.custId
				&& Objects.equals(custPw, other.custPw) && Objects.equals(custfName, other.custfName)
				&& Objects.equals(custlName, other.custlName);
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custfName=" + custfName + ", custlName=" + custlName + ", custPw="
				+ custPw + ", balance=" + balance + ", custApproved=" + custApproved + "]";
	}
	
	
}
