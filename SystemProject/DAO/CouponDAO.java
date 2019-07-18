package DAO;

//DAO = Data Access Object
import java.util.Set;

import Exceptions.MainSystemException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

//CouponDAO relate to all the functions that are in the coupons table
//Data Access Object is an object that provides an abstract interface
//* to the database. The DAO provides some specific data operations without exposing details
//* of the database. Implementation is done by other classes - DBDAO which interacts with the Driver
//* and with the Database through SQL queries.
public interface CouponDAO {

void createCoupon (Coupon coupon) throws MainSystemException ; // INSERT into coupon table

void removeCoupon (Coupon coupon) throws MainSystemException ; // REMOVE from coupon table

void updateCoupon (Coupon coupon) throws MainSystemException ; // UPDATE coupon table

Coupon getCoupon (long id) throws MainSystemException ; // gets coupons via ID

Set<Coupon> getAllCoupons () throws MainSystemException ; // gets all coupons using "Set"

Set<Coupon> getCouponByType (CouponType couponType) throws MainSystemException ; //gets Coupon by type using "Set"

void unlinkCustomerCoupon (long id) throws  MainSystemException; 

void unlinkCompanyCoupon (long id) throws  MainSystemException;

Coupon getCouponByTitle (String title) throws MainSystemException ; // get coupons by TITLE

void removeExpiredCoupons() throws MainSystemException; //removes all the expired coupons 
}
