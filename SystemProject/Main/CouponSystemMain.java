package Main;


import java.util.Arrays;


import DAO.CompanyDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import DataBase.ConnectionPool;
import DataBase.DataBase;
import Exceptions.MainSystemException;
import Facades.AdminFacade;
import Facades.CompanyFacade;
import Facades.CouponClientFacade;
import Facades.CustomerFacade;
import JavaBeans.ClientType;


public class CouponSystemMain {
	//this class contains constructors for all the systems functions
   //creating tables , building a connections and starting the dailyTask 
  //
	
	
	public static void BuildDB() throws Exception {
	
			System.out.println("Welcome to Coupon System");
			try {
				DataBase.BuildDB();
			} catch (Exception e) {
				System.out.println("DB already exists");
			}

		}


	private CompanyDAO companyDAO = new CompanyDBDAO();
	
	private CustomerDAO customerDAO = new CustomerDBDAO();
	
	private DailyCouponExpirationTask task = new DailyCouponExpirationTask();
	
	public Thread dailyTask = new Thread(task);
	
	private static CouponSystemMain instance;
	
	private CouponSystemMain() throws MainSystemException {
		ConnectionPool.getInstance();
		dailyTask.start();
	}
	
	public synchronized static CouponSystemMain getInstance() throws MainSystemException {
		if (instance == null) { 
			instance = new CouponSystemMain();
		}
		return instance;
	}
	public CouponClientFacade login(String name, String password, ClientType clientType) throws MainSystemException {
		try {
			switch (clientType) {
			case ADMIN:
				if (name.equals("admin") && password.equals("1234")) {
					return new AdminFacade();
				}
				throw new MainSystemException("Error: user name or password incurect. please try again");
			case COMPANY:
				if (companyDAO.login(name, password)) {
					return new CompanyFacade(companyDAO.getCompanyByName(name).getId());
				}
				throw new MainSystemException("Error: user name or password incurect. please try again");
			case CUSTOMER:
				if (customerDAO.login(name, password)) {
					return new CustomerFacade(customerDAO.getCustomerByName(name).getId());
				}
				throw new MainSystemException("Error: user name or password incurect. please try again");
			default:
				throw new MainSystemException(
						"Error: ClientType incurrect. please choose from: " + Arrays.toString(ClientType.values()));
			}
		} catch (MainSystemException e) {
			throw new MainSystemException("Error: problem while trying to login", e);
		}
	}

	public void shutdown() throws MainSystemException {
		dailyTask.interrupt();
		ConnectionPool.getInstance().closeAllConnections();
		System.out.println("Coupon System closed - connection pool closed and daily task stopped");

	}

}