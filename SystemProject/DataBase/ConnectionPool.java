package DataBase;

// This class is a singelton class that manages all the connections 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import Exceptions.MainSystemException;

public class ConnectionPool {
	//ATTRIBUTES 
	private Set<Connection> connections = new LinkedHashSet<>(); 
	public static final int MAX_CONNECTIONS = 10; // the max connection 
	private boolean bool; //this is the main code used for creating a sort of Que to end the connection
	private String url = "jdbc:derby://localhost:1527/MyProject;create=true";  // the url connecting string
	private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver"; // the driver connecting string

	private ConnectionPool() throws MainSystemException { 
		try {
			Class.forName(DRIVER); 
		} catch (ClassNotFoundException e) {
			String y = "Could not connect to ConnectionPool - the driver is not found";
			throw new MainSystemException(y, e); // the args that are in the MainSystemException
		} 
		for (int i = 0; i < MAX_CONNECTIONS; i++) { // using "for" loop for the connection
			try {
				connections.add(DriverManager.getConnection(url)); 
			} catch (SQLException e) {
				String y = "Could not create ConnectionPool"; //this message will appear if the connectiong is not found
				throw new MainSystemException(y, e); 
			}
		}
		bool = true; // main use
		
	}

	private static ConnectionPool instance;

	public synchronized static ConnectionPool getInstance() throws MainSystemException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	// METHODS
	public synchronized Connection getConnection() throws MainSystemException {
		if (!bool) {
			throw new MainSystemException("get connection faild - connection pool close");
		}
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				String x = "get Connection faild";
				throw new MainSystemException(x, e);
			}
		}
		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove();
		notify();
		return con;
	}

	public synchronized void returnConnection(Connection con) throws MainSystemException {
		if (connections.size() == MAX_CONNECTIONS) {
			try {
				wait();
			} catch (InterruptedException e) {
				String x = "return Connection faild";
				throw new MainSystemException(x, e);
			}
		}
		connections.add(con);
		notify();
	}

	public synchronized void closeAllConnections() throws MainSystemException {
		bool = false; // original code
		while (connections.size() < MAX_CONNECTIONS) { 
			try {
				wait();
			} catch (InterruptedException e) {
				String x = "close all connections faild";
				throw new MainSystemException(x, e);
			}
		}
		Iterator<Connection> it = connections.iterator();
		while (it.hasNext()) {
			try {
				it.next().close();
				it.remove();
			} catch (SQLException e) {
				String x = "close all connections faild";
				throw new MainSystemException(x, e);
			}
		}
		instance = null;
	}
}
