package project.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.bank.model.Customer;
import project.bank.utility.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {
	Connection con = DBConnection.getConnection(); // Connecting to database

	@Override
	public boolean addCustomer(Customer customer) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was implemented or not
		try {
			statement = con.prepareStatement("insert into customer values(nextval('id_sequence'),?,?,?,?,?)");
			statement.setString(1, customer.getCustfName());
			statement.setString(2, customer.getCustlName());
			statement.setString(3, customer.getCustPw());
			statement.setInt(4, customer.getBalance());
			statement.setString(5, customer.getCustApproved());
			statement.executeUpdate();
			// To get the information of customer that was just inserted
			statement = con.prepareStatement(
					"select * from customer where custfName = '" + customer.getCustfName() + "'" + " and custlName = '"
							+ customer.getCustlName() + "'" + " and custPw = '" + customer.getCustPw() + "'");
			ResultSet res = statement.executeQuery();
			res.next();
			customer.setCustId(res.getInt(1));// Setting customer id to nextval in database table
			System.out.println("\nCongratulations " + res.getString(2)
					+ ", you have successfully opened your bank account.\nYour customer id is " + res.getInt(1)
					+ ", please sign in using your customer id and password.");
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}


	@Override
	public boolean doesCustExist(int custId) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer exists or not
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, custId);
			ResultSet res = statement.executeQuery();
			success = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean doesCustCredExist(String custPw, String fName, String lName) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was implemented or not
		try {
			statement = con.prepareStatement(
					"select * from customer where lower(custPw) = ? and lower(custfName) = ? and lower(custlName) = ?");
			statement.setString(1, custPw.toLowerCase());
			statement.setString(2, fName.toLowerCase());
			statement.setString(3, lName.toLowerCase());
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				success = true;
			} else {
				success = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean checkUserCredentials(int id, String custPw) {
		PreparedStatement statement = null;
		boolean success = false;// To decide whether customer was implemented or not
		try {
			statement = con.prepareStatement("select * from customer where custId = ? and custPw = ?");
			statement.setInt(1, id);
			statement.setString(2, custPw);
			ResultSet res = statement.executeQuery();
			success = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public String getCustomerfn(int id) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
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
	public String getCustomerln(int id) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
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
	public int getCustomerBalance(int id) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			return res.getInt(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deposit(int id, int amount) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("update customer set balance = balance + ? where custId = ?");
			statement.setInt(1, amount);
			statement.setInt(2, id);
			statement.executeUpdate();
			System.out.println("You have depositted $" + amount + ", your new balance is $" + getCustomerBalance(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void withdraw(int id, int amount) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("update customer set balance = balance - ? where custId = ?");
			statement.setInt(1, amount);
			statement.setInt(2, id);
			statement.executeUpdate();
			System.out.println("You have withdrawn $" + amount + ", your new balance is $" + getCustomerBalance(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void transfer(int id1, int id2, int amount) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("update customer set balance = balance - ? where custId = ?");
			statement.setInt(1, amount);
			statement.setInt(2, id1);
			statement.executeUpdate();
			statement = con.prepareStatement("update customer set balance = balance + ? where custId = ?");
			statement.setInt(1, amount);
			statement.setInt(2, id2);
			statement.executeUpdate();
			System.out.println("You have successfully transferred $" + amount + " to the user with id: " + id2
					+ ", your new balance is $" + getCustomerBalance(id1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean custStatus(int id) {
		PreparedStatement statement = null;
		boolean success = false;
		try {
			statement = con.prepareStatement("select * from customer where custId = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			if (res.getString(6).equals("Yes"))
				success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

}
