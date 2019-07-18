//package Test;
//
//import java.util.Date;
//import DAO.CompanyDAO;
//import DAO.CouponDAO;
//import DAO.CustomerDAO;
//import DBDAO.CompanyDBDAO;
//import DBDAO.CouponDBDAO;
//import DBDAO.CustomerDBDAO;
//import DataBase.ConnectionPool;
//import DataBase.DataBase;
//import Facades.AdminFacade;
//import Facades.CompanyFacade;
//import Facades.CustomerFacade;
//import JavaBeans.ClientType;
//import JavaBeans.Company;
//import JavaBeans.Coupon;
//import JavaBeans.CouponType;
//import JavaBeans.Customer;
//import Main.CouponSystemMain;
//
//public class Test2 {
//	
//	public static void main (String []args){
//
//		
//	
//		new Company();
//		new Customer();
//		CompanyDAO companyDAO = new CompanyDBDAO();
//		CustomerDAO customerDAO = new CustomerDBDAO();
//		CouponDAO couponDAO = new CouponDBDAO();
//	
//		AdminFacade admin;
//		CompanyFacade companyUser1; 
//		CompanyFacade companyUser2;
//		CustomerFacade customerUser1;
//		CustomerFacade customerUser2;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
//// *********************\\||START COUPON SYSTEM TEST||//*******************\\
//			
//	         	    System.out.println("//||START COUPON SYSTEM||\\");
//				    System.out.println("*************************************");
//				    System.out.println("Start system test: ");
//				  
//				
//				// CONNECTING TO TO THE COUPON SYSTEM
//				CouponSystemMain couponSystem = null;
//				try {
//					couponSystem = CouponSystemMain.getInstance();
//				} catch (Exception e) {
//				    System.out.println(e.getMessage());
//				}
//
//				try {
//					ConnectionPool.getInstance().getConnection(); // 
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
//// ***********************\\||ADMIN TEST||//**************************\\
//				
//				    System.out.println("*************************************");
//				    System.out.println();System.out.println();
//				    System.out.println("//||ADMIN TEST||\\");
//
//				    //1\\ ADMIN LOGIN 
//			   	 
//				try {
//					admin = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
//					
//					//3\\ ADMIN CREATES COMPANIES
//					System.out.println("*************************************");
//					System.out.println("3- add companies test:");
//					admin.createCompany(new Company(111, "Microsoft", "1223", "Micro@gmai.com"));
//					admin.createCompany(new Company(222, "Humongous", "pswrd123", "fat@gmail.com"));
//					admin.createCompany(new Company(333,"Trivago", "2580", "Trvl@gmail.com"));
//					admin.createCompany(new Company(444, "Renuar", "xccx168", "rnr1@gmail.com"));
//					
//					//4\\ PRINT ALL COMPANIES
//					System.out.println("*************************************");
//					System.out.println("4- Print all companies test: ");
//					System.out.println("List of companies: ");
//					admin.getAllCompanies();
//
//					//5\\ PRINT A COMPANY
//					System.out.println("*************************************");
//					System.out.println("5- Print a Company test: ");
//					admin.getCompany(111);
//
//					//6\\ REMOVE A COMPANY
////					System.out.println("*************************************");
////					System.out.println("6- Remove a Company test: ");
////					admin.removeCompany(companyDAO.getCompany(1));
////					System.out.println("List of companies after removing a company: ");
////					admin.getAllCompanies();
//
//					//7\\ UPDATE A COMPANY
//					System.out.println("*************************************");
//					System.out.println("7- Update a Company test: ");
//					admin.updateCompany(new Company(111, "Microsoft", "1234567", "micr0soft@gmail.com"));
//					System.out.println("Company after update:");
//					admin.getCompany(111);
//
//					//8\\ ADMIN CREATING CUSTOMERS
//					System.out.println("*************************************");
//					System.out.println("8- Add a Customer test:");
//					admin.createCustomer(new Customer(2001, "Kim", "20014"));
//					admin.createCustomer(new Customer(2002, "John", "Jh672"));
//					admin.createCustomer(new Customer(2003, "Kobi", "java123"));
//					admin.createCustomer(new Customer(2004, "Liran", "password"));
//				
//
//					
//
//					//9\\ PRINT ALL CUSTOMERS
//					System.out.println("*************************************");
//					System.out.println("9- Print all customers test: ");
//					System.out.println("List of customers: ");
//					admin.getAllCustomers();
//
//					//10\\ PRINT A CUSTOMER
//					System.out.println("*************************************");
//					System.out.println("10- Print a customer test:");
//					admin.getCustomer(2001);
//
////					//11\\ REMOVE A CUSTOMER
////					System.out.println("*************************************");
////					System.out.println("11- Remove a Customer test: ");
////					admin.removeCustomer(customerDAO.getCustomerByName("Mike"));
////					System.out.println("List of customers after removeing customer: ");
////					admin.getAllCustomers();
//
//					//12\\ UPDATE A CUSTOMER
//					System.out.println("*************************************");
//					System.out.println("12- Update a Customer test: ");
//					admin.updateCustomer(new Customer(2003, "Kobi", "JavaMaster123"));
//					System.out.println("Customer after update:");
//					admin.getCustomer(2003);
//
//					//13\\ ADD AN EXISTING COMPANY
//					System.out.println("*************************************");
//					System.out.println("13- try to add company with name which already exists: ");
//					admin.createCompany(new Company(111, "Microsoft", "1223", "Micro@gmai.com"));
//
//					//14\\ ADD AN EXISTING CUSTOMER
//					System.out.println("*************************************");
//					System.out.println("14- try to add customer with name which already exists");
//					admin.createCustomer(new Customer(2004, "Kim", "KimKo@gmail.com"));
//
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//////************************************************************************************************************************************************
////// *************************\\||COMPANY TEST||//**************************\\
//				
//				//LOGIN TEST
//				
//				   System.out.println("*************************************");
//				   System.out.println();System.out.println();
//				   System.out.println("\\||COMPANY TEST||//");
//
//				   //1\ Company LOGING ATTEMPT 	
//				   System.out.println("*************************************");
//				  
//				try {                                                            
//					companyUser1 = (CompanyFacade)couponSystem.login("Microsoft", "1234567", ClientType.COMPANY);
//					
//				//3\\ PRINT A COMPANY
//				System.out.println("*************************************");
//				System.out.println("3- Print Company test:");
//				companyDAO.getCompany(111);
//				
////**********************************\\||COUPON #1||//***************************************************************************************
//				System.out.println("*************************************");
//				System.out.println("4- Add coupon test:");
//				
//				companyUser1.createCoupon(new Coupon(1, "Microsoft office", 
//							new Date(119, 5, 12, 12, 30), 
//							new Date(119, 6, 12, 12, 30), 1, 
//							CouponType.TECHNOLOGY, "exclusive", 100.00, "img.jpg"));
////**********************************\\||COUPON #2||//**************************************************************************************
//					companyUser1.createCoupon(new Coupon(2, "Humongous", 
//							new Date(119, 4, 23, 18, 00), 
//							new Date(120, 6, 3, 2, 30), 200, 
//							CouponType.RESTURANTS, "half price", 50.00, "img.jpg"));
////*********************************\\||COUPON #3||//*****************************************************************************************
//					companyUser1.createCoupon(new Coupon(3, "Trivago fly", 
//							new Date(120, 3, 8, 12, 00), 
//							new Date(120, 6, 4, 12, 30), 30, 
//							CouponType.TRAVELLING, "30% off", 89.99, "img.jpg"));
////*********************************\\||COUPON #4||//******************************************************************************************
//					companyUser1.createCoupon(new Coupon(4, "Renuar clothing", 
//							new Date(119, 7, 20, 13, 20), 
//							new Date(119, 10, 13,10,30), 300, 
//							CouponType.FASHION, "buy 1 get 1 free", 20.00, "img.jpg"));
////*********************************\\||COUPON #5||//******************************************************************************************
//					
//					
//					//4\\ PRINT ALL COUPONS
//					System.out.println("*************************************");
//					System.out.println("4- Print all coupons test: ");
//					System.out.println("List of coupons: ");
//					companyUser1.getAllCoupons();
//					
//					//5\\ PRINT COUPONS BY TYPE
//					System.out.println("*************************************");
//					System.out.println("5- Print all coupons by type test: ");
//					System.out.println("List of coupons by type RESTAURANTS: ");
//					companyUser1.getCouponsByType(CouponType.RESTURANTS);
//					
//					//6\\PRINT COUPONS BY PRICE
//					System.out.println("*************************************");
//					System.out.println("6- Print all coupons by price limit test: ");
//					System.out.println("List of coupons by price limit 100.00: ");
//					companyUser1.getCouponsByPrice(100.00);
//					
//					//7\\ PRINT COUPONS BY DATE
//					System.out.println("*************************************");
//					System.out.println("7- Print all coupons by date test: ");
//					System.out.println("List of coupons until the date 2020/7/3: ");
//					companyUser1.getCouponsByDate(new Date(120, 6, 3, 2, 30));
//					
//					//8\\ REMOVE COUPON
////					System.out.println("*************************************");
////					System.out.println("8- Remove coupon test: ");
////					companyUser1.removeCoupon(couponDAO.getCoupon(1));
////					System.out.println("List of coupons after removing a coupon:");
////					companyUser1.getAllCoupons();
//					
//					
//					//9\\ UPDATE COUPON
//					System.out.println("*************************************");
//					System.out.println("9- Update a coupon test: ");
//					companyUser1.updateCoupon(new Coupon(2, "Humongous", 
//							new Date(119, 4, 23, 18, 00), 
//							new Date(120, 6, 5, 2, 30), 200, 
//							CouponType.RESTURANTS, "half price", 120.00, "img.jpg"));
//					companyUser1.getCoupon(2);
//					
//					
//					//10\\ ADD COUPON WITH SAME TITLE
//					System.out.println("*************************************");
//					System.out.println("10- try to add coupon with same title: ");
//					companyUser1.createCoupon(new Coupon(4, "Renuar clothing", 
//							new Date(119, 7, 20, 13, 20), 
//							new Date(119, 10, 13,10,30), 300, 
//							CouponType.FASHION, "buy 1 get 1 free", 20.00, "img.jpg"));
//					
//					//11\\ ADD COUPON WITH EXPIRED DATE
//					System.out.println("*************************************");
//					System.out.println("11- Try to add coupon with date that already passed: ");
//					companyUser1.createCoupon(new Coupon(5, "Nike", new Date(119, 3, 12, 12, 30), 
//							new Date(119, 4, 12, 12, 30), 300, CouponType.SPORTS, "equip yourself with the best gear",230.00, "img.jpg"));
//					
//					
//					//12\\ REMOVE COUPON WITH ANOTHER USER
////					System.out.println("*************************************");
////					System.out.println("12- Try to remove coupon of another company: ");
////					System.out.println("Login of another company and add coupon:");
////    				companyUser2 = (CompanyFacade) couponSystem.login("Example", "xmple2121", ClientType.COMPANY);
////					companyUser2.createCoupon(new Coupon(6, "OutDoor Gear", // user2 created a new coupon
////							new Date(119, 6, 14, 21, 30),
////							new Date(120, 4, 13, 15, 00), 34, 
////							CouponType.CAMPING, "camping lexurary", 1000, "img.jpg"));
//					
//					
////					//13\\ USER1 REMOVES USER2 COUPON
////					System.out.println("13- CompanyUser1 tries to remove coupon of  User2: ");
////					companyUser1.removeCoupon(couponDAO.getCoupon(6));
//					
//					
//					//14\\ UPDATE EXPIRED COUPON
//					System.out.println("*************************************");
//					System.out.println("14- Update expired coupon test: ");
//					companyUser1.updateCoupon(new Coupon(1, "Microsoft office", 
//							new Date(119, 3, 12, 12, 30), 
//							new Date(119, 5, 12, 12, 30), 310,  // DATE & AMOUNT CHANGED
//							CouponType.TECHNOLOGY, "exclusive", 100.00, "img.jpg"));
//					
//				
//					//15\\ USER1 UPDATE USER2 COUPON
//					System.out.println("*************************************");
//					System.out.println("15- User1 Update User2 coupon: ");
//					companyUser1.updateCoupon(new Coupon(6, "OutDoor fun", 
//							new Date(120, 3, 3, 13, 30), 
//							new Date(121, 3, 3, 13, 30), 32, 
//							CouponType.CAMPING, "THIS IS USER1", 30.00, "img.jpg"));
//					
//			
//					//16\\ USER1 ATTEMPT TO GET USER2 COUPON#6
//					System.out.println("*************************************");
//					System.out.println("(16) Try to get coupon of another company: ");
//					companyUser1.getCoupon(6);
//					
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// ****************************\\||CUSTOMER TEST||//**************************//
//			
//			    //LOGIN TEST
//				
//				    System.out.println("*************************************");
//				    System.out.println();System.out.println();
//				    System.out.println("//||Customer Test||\\");
//						
//				try {
//					customerUser1= (CustomerFacade)couponSystem.login("Kim", "20014", ClientType.CUSTOMER);
//					
//					//3\\ PRINT A CUSTOMER
//					System.out.println("*************************************");
//					System.out.println("(3) Print Customer test:");
//					customerDAO.getCustomer(2004);
//						
//					
//					//4\\ PURCHASE A COUPON
//					System.out.println("*************************************");
//					System.out.println("4- Purchase Coupon test:");
//					customerUser1.purchaseCoupon(couponDAO.getCoupon(1));
//					customerUser1.purchaseCoupon(couponDAO.getCoupon(2));
//					customerUser1.purchaseCoupon(couponDAO.getCoupon(3));
//					
//					
//					//5\\ PURCHASING A PURCHASED COUPON
//					System.out.println("*************************************");
//					System.out.println("5- Try to purchase coupon which already purchased:");
//					
//					
//					//6\\ PURCHASE A COUPON OUT OF STOCK
//					System.out.println("*************************************");
//					System.out.println("first need to login with another customer:");
//					customerUser2 = (CustomerFacade)couponSystem.login("Kobi", "java123", ClientType.CUSTOMER);
//					System.out.println("6- try to purchase coupon which out of stock: ");
//					customerUser2.purchaseCoupon(couponDAO.getCoupon(1));
//					
//					//7\\ PURCHASE AN EXPIRED COUPON
//					System.out.println("*************************************");
//					System.out.println("7- try to purchase coupon which already expired: ");
//					customerUser2.purchaseCoupon(couponDAO.getCoupon(6));
//					
//					//8\\ GET ALL USER1 COUPONS
//					System.out.println("*************************************");
//					System.out.println("8- Print all coupons that belong to customer test: ");
//					customerUser1.getAllPurchasedCoupons();
//					
//					//9\\ GET ALL USER1 COUPON BY TYPE
//					System.out.println("*************************************");
//					System.out.println("9- Print all coupons that belong to customer by type RESTAURANTS: ");
//					customerUser1.getAllPurchasedCouponsByType(CouponType.RESTURANTS);
//					
//					//10\\ PRINT ALL USER1 COUPON BY PRICE
//					System.out.println("*************************************");	
//					System.out.println("(10) Print all coupons that belong to customer by price limit 100.00: ");
//					customerUser1.getAllPurchasedCouponsByPrice(100.00);
//					
//					
//					
//					//** Daily Coupon Expiration Task Test ** // 
//					System.out.println("*************************************");
//					System.out.println();System.out.println();
//					System.out.println("//** Daily Coupon Expiration Task Test ** //");
//					System.out.println("*************************************");
//					
//					// **Shutdown Test**//
//					System.out.println("*************************************");
//					System.out.println();System.out.println();
//					System.out.println("// **Shutdown Test**//");
//					System.out.println("*************************************");
//					couponSystem.shutdown();
//					
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				
//	}
//		  }
//	
//}
