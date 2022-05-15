package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



//data base connection

public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//insert

		public String insertPayment(String CustomerName, String BillAmount, String AccountNo)  
		{   
			String output = ""; 
		 
			try   
			{    
				Connection con = connect(); 
		 
				if (con == null)    
				{return "Error while connecting to the database for inserting."; } 
		 
				// create a prepared statement 
				String query = " insert into pay (PaymentID , CustomerName , BillAmount , AccountNo)"+ " values (?, ?, ?, ?)"; 
		 
		 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				// binding values    
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, CustomerName);
				 preparedStmt.setString(3, BillAmount);
				 preparedStmt.setString(4, AccountNo);
				 
				
				// execute the statement    
				preparedStmt.execute();    
				con.close(); 
		   
				String newPayment = readPayment(); 
				output =  "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
			}   
			catch (Exception e)   
			{    
				output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";  
				System.err.println(e.getMessage());   
			} 
			
		  return output;  
		  
		}
		
		//read
		
		public String readPayment()  
		{   
			String output = ""; 
		
			try   
			{    
				Connection con = connect(); 
			
				if (con == null)    
				{
					return "Error while connecting to the database for reading."; 
				} 
		 
				// Prepare the html table to be displayed    
				output = "<table border=\'1\'><tr><th>CustomerName</th><th>BillAmount</th><th>AccountNo</th><th>Update</th><th>Remove</th></tr>";
		 
				String query = "select * from pay"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
		 
				// iterate through the rows in the result set    
				while (rs.next())    
				{     
					String PaymentID = Integer.toString(rs.getInt("PaymentID")); 
					 String CustomerName = rs.getString("CustomerName");
					 String BillAmount = rs.getString("BillAmount");
					 String AccountNo = rs.getString("AccountNo");
					 
		 
					// html table values
					output += "<tr><td><input id=\'hidPaymentIDUpdate\' name=\'hidPaymentIDUpdate\' type=\'hidden\' value=\'" + PaymentID + "'>" 
								+ CustomerName + "</td>"; 
					output += "<td>" + BillAmount + "</td>";
					output += "<td>" + AccountNo + "</td>";
					

					  
		 
					// table buttons     
					output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iid='" + PaymentID + "'>" + "</td></tr>"; 
				
				}
				con.close(); 
		 
				// Complete the html table    
				output += "</table>";   
			}   
			catch (Exception e)   
			{    
				output = "Error while reading the Payment.";    
				System.err.println(e.getMessage());   
			} 
		 
			return output;  
		}
		
		//update
		
		public String updatePayment(String PaymentID, String CustomerName, String BillAmount, String AccountNo)  
		{   
			String output = ""; 
		 
			try   
			{    
				Connection con = connect(); 
		 
				if (con == null)    
				{return "Error while connecting to the database for updating."; } 
		 
				// create a prepared statement    
				String query = "UPDATE pay SET CustomerName=?,BillAmount=?,AccountNo=? WHERE PaymentID=?"; 
		 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				// binding values    
				preparedStmt.setString(1, CustomerName);
				 preparedStmt.setString(2, BillAmount);
				 preparedStmt.setString(3, AccountNo);
				 preparedStmt.setInt(4, Integer.parseInt(PaymentID)); 
		 
				// execute the statement    
				preparedStmt.execute();    
				con.close(); 
		 
				String newPayment = readPayment();    
				output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
			}   
			catch (Exception e)   
			{    
				output =  "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";   
				System.err.println(e.getMessage());   
			} 
		 
		  return output;  
		} 
		
		//delete
		
		public String deletePayment(String PaymentID)   
		{   
			String output = ""; 
		 
			try   
			{    
				Connection con = connect(); 
		 
				if (con == null)    
				{
					return "Error while connecting to the database for deleting."; 
					
				} 
		 
				// create a prepared statement    
				String query = "delete from pay where PaymentID=?";  
		 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				// binding values    
				preparedStmt.setInt(1, Integer.parseInt(PaymentID)); 
		 
				// execute the statement    
				preparedStmt.execute();    
				con.close(); 
		 
				String newPayment = readPayment();  
				    
				output = "{\"status\":\"success\", \"data\": \"" +  newPayment + "\"}";    
			}   
			catch (Exception e)   
			{    
				output = "Error while deleting the Innovator.";    
				System.err.println(e.getMessage());   
			} 
		 
			return output;  
		}

}
