package project.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.bank.model.Employee;
import project.bank.utility.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	Connection con = DBConnection.getConnection();
	
	
	@Override
	public boolean addEmployee(Employee employee) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was implemented or not
		try {
			statement = con.prepareStatement("insert into employee values(nextval('id_sequence'),?,?,?)");
			statement.setString(1, employee.getEmpfName());
			statement.setString(2, employee.getEmplName());
			statement.setString(3, employee.getEmpPw());
			statement.executeUpdate();
			// To get the information of customer that was just inserted
			statement = con.prepareStatement(
					"select * from employee where empfName = '" + employee.getEmpfName() + "'" + " and emplName = '"
					+ employee.getEmplName() + "'" + " and empPw = '" + employee.getEmpPw() + "'");
			ResultSet res = statement.executeQuery();
			res.next();
			employee.setEmpId(res.getInt(1));
			System.out.println("\nCongratulations " + res.getString(2)
			+ ", you have successfully created your employee account. \nYour employee id is " +
					res.getInt(1) + ", please sign in using your employee id and password.");
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
	
	@Override
	public boolean doesEmpCredExist(String fn, String ln, String pw) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was implemented or not
		try {
			statement = con
					.prepareStatement("select * from employee where lower(empfName) = ? and lower(emplName) = ? and lower(empPw) = ?");
			statement.setString(1, fn.toLowerCase());
			statement.setString(2, ln.toLowerCase());
			statement.setString(3, pw.toLowerCase());
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				success = true;
			}
			else {
				success = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean doesEmpExist(int empId) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer exists or not
		try {
			statement = con.prepareStatement("select * from employee where empId = ?");
			statement.setInt(1, empId);
			ResultSet res = statement.executeQuery();
			success = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean checkUserCred(int empId, String empPw) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was implemented or not
		try {
			statement = con.prepareStatement("select * from employee where empId = ? and empPw = ?");
			statement.setInt(1, empId);
			statement.setString(2, empPw);
			ResultSet res = statement.executeQuery();
			success = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public String getEmpfn(int id) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from employee where empId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			return res.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getEmpln(int id) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from employee where empId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			return res.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean hasAppr(int id) {
		PreparedStatement statement = null;
		boolean success = false;
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			if(res.getString(6).equals("Yes"))
				success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean removeCust(int id) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was deleted or not
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			System.out.println("Customer " + res.getString(2) + " " + res.getString(3) + " with ID: " + id
					+ " has been rejected.\nAccount will be deleted.");

			statement = con.prepareStatement("delete from customer where custId = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean approveCust(int id) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was deleted or not
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			System.out.println("Customer " + res.getString(2) + " " + res.getString(3) + " with ID: " + id
					+ " has been approved.\nUser will be updated.");

			statement = con.prepareStatement("update customer set custApproved = ? where custId = ?");
			statement.setString(1, "Yes");
			statement.setInt(2, id);
			statement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public void empView(int id) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was deleted or not
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			System.out.println("Customer ID: " + res.getInt(1) + " | First Name: "
					+ res.getString(2) + " | Last Name: " + res.getString(3) + 
					" | Balance: " + res.getInt(5) + " | Approved: " + res.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
