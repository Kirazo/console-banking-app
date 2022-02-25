package project.bank.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import project.bank.model.Customer;
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)

class CustEmpDAOImplTest {
	CustomerDAO customerDAO;
	Customer customer;
	int custId;
	String fName;
	String lName;
	String pw;
	int balance;
	String custApproved;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAOImpl();
		custId = -1; //Temp value
		fName = "FNameTest";
		lName = "LNameTest";
		pw = "PwTest";
		balance = 0;
		custApproved = "No";
	}
	@Test
	@Order(value = 1)
	@DisplayName("Testing adding customer")
	void testAddCustomer() {
		customer = new Customer(custId, fName, lName, pw, balance, custApproved);
		assertTrue(customerDAO.addCustomer(customer));
	}
	
	@Test
	@Order(value = 2)
	@DisplayName("Testing customer first name")
	void testCustCred() {
		assertTrue(customerDAO.doesCustCredExist(pw, fName, lName));
	}
	


}
