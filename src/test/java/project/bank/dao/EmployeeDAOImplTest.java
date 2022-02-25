package project.bank.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import project.bank.model.Employee;
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class EmployeeDAOImplTest {
	EmployeeDAO employeeDAO;
	Employee employee;
	int employeeId;
	String fName;
	String lName;
	String pw;
	
	@BeforeEach
	void setUp() throws Exception {
		employeeDAO = new EmployeeDAOImpl();
		employeeId = -1;//Temp value
		fName = "FNameTest";
		lName = "LNameTest";
		pw = "PwTest";
		
	}
	
	@Test
	@Order(value = 1)
	@DisplayName("Testing adding employee")
	void testAddEmployee() {
		employee = new Employee(employeeId, fName, lName, pw);
		employeeDAO.addEmployee(employee);
	}
	
	@Test
	@Order(value = 2)
	@DisplayName("Testing adding employee")
	void testCredEmployee() {
		assertTrue(employeeDAO.doesEmpCredExist(fName, lName, pw));
	}

}
