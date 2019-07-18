package Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.derby.impl.sql.catalog.SYSPERMSRowFactory;
import org.apache.derby.impl.sql.compile.GetCurrentConnectionNode;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import DataBase.ConnectionPool;
import DataBase.CreateDB;
import Facades.AdminFacade;
import Facades.CompanyFacade;
import Facades.CustomerFacade;
import JavaBeans.ClientType;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;
import Main.CouponSystemMain;

public class Tester {
	
	public static void main (String []args){

		
	
		new Company();
		new Customer();
		CompanyDAO companyDAO = new CompanyDBDAO();
		CustomerDAO customerDAO = new CustomerDBDAO();
		CouponDAO couponDAO = new CouponDBDAO();
	
		AdminFacade admin;
		CompanyFacade companyUser1; 
		CompanyFacade companyUser2;
		CustomerFacade customerUser1;
		CustomerFacade customerUser2;
		
//**********************************************|| COUPON SYSTEM TEST ||************************************************************\\
		
		System.out.println("*********************||START COUPON SYSTEM||************************************************************");
		
		// CONNECTING TO TO THE COUPON SYSTEM
		CouponSystemMain couponSystem = null;
		try {
			CreateDB.main(args);
			couponSystem = CouponSystemMain.getInstance();
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}

		try {
			ConnectionPool.getInstance().getConnection(); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//*********************************************************|| ADMIN TEST ||*************************************************************************\\
		
		 // ADMIN LOGIN //
System.out.println("*****************************************|| ADMIN TEST ||*****************************************************************\n");
		try {
			admin = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
			
			
			//list of companies 
			Company company1 = new Company(1234, "Microsoft", "Micr0s0ft", "microsoft@gmail.com");
			Company company2 = new Company(2345, "Rayban", "IcU66", "rayban@gmail.com");
			Company company3 = new Company(3456, "Trivago", "Trvl360", "trivago@gmail.com");
			Company company4 = new Company(4567, "Mcdonalds", "mc333", "macyD@gmail.com");
			Company company5 = new Company(5678, "Apple", "Mac13pro", "apple@gmail.com");
			//admin creates companies
			admin.createCompany(company1);
			admin.createCompany(company2);
			admin.createCompany(company3);
			admin.createCompany(company4);
			admin.createCompany(company5);
			
            //list of customers 
			Customer customer1 = new Customer(2001, "Kim", "Kim1990");
			Customer customer2 = new Customer(2002, "Ran", "ran3131");
			Customer customer3 = new Customer(2003, "Kobi", "java123");
			Customer customer4 = new Customer(2005, "Menny", "2580");
			//admin creates customers
			admin.createCustomer(customer1);
			admin.createCustomer(customer2);// will be removed
			admin.createCustomer(customer3);// will be updated
			admin.createCustomer(customer4);
			
System.out.println("*****************************************************************************************************************************");
			
			// ADMIN COMPANY TEST //
			
			/* 1.GET ALL COMPANIES by admin
			 * 2.UPDATE A COMPANY by admin
			 * 3. REMOVE A COMPANY by admin
			  */
			
			// 1.GET ALL COMPANIES //
			System.out.println();
			System.out.println("Get all Companies test");
			admin.getAllCompanies();
			
			// 2. UPDATE A COMPANY //
			System.out.println();
			System.out.println("Update a Company test");
			admin.updateCompany(company3 = new Company(3456, "Trivago", "N33D2G0", "travel@gmail.com"));
			admin.getCompany(3456);
			
			// 3. REMOVE A COMPANY //
			//System.out.println();
			//System.out.println("Remove a Company test");
			//admin.removeCompany(company5);
			//admin.getAllCompanies();
			
			
			
System.out.println("***************************************************************************************************************************");
	
			// ADMIN CUSTOMER TEST //
           
            /* 1.GET ALL CUSTOMERS by admin
             * 2.UPDATE A CUSTOMER by admin
             * 3.REMOVE A CUSTOMER by admin 
             */

           // 1.GET ALL CUSTOMERS
            System.out.println();
            System.out.println("Get all Customers test");
            admin.getAllCustomers();
            
           // 2.UPDATE A CUSTOMER
            System.out.println();
            System.out.println("Update a Customer test");
            admin.updateCustomer(customer4 = new Customer(2006, "Mor", "Mr3366"));
            admin.getCustomer(2006);
            
           // 3.REMOVE A CUSTOMER
           // System.out.println();
           // System.out.println("Remove a customer test");
           // admin.removeCustomer(customer2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
            
System.out.println("=====================================|| END ADMIN TEST ||====================================================================");
			
//******************************************************|| COMPANY TEST #1 ||***********************************************************************\\

            
      // COMPANY #1 LOGIN //
System.out.println("**************************************|| COMPANY TEST #1 TEST ||*************************************************************");
        	   try {                                                            
					companyUser1 = (CompanyFacade)couponSystem.login("Microsoft", "Micr0s0ft", ClientType.COMPANY);
					
                                          //** Company #1 Creating Coupons **\\
			//****************************************************************************************************\\
					// coupon #1
					Coupon coupon1 = new Coupon(111, "Microsoft office", new Date(), 
							new GregorianCalendar(2019, Calendar.JUNE, 21, 15, 30).getTime(),
							13, CouponType.TECHNOLOGY, "Deal #1", 150.00, "img.jpg");
					
                   // coupon #2 
					
					Coupon coupon2 = new Coupon(222, "Microsoft Windows",new Date(),
							 new GregorianCalendar(2019, Calendar.JULY, 4, 20, 00).getTime(),
							 300, CouponType.SOFTWARE, "Deal #2", 170.00, "img.jpg");
					
				  // coupon #3
					
					Coupon coupon3 = new Coupon(333, "Microsoft Surface pro", new Date(),
							new GregorianCalendar(2020, Calendar.AUGUST, 5, 12, 45).getTime(),
							15, CouponType.COMPUTERS, "Deal #3", 500.00, "img.jpg");
					
				// coupon #4 
					
					Coupon coupon4 = new Coupon(444, "Anti Virus", new Date(),
							new GregorianCalendar(2021, Calendar.FEBRUARY, 4, 13, 30).getTime(),
							50, CouponType.SOFTWARE, "Deal #4", 30.00, "img.jpg");
					
					companyUser1.createCoupon(coupon1);
					companyUser1.createCoupon(coupon2);
					companyUser1.createCoupon(coupon3);
					companyUser1.createCoupon(coupon4);
					
System.out.println("***********************************************************************************************************");
          
               /*COMPANY #1 TESTS
                * 1.GET ALL COUPONS by company
                * 2.UPDATE A COUPON by company
                * 3.GET COUPONS by type
                * 4.GET COUPONS by price
                * 5.GET COUPONS by date
                * 6.REMOVE COUPONS by company
                */

                // 1.GET ALL COUPONS by company
                   System.out.println();
                   System.out.println("Get all Coupons by Company test");
                   companyUser1.getAllCoupons();
                   
                // 2.UPDATE A COUPON by company
                   System.out.println();
                   System.out.println("Update a coupon by company test");
                   companyUser1.updateCoupon(coupon3 = new Coupon(333, "Microsoft Surface pro", new Date(),
							new GregorianCalendar(2021, Calendar.MAY, 5, 12, 45).getTime(),
							15, CouponType.HARDWARE, "Deal #3", 500.00, "img.jpg"));
                   
                 // 3.GET COUPONS by type
                   System.out.println();
                   System.out.println("Get coupons by type test");
                   companyUser1.getCouponsByType(CouponType.SOFTWARE);
                   
                // 4.GET COUPONS by price
                   System.out.println();
                   System.out.println("Get coupons by price test");
                   companyUser1.getCouponsByPrice(250.00);
                   
                // 5.GET COUPONS by date
                   System.out.println();
                   System.out.println("Get coupoins by date test");
                   companyUser1.getCouponsByDate(new GregorianCalendar(2020, Calendar.JULY, 7, 20, 30).getTime());
                   
               // 6.Remove COUPON by company
                   //System.out.println();
                   //System.out.println("Remove coupon by company test");
                 //  companyUser1.removeCoupon(coupon4);
                
        	   } catch (Exception e) {
       			System.out.println(e.getMessage());
       		}
System.out.println("==================================|| END COMPANY #1 TEST=====================================================");

 //*********************************************************|| COMPANY #2 TEST ||********************************************************\\

// COMPANY #2 LOGIN //
System.out.println("**************************************|| COMPANY TEST #2 TEST ||*************************************************************");
    	   try {                                                            
				companyUser2 = (CompanyFacade)couponSystem.login("Apple", "Mac13pro", ClientType.COMPANY);
				
                                      //** Company #2 Creating Coupons **\\
		     //****************************************************************************************************\\
				// coupon #1
				Coupon coupon1 = new Coupon(555, "Apple Imac", new Date(), 
						new GregorianCalendar(2019, Calendar.JUNE, 21, 15, 30).getTime(),
						13, CouponType.TECHNOLOGY, "Deal #1", 150.00, "img.jpg");
				
               // coupon #2 
				
				Coupon coupon2 = new Coupon(666, "Apple Macbook pro13",new Date(),
						 new GregorianCalendar(2019, Calendar.JULY, 4, 20, 00).getTime(),
						 300, CouponType.SOFTWARE, "Deal #2", 170.00, "img.jpg");
				
			  // coupon #3
				
				Coupon coupon3 = new Coupon(777, "IMac Pro", new Date(),
						new GregorianCalendar(2020, Calendar.AUGUST, 5, 12, 45).getTime(),
						15, CouponType.COMPUTERS, "Deal #3", 500.00, "img.jpg");
				
			// coupon #4 
				
				Coupon coupon4 = new Coupon(888, "Mackeeper", new Date(),
						new GregorianCalendar(2021, Calendar.FEBRUARY, 4, 13, 30).getTime(),
						50, CouponType.SOFTWARE, "Deal #4", 30.00, "img.jpg");
				
				companyUser2.createCoupon(coupon1);
				companyUser2.createCoupon(coupon2);
				companyUser2.createCoupon(coupon3);
				companyUser2.createCoupon(coupon4);
				
System.out.println("***********************************************************************************************************");
      
           /*COMPANY #2 TESTS
            * 1.GET ALL COUPONS by company
            * 2.UPDATE A COUPON by company
            * 3.GET COUPONS by type
            * 4.GET COUPONS by price
            * 5.GET COUPONS by date
            * 6.REMOVE COUPONS by company
            */

            // 1.GET ALL COUPONS by company
               System.out.println();
               System.out.println("Get all Coupons by Company test");
               companyUser2.getAllCoupons();
               
            // 2.UPDATE A COUPON by company
               System.out.println();
               System.out.println("Update a coupon by company test");
               companyUser2.updateCoupon(coupon3 = new Coupon(777, "IMac Pro 2019", new Date(),
						new GregorianCalendar(2022, Calendar.AUGUST, 5, 12, 45).getTime(),
						150, CouponType.COMPUTERS, "Deal #3", 700.00, "img.jpg"));
               
             // 3.GET COUPONS by type
               System.out.println();
               System.out.println("Get coupons by type test");
               companyUser2.getCouponsByType(CouponType.SOFTWARE);
               
            // 4.GET COUPONS by price
               System.out.println();
               System.out.println("Get coupons by price test");
               companyUser2.getCouponsByPrice(250.00);
               
            // 5.GET COUPONS by date
               System.out.println();
               System.out.println("Get coupoins by date test");
               companyUser2.getCouponsByDate(new GregorianCalendar(2020, Calendar.JULY, 7, 20, 30).getTime());
               
           // 6.Remove COUPON by company
               //System.out.println();
               //System.out.println("Remove coupon by company test");
             //  companyUser1.removeCoupon(coupon4);
               
    	   } catch (Exception e) {
      			System.out.println(e.getMessage());
      		}

System.out.println("=============================================|| END COMPANY #2 TEST===========================================");
           
//*********************************************************|| CUSTOMER #1 TEST ||********************************************************\\

//COMPANY #2 LOGIN //
System.out.println("**************************************|| CUSTOMER #1 TEST ||*************************************************************");
 	   try {                                                            
				customerUser1 = (CustomerFacade)couponSystem.login("Kim", "Kim1990", ClientType.CUSTOMER);
				
				
				customerUser1.purchaseCoupon(couponDAO.getCoupon(333));
				customerUser1.purchaseCoupon(couponDAO.getCoupon(444));
				customerUser1.purchaseCoupon(couponDAO.getCoupon(555));
				customerUser1.purchaseCoupon(couponDAO.getCoupon(111));
				
				/* CUSTOMER #1 TEST 
				 * 1.GET ALL USERS COUPONS
				 * 2.GET ALL USERS COUPON by type
				 * 3.GET ALL USERS COUPON by price
				 */
			   
				
				// 1. GET ALL USER1 COUPONS
				System.out.println();
				System.out.println(" Get all User1 coupons test ");
				customerUser1.getAllPurchasedCoupons();
				
				// 2.GET ALL USER1 COUPON BY TYPE
				System.out.println();
				System.out.println("Get all User1 coupons by type test ");
				customerUser1.getAllPurchasedCouponsByType(CouponType.SOFTWARE);
				
				// 3.PRINT ALL USER1 COUPON BY PRICE
				System.out.println();	
				System.out.println("Get all User1 coupons by price test ");
				customerUser1.getAllPurchasedCouponsByPrice(100.00);
				
 	  } catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
			
System.out.println("=================================================|| END CUSTOMER #1 TEST ||=================================================");

//*********************************************************|| CUSTOMER #2 TEST ||********************************************************\\

//COMPANY #2 LOGIN //
System.out.println("**************************************|| CUSTOMER #2 TEST ||*************************************************************");
	   try {                                                            
				customerUser2 = (CustomerFacade)couponSystem.login("Kobi", "java123", ClientType.CUSTOMER);
				
				
				customerUser2.purchaseCoupon(couponDAO.getCoupon(666));
				customerUser2.purchaseCoupon(couponDAO.getCoupon(888));
				customerUser2.purchaseCoupon(couponDAO.getCoupon(222));
				customerUser2.purchaseCoupon(couponDAO.getCoupon(777));
				
				/* CUSTOMER #1 TEST 
				 * 1.GET PURCHASED COUPONS
				 * 2
				 * 1.GET ALL USERS COUPONS
				 * 2.GET ALL USERS COUPON by type
				 * 3.GET ALL USERS COUPON by price
				 */
			   
				
				// 1. PURCHASE A COUPON
				System.out.println("*************************************");
				System.out.println("4- Purchase Coupon test:");
				customerUser2.purchaseCoupon(couponDAO.getCoupon(1));
				customerUser2.purchaseCoupon(couponDAO.getCoupon(2));
				customerUser2.purchaseCoupon(couponDAO.getCoupon(3));
				
				
				// 2. PURCHASING A PURCHASED COUPON
				System.out.println();
				System.out.println("Purchasing a purchased coupon");
				customerUser2.purchaseCoupon(couponDAO.getCoupon(333));
				
				
				// 4. GET ALL USER2 COUPONS
				System.out.println();
				System.out.println("Get all User2 coupons");
				customerUser2.getAllPurchasedCoupons();
				
				// 5. GET ALL USER2 COUPON BY TYPE
				System.out.println();
				System.out.println("Get all User2 coupon by type ");
				customerUser2.getAllPurchasedCouponsByType(CouponType.RESTURANTS);
				
				// 6. PRINT ALL USER2 COUPON BY PRICE
				System.out.println();	
				System.out.println("Get all User2 coupon by price ");
				customerUser2.getAllPurchasedCouponsByPrice(100.00);
				
				
				
				
				
				
System.out.println("==============================================|| END CUSTOMER #2 TEST ||=========================================");

				//** Daily Coupon Expiration Task Test ** // 
				System.out.println("*************************************");
				System.out.println();System.out.println();
				System.out.println("//** Daily Coupon Expiration Task Test ** //");
				System.out.println("*************************************");
				
				// **Shutdown Test**//
				System.out.println("*************************************");
				System.out.println();System.out.println();
				System.out.println("// **Shutdown Test**//");
				System.out.println("*************************************");
				couponSystem.shutdown();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			
}
	  }

}