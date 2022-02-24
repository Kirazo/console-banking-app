package project.bank.model;

import java.util.Objects;

public class Employee {
	private int empId;
	private String empfName;
	private String emplName;
	private String empPw;

	public Employee() {
		
	}

	public Employee(int empId, String empfName, String emplName, String empPw) {
		super();
		this.empId = empId;
		this.empfName = empfName;
		this.emplName = emplName;
		this.empPw = empPw;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpfName() {
		return empfName;
	}

	public void setEmpfName(String empfName) {
		this.empfName = empfName;
	}

	public String getEmplName() {
		return emplName;
	}

	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}

	public String getEmpPw() {
		return empPw;
	}

	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId, empPw, empfName, emplName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return empId == other.empId && Objects.equals(empPw, other.empPw) && Objects.equals(empfName, other.empfName)
				&& Objects.equals(emplName, other.emplName);
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empfName=" + empfName + ", emplName=" + emplName + ", empPw=" + empPw
				+ "]";
	}	

	
}
