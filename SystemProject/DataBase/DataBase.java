package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {

	// the DataBase connection port
	private static String connectionString = "jdbc:derby://localhost:1527/Coupon;create=true";
	// the DataBase derby driver 
	private static String DriverConnection = "org.apache.derby.jdbc.ClientDriver";
	
	/* Static connectionPool Object */
	private static ConnectionPool connectionPool;

	
	//Get method for DB connection port 
	public static String getConnectionString() {
		return connectionString; 
	}

	// Get method for DB derby driver 
	public static String getDriverConnection() {
		return DriverConnection;
	}

	
	
	/*
	 * Create Company table method:
	 * This table holds the data of companies objects.
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for create Company table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void createCompanyTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		
		String sql = "create table Company (ID bigint primary key Not null ,"
			+"COMP_NAME varchar (25) not null ,"
			+"PASSWORD varchar (25) not null ,"
			+"EMAIL varchar (35) not null )";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.executeUpdate();

			System.out.println("Company table has been created");

		} catch (SQLException e) {
			throw new Exception("unable to create Company table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}

	}

	
	/*
	 * Create Customer table method:
	 * This table holds the data of customers objects.
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for create Customer table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void createCustomerTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		
		
		String sql = "create table Customer (ID bigint primary key ,"
		    + "CUST_NAME varchar (25) ,"
		    + "PASSWORD varchar (25) )";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.executeUpdate();

			System.out.println("Customer table has been created");

		} catch (SQLException e) {
			throw new Exception("unable to create Customer table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}

	}

//	
//	 Create Coupon table method:
//	 This table holds the data of coupons objects.
//	 This method receive connection to DB from connectionPool and create statement.
//	 Then SQL query for create Coupon table is executed. 
//	 If there is DB issue, SQLException is activated.
//	 Finally connection closed and return to pool.
//	 
	public static void createCouponTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		
		String sql = "create table Coupon (ID bigint primary key,"
			+ "TITLE varchar (25) ,"
			+ "START_DATE date ,"
			+ "END_DATE date ,"
			+ "AMOUNT integer ,"
			+ "TYPE varchar (25) ,"
			+ "MESSAGE varchar (25) ,"
			+ "PRICE float ,"
			+ "IMAGE varchar (500) )";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.executeUpdate();

			System.out.println("Coupon table has been created");

		} catch (SQLException e) {
			throw new Exception("unable to create Coupon table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}

	}

	/*
	 * Create Customer_Coupon table method:
	 * This table is Join table which combines two columns as unified primary key.  
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for create Customer_Coupon table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void createCustomer_CouponTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		
		String sql = "create table Customer_Coupon (CUST_ID bigint ,COUPON_ID bigint, "
		+ "PRIMARY KEY (CUST_ID, COUPON_ID), FOREIGN KEY(CUST_ID) REFERENCES CUSTOMER(ID), FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)";
	    //FOREIGN KEY(CUST_ID) REFERENCES CUSTOMER(ID), FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)"


		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.executeUpdate();

			System.out.println("Customer_Coupon table has been created");

		} catch (SQLException e) {
			throw new Exception("unable to create Customer_Coupon table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}

	}

	/*
	 * Create Company_Coupon table method:
	 * This table is Join table which combines two columns as unified primary key.  
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for create Company_Coupon table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void createCompany_CouponTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		
		String sql = "create table Company_Coupon (COMP_ID bigint ,COUPON_ID bigint , "
		+ "PRIMARY KEY (COMP_ID, COUPON_ID), FOREIGN KEY(COMP_ID) REFERENCES COMPANY(ID), FOREIGN KEY(COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.executeUpdate();

			System.out.println("Company_Coupon table has been created");

		} catch (SQLException e) {
			throw new Exception("unable to create Company_Coupon table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}

	}
	
	/*
	 * Create ExpiredCoupon table method:
	 * This table holds the data of coupons objects which expired.
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for create ExpiredCoupon table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	
	public static void dropCompanyTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();

		String sql = "drop table Company";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.executeUpdate();

			System.out.println("Company Table dropped successfully");

		} catch (SQLException e) {
			throw new Exception("unable to drop Company Table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}
	}

	/*
	 * Drop Customer table method:
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for drop Customer table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void dropCustomerTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();

		String sql = "drop table Customer";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.executeUpdate();

			System.out.println("Customer Table dropped successfully");

		} catch (SQLException e) {
			throw new Exception("unable to drop Customer Table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}
	}

	/*
	 * Drop Coupon table method:
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for drop Coupon table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void dropCouponTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();

		String sql = "drop table Coupon";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.executeUpdate();

			System.out.println("Coupon Table dropped successfully");

		} catch (SQLException e) {
			throw new Exception("unable to drop Coupon Table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}
	}

	/*
	 * Drop Customer_Coupon table method:
	 * This method receive connection to DB and from connectionPool create statement.
	 * Then SQL query for drop Customer_Coupon table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void dropCustomer_CouponTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();

		String sql = "drop table Customer_Coupon";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.executeUpdate();

			System.out.println("Customer_Coupon Table dropped successfully");

		} catch (SQLException e) {
			throw new Exception("unable to drop Customer_Coupon Table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}
	}

	/*
	 * Drop Company_Coupon table method:
	 * This method receive connection to DB from connectionPool and create statement.
	 * Then SQL query for drop Company_Coupon table is executed. 
	 * If there is DB issue, SQLException is activated.
	 * Finally connection closed and return to pool.
	 */
	public static void dropCompany_CouponTable() throws Exception {

		connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();

		String sql = "drop table Company_Coupon";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.executeUpdate();

			System.out.println("Company_Coupon Table dropped successfully");

		} catch (SQLException e) {
			throw new Exception("unable to drop Company_Coupon Table");
		} finally {
			connection.close();
			connectionPool.returnConnection(connection);
		}
	}

	public static void BuildDB() throws Exception {

		try {
			DataBase.createCompanyTable();
			DataBase.createCustomerTable();
			DataBase.createCouponTable();
			DataBase.createCompany_CouponTable();
			DataBase.createCustomer_CouponTable();
		} catch (SQLException e) {
			throw new Exception("unable to build all tables of DB");
		}
	}
	public static void DropDB() throws Exception {

		try {
			DataBase.dropCompany_CouponTable();
			DataBase.dropCustomer_CouponTable();
			DataBase.dropCompanyTable();
			DataBase.dropCustomerTable();
			DataBase.dropCouponTable();
		
		} catch (SQLException e) {
			throw new Exception("unable to drop all tables of DB");
		}
	}
}
