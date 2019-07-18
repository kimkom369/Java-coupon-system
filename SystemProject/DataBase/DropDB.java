package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// this class is in charge of deleting the tables from the database
public class DropDB { 

	public static void main(String[] args) {
		//This method receives a connection to DataBase from the ConnectionPool and creates a statement.
		String TheDriver = "org.apache.derby.jdbc.ClientDriver"; // the path of the driver
		try {
			Class.forName(TheDriver); 
			System.out.println("driver is loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String conn = "jdbc:derby://localhost:1527/MyProject;create=true"; 
		try (Connection con = DriverManager.getConnection(conn); 
				Statement stm2 = con.createStatement();) {
			System.out.println("connected to : " + conn); 
			
			String sql1 ="drop table APP.COMPANY_COUPON"; //==========> delets company-coupon table from the APP.schemas
	
			String sql2="drop table APP.CUSTOMER_COUPON"; //==========> delets customer-coupon table from the APP.schemas
			String sql3="drop table APP.CUSTOMER"; //==========> delets customer table from the APP.schemas
			String sql4="drop table APP.COUPON"; //==========> delets coupon table from the APP.schemas
			String sql5="drop table APP.COMPANY";//==========> delets company table from the APP.schemas
			
			stm2.execute(sql1);    //===> SQL query for drop APP.Company table is executed
			stm2.execute(sql2);   //===> SQL query for drop APP.Company-coupon table is executed
			stm2.execute(sql3);  //===> SQL query for drop APP.Customer-coupon table is executed
			stm2.execute(sql4); //===> SQL query for drop APP.Customer table is executed
			stm2.execute(sql5);//===> SQL query for drop APP.Coupon table is executed
		
		    System.out.println("All tables have been deleted"); 
		} catch (SQLException e) {
			e.printStackTrace();
		
		}	
	 }
}
	
