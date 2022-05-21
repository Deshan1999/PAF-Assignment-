package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/egpower?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String pay_method, String account_no, String pay_date, String pay_amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into paymentpower(`pay_ID`,`pay_method`,`account_no`,`pay_date`,`pay_amount`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pay_method);
			preparedStmt.setString(3, account_no);
			preparedStmt.setString(4, pay_date);
			preparedStmt.setString(5, pay_amount);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Payment Method</th><th>Account No</th><th>Date</th><th>Payment Amount</th></tr>";
			String query = "select * from paymentpower";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pay_ID = Integer.toString(rs.getInt("pay_ID"));
				String pay_method = rs.getString("pay_method");
				String account_no = rs.getString("account_no");
				String pay_date = rs.getString("pay_date");
				String pay_amount = rs.getString("pay_amount");

				output += "<tr><td>" + pay_ID + "</td>";
				output += "<td>" + pay_method + "</td>";
				output += "<td>" + account_no + "</td>";
				output += "<td>" + pay_date + "</td>";
				output += "<td>" + pay_amount + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String pay_ID, String pay_method, String account_no, String pay_date, String pay_amount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE paymentpower SET pay_method=?,account_no=?,pay_date=?,pay_amount=? WHERE pay_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, pay_method);
			preparedStmt.setString(2, account_no);
			preparedStmt.setString(3, pay_date);
			preparedStmt.setString(4, pay_amount);
			preparedStmt.setInt(5, Integer.parseInt(pay_ID));

			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String pay_ID) {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
		
			String query = "delete from paymentpower where pay_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pay_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
