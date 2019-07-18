//package Test;
//
//
//import java.sql.SQLException;
//import java.sql.Time;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import org.apache.derby.client.am.DateTime;
//
//import DAO.CompanyDAO;
//import DAO.CouponDAO;
//import DAO.CustomerDAO;
//import DBDAO.CompanyDBDAO;
//import DBDAO.CouponDBDAO;
//import DBDAO.CustomerDBDAO;
//import DataBase.DataBase;
//import Exceptions.MainSystemException;
//import Facades.AdminFacade;
//import Facades.CompanyFacade;
//import Facades.CouponClientFacade;
//import Facades.CustomerFacade;
//import JavaBeans.ClientType;
//import JavaBeans.Company;
//import JavaBeans.Coupon;
//import JavaBeans.CouponType;
//import JavaBeans.Customer;
//import Main.CouponSystemMain;
//
//
//public class Test1 {
//	
//	public static void main(String[] args) throws MainSystemException {
//		
//		// CREATE DATABASE		
//		
////		  try {
////	    	   Class.forName(DataBase.getDriverConnection());
////	    		        DataBase.BuildDB();
////		  } catch (Exception e) {
////				e.printStackTrace();
////				System.out.println(e.getMessage());
////				System.out.println(e.getLocalizedMessage());
////				System.out.println(e.toString());
////			}
//		// DELETE DATABASE
////		  try {
////			  Class.forName(DataBase.getDriverConnection());
////			       DataBase.DropDB();
////		  } catch (Exception e) { 
////			  e.printStackTrace();
////			  System.out.println(e.getMessage());
////			  System.out.println(e.getStackTrace());
////		  }
//
//		CompanyDAO companyDAO = new CompanyDBDAO();
//		CustomerDAO customerDAO = new CustomerDBDAO();
//		CouponDAO couponDAO = new CouponDBDAO();
//		CouponSystemMain couponSystemMain = CouponSystemMain.getInstance();
//		
//		      System.out.println("THIS IS TEST #1");
//		
//		try {
//			CouponClientFacade couponClientFacade = couponSystemMain.login("admin", "1234", ClientType.ADMIN);
//			if (couponClientFacade instanceof AdminFacade) {
//				AdminFacade adminFacade = (AdminFacade) couponClientFacade;
//				
//				// ADMIN creates 5 companies//////////////////////////////////////////////////////////
//				System.out.println("*************************************************************");
//				System.out.println("ADMIN CREATED 5 NEW COMPANIES");
//				adminFacade.createCompany(new Company(1, "Microsoft", "1234", "Micro@gmail.com"));
//				adminFacade.createCompany(new Company(2, "Trivago", "2345", "trvl@gmail.com"));
//				adminFacade.createCompany(new Company(3, "Mcdonalds", "3456", "MaCd@gmail.com"));
//				adminFacade.createCompany(new Company(4, "Bodaskins", "4567", "Boda@gmail.com"));
//				adminFacade.createCompany(new Company(5, "Teva", "5678", "Teva@gmail.com"));
//				
//				
//				//adminFacade.removeCompany(companyDAO.getCompany(1));
//				
//               //  ADMIN creates 5 customer's//////////////////////////////////////////////////////////
//				System.out.println("******************************************************************");
//				System.out.println("ADMIN CREATED 5 NEW CUSTOMERS");
//				adminFacade.createCustomer(new Customer(452216, "Guy", "pAssW0rd123"));
//				adminFacade.createCustomer(new Customer(340034, "Oren", "2580"));
//				adminFacade.createCustomer(new Customer(359023, "Shani", "XYZQ"));
//				adminFacade.createCustomer(new Customer(503569, "Kobi", "JAvA"));
//				adminFacade.createCustomer(new Customer(219743, "Asaf", "43433"));
//	
//				
//			}
//			//problem with testing is trying to get the time work. when trying to create the customer coupon 
//			// the customer is linked to the customer_coupon with the coupon but they are not in correct time with each other.
//
//			// create coupons for each company/////////////////////////////////////////////////////////////////////////////////////
//			
//			//************ FIRST COUPON *********************************************************************
//			
//			System.out.println("*****************************************************************************");
//			System.out.println("COUPON #1");
//			CompanyFacade companyFacade = new CompanyFacade(companyDAO.getCompanyByName("Microsoft").getId());
//			companyFacade.createCoupon(new Coupon(111, "Microsoft office",new Date(), 
//					                   new GregorianCalendar(2019, Calendar.JUNE,16, 20,15).getTime(),
//				                       50, CouponType.TECHNOLOGY, "special offer",99.00,"img.jpg"));
//			
//			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			//************** SECOND COUPON ***************************INVALID COUPON ---> the main exception will show error.
//			
//			System.out.println("****************************************************************************************");
//			System.out.println("COUPON #2");
//			CompanyFacade companyFacade2 = new CompanyFacade(companyDAO.getCompanyByName("Trivago").getId()); 
//			companyFacade2.createCoupon(new Coupon(222, "Travel 30% off",new Date(),
//					                    new GregorianCalendar(2020, 5, 6, 20, 00).getTime(),
//					                                          300, CouponType.TRAVELLING, "deal of the year", 50.90,"img.jpg"));
//		  
//			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			//*********** THIRD COUPON *******************************************************************
//			
//			System.out.println("*********************************************************************************");
//			System.out.println("COUPON #3");
//			CompanyFacade companyFacade3 = new CompanyFacade(companyDAO.getCompanyByName("Mcdonalds").getId());
//			companyFacade3.createCoupon(new Coupon(33333, "Mcdonalds", new Date(),
//					                    new GregorianCalendar(2019,Calendar.JUNE, 16, 20, 50).getTime(),
//					                                           10, CouponType.RESTURANTS, "70% off",20.00,"img.jpg"));
//			
//			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			//*********** FOURTH COUPON *********************************************************************
//			
//			System.out.println("***************************************************************************************");
//			System.out.println("COUPON #4");
//			CompanyFacade companyFacade4 = new CompanyFacade(companyDAO.getCompanyByName("Bodaskins").getId());
//			companyFacade4.createCoupon(new Coupon(44444, "Bodaskins",new Date(),
//					                    new GregorianCalendar(2021, 5, 16, 18, 50).getTime(),
//							                                55, CouponType.TECHNOLOGY, "limit edition", 400,"img.jpg"));
//			
//			 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			//*********** FIFTH COUPON *******************************************************************
//			
//			System.out.println("**************************************************************************************");
//			System.out.println("COUPON #5");
//			CompanyFacade companyFacade5 = new CompanyFacade(companyDAO.getCompanyByName("Teva").getId());
//			companyFacade5.createCoupon(new Coupon(55555, "Teva", new Date(),
//					                    new GregorianCalendar(2020, 5, 16, 18, 50).getTime(),
//					                                         15, CouponType.TRAVELLING, "special", 1199,"img.jpg"));
//		    
//			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			
//			
//			System.out.println("*************************************************************************************");
//			System.out.println("CUSTOMERS PURCHASE COUPONS");
//			System.out.println();
//			CustomerFacade customerFacade1 = new CustomerFacade(452216); 
//			customerFacade1.purchaseCoupon(couponDAO.getCoupon(111));  // links the customer and the coupon via ID
//			System.out.println("GUY PURCHASED COUPON: MICROSOFT- end date is 16/6/2019 19:30 ");
//			System.out.println();
//			
//			CustomerFacade customerFacade2 = new CustomerFacade(340034); //Customer Oren has purchased a coupon.
//			customerFacade1.purchaseCoupon(couponDAO.getCoupon(222)); 
//			System.out.println("OREN PURCHASED COUPON: TRIVAGO- end date is 6/6/2021 18:50");
//			System.out.println();
//			
//			CustomerFacade customerFacade3 = new CustomerFacade(359023);
//			customerFacade3.purchaseCoupon(couponDAO.getCoupon(55555));
//			System.out.println("KOBI PURCHASED COUPON: TEVA- end date is 6/6/2020 18:50");
//			System.out.println();
//			
//			
//			System.out.println("UNLINK COUPONS 111 & 333 - both expired coupon");
//			
//			
//			
//			
//			
//			
//	    
//		} catch (MainSystemException e) { 
//			e.printStackTrace();
//		}
//	
//		// after 15 minutes the coupon system will shut down	
//		finally {
//			try {
//				Thread.currentThread();
//				Thread.sleep(600000); 
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			if (couponSystemMain != null) {
//				couponSystemMain.shutdown();
//			}
//		}
//	}
//}
//		
//		 
