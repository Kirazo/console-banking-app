package project.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.bank.utility.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {
	Connection con = DBConnection.getConnection(); // Connecting to database
	
	@Override
	public void transLog(int id1, int id2, int amount) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("insert into transactions values(nextval('transId_sequence'), ?, ?, ?, current_date)");
			statement.setInt(1, id1);
			statement.setInt(2, id2);
			statement.setInt(3, amount);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean viewTransLog() {
		PreparedStatement statement = null;
		boolean success = false;
		try {
			statement = con.prepareStatement("select * from transactions order by transId");
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				success = true;
				System.out.println("Transaction Id: " + res.getInt(1) + " | " + "Sender Id: " + res.getInt(2)
									+ " | " + "Receiver Id: " + res.getInt(3) + " | " + "Amount: $" + 
									+ res.getInt(4) + " | " + "Date: " + res.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

}
