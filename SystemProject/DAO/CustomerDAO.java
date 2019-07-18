package DAO;

//DAO = Data Access Object
import java.util.Set;

import Exceptions.MainSystemException;
import JavaBeans.Coupon;
import JavaBeans.Customer;

//CustomerDAO relates to all the functions that are in the customer table
public interface CustomerDAO {
	
void createCustomer (Customer customer) throws MainSystemException ; // INSERT into customer table

void removeCustomer (Customer customer) throws MainSystemException ; // REMOVE from customer table

void updateCustomer (Customer customer) throws MainSystemException ; // UPDATE customer table

Customer getCustomer (long id) throws MainSystemException ; // get customer using ID

Set<Customer> getAllCustomers () throws MainSystemException ; // gets all customers using "Set"

Set<Coupon> getAllCoupons (Customer customer) throws MainSystemException ; // gets all coupons using "Set"

boolean login (String Cust_name , String password) throws MainSystemException ; 

void link_Customer_Coupon (long customerID, long couponID)throws MainSystemException;

void unlink_all_Customer_Coupons (Customer customer)throws MainSystemException;

Customer getCustomerByName (String cust_name) throws MainSystemException ; // get Customer by name 

}