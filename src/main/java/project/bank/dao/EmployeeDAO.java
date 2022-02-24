package project.bank.dao;

import project.bank.model.Employee;

public interface EmployeeDAO {
	public boolean addEmployee(Employee employee);

	public boolean doesEmpCredExist(String fn, String ln, String pw);

	public boolean doesEmpExist(int empId);

	public boolean checkUserCred(int empId, String empPw);

	public String getEmpfn(int id);

	public String getEmpln(int id);
	
	public boolean hasAppr(int id);
	
	public boolean removeCust(int id);
	
	public boolean approveCust(int id);
	
	public void empView(int id);

}
