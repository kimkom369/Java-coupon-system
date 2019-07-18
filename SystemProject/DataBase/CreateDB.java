package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import Test.Tester;


public class CreateDB {
//This class is the main tool to build tables and Delete them in the project 
	
// In case of an issue, SQLException is activated.
// Finally connection closed and return to pool.
     
	public static void main(String[] args) { 
		//This method receives a connection to DataBase from the ConnectionPool and creates a statement.
		String TheDriver = "org.apache.derby.jdbc.ClientDriver"; // the path of the driver
		try {
			Class.forName(TheDriver); 
			System.out.println("driver is loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String conn = "jdbc:derby://localhost:1527/MyProject;create=true"; //=====> the path to the connnection
		try (Connection con = DriverManager.getConnection(conn); 
				Statement stm = con.createStatement();) { 
			System.out.println("connected to : " + conn); 
			
			//Create COMPANY TABLE//====>>> This table holds the data of company objects.
			String sql = "create table Company (ID bigint primary key,";
			sql+= "COMP_NAME varchar (25) ,";
			sql+= "PASSWORD varchar (25) ,";
			sql+= "EMAIL varchar (35) )";
		  
			//CREATE CUSTOMER TABLE//=====> This table holds the data of customer objects.
			String sql2 = "create table Customer (ID bigint primary key,";
			sql2+="CUST_NAME varchar (25) ,";
			sql2+= "PASSWORD varchar (25) )";
			
			//CREATE COUPON TABLE//=======> This table holds the data of coupon objects.
			String sql3 = "create table Coupon (ID bigint primary key,";
			sql3+= "TITLE varchar (25) ,";
			sql3+= "START_DATE date ,";
			sql3+= "END_DATE date ,";
			sql3+= "AMOUNT integer ,";
			sql3+= "TYPE varchar (25) ,";
			sql3+= "MESSAGE varchar (25) ,";
			sql3+= "PRICE float ,";
			sql3+= "IMAGE varchar (500) )";
		   
			//CREATE JOIN TABLE CUSTOMER_COUPON//=====> This table holds the data of customer_coupon objects.
			String sql4 = "create table Customer_Coupon (CUST_ID bigint ,COUPON_ID bigint, ";
			sql4+= "PRIMARY KEY (CUST_ID, COUPON_ID), FOREIGN KEY(CUST_ID) REFERENCES CUSTOMER(ID), FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)";// FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)
		    //FOREIGN KEY(CUST_ID) REFERENCES CUSTOMER(ID), FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)"
			
			//CREATE JOIN TABLE COMPANY_COUPON//======> This table holds the data of company_coupon objects.
			String sql5 = "create table Company_Coupon (COMP_ID bigint ,COUPON_ID bigint , ";
			sql5+= "PRIMARY KEY (COMP_ID, COUPON_ID), FOREIGN KEY(COMP_ID) REFERENCES COMPANY(ID), FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)"; 
			
		   //FOREIGN KEY(COMP_ID) REFERENCES COMPANY(ID), FOREIGN KEY(COUPON_ID) REFERENCES COUPON(ID), PRIMARY KEY (COMP_ID, COUPON_ID))")
			
			//EXECUTION OF ALL THE SQL STATEMENTS//
			stm.executeUpdate(sql);     //=====> SQL query for create Company table is executed
			stm.executeUpdate(sql2);   //======> SQL query for create customer table is executed
			stm.executeUpdate(sql3);  //======> SQL query for create coupon table is executed
			stm.executeUpdate(sql4); //======> SQL query for create customer-coupon table is executed
			stm.executeUpdate(sql5);//======> SQL query for create company-coupon table is executed
		    System.out.println("Tables: Company,Customer,Coupon,Customer-coupon,Company-coupon are created");
		} catch (SQLException e) { //SQLException is activated if theres any problems
			e.printStackTrace();
		     
		      Tester.main(null);
	     
			}
		}
	}

