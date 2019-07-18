package Facades;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import DAO.CompanyDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.MainSystemException;
import JavaBeans.ClientType;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;

public class CustomerFacade implements CouponClientFacade {
	CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	CouponDBDAO couponDBDAO = new CouponDBDAO();
	
	private long logedID = 0;
	private Customer customer;
	private ClientType clientType;
	private CustomerDAO customerDAO;
	
	
	// this was added to customerfacade/////////////////////////
	public CustomerFacade(Customer customer) throws Exception {

		this.customer = customer;
		this.clientType = ClientType.CUSTOMER;
		this.customerDAO= new CustomerDBDAO();
	}
	
	public long getLogedID() {
		return logedID;
	}

	public CustomerFacade(long logedID) {
		this.logedID = logedID;
	}

	public void purchaseCoupon(Coupon coupon) throws MainSystemException {
		try {
			if (coupon == null) {
				throw new MainSystemException("purchase failed : coupons ID is incorrect", null);
			}
			Coupon a = couponDBDAO.getCoupon(coupon.getId());
			if (a == null) {
				throw new MainSystemException("Error: coupon " + coupon.getTitle() + " does not exist", null);
			}
			if (coupon.getAmount() == 0) {
				throw new MainSystemException("Error: coupon out of stock", null);
			}  // THis was changed from getenddate tostartdate and this was before.new date(system.currenttimemiliseconds)
			if (coupon.getStartDate().after(coupon.getEndDate())) { //if (coupon.getEndDate().before(new Date(System.currentTimeMillis())))
				throw new MainSystemException("Error: coupon not valid", null);
			}
			customerDBDAO.link_Customer_Coupon(getLogedID(), coupon.getId());
			coupon.setAmount(coupon.getAmount() - 1);
			couponDBDAO.updateCoupon(coupon);
			System.out.println("coupon " + coupon.getTitle() + " purchased successfully");
		} catch (MainSystemException e1) {
			
			System.out.println(e1.getMessage());
			System.out.println(e1.getCause());
			System.out.println(e1.getStackTrace());
			throw new MainSystemException(e1.getMessage(), e1);
		}
	}

	public Set<Coupon> getAllPurchasedCoupons() throws MainSystemException {
		Set<Coupon> set = new LinkedHashSet<>();
		try {
			set = customerDBDAO.getAllCoupons(customerDBDAO.getCustomer(logedID));
			if (set.isEmpty()) {
				throw new MainSystemException("Error. there are no purchased coupuns for client " + logedID, null);
			} else {
				return set;
			}
		} catch (MainSystemException e) {
			throw new MainSystemException(e.getMessage(), e);
		}
	}

	public Set<Coupon> getAllPurchasedCouponsByType(CouponType couponType) throws MainSystemException {
		Set<Coupon> set1 = new LinkedHashSet<>();
		Set<Coupon> set2 = new LinkedHashSet<>();
		try {
			set1 = getAllPurchasedCoupons();
			for (Coupon coupon : set1) {
				if (coupon.getCouponType().equals(couponType)) {
					set2.add(coupon);
				}
			}
			if (set2.isEmpty()) {
				throw new MainSystemException("Error. there are no purchased coupuns from type " + couponType, null);
			}
			return set2;
		} catch (MainSystemException e) {
			throw new MainSystemException("Error. couldn't show all purchased coupons from type " + couponType.name(),
					e);
		}
	}

	public Set<Coupon> getAllPurchasedCouponsByPrice(double price) throws MainSystemException {
		Set<Coupon> set1 = new LinkedHashSet<>();
		Set<Coupon> set2 = new LinkedHashSet<>();
		try {
			set1 = getAllPurchasedCoupons();
			for (Coupon coupon : set1) {
				if (coupon.getPrice() <= price) {
					set2.add(coupon);
				}
			}
			if (set2.isEmpty()) {
				throw new MainSystemException("Error. there are no purchased coupuns up to price: " + price, null);
			}
			return set2;
		} catch (MainSystemException e) {
			throw new MainSystemException("Error. couldn't show all purchased coupons up to price " + price, e);
		}
	}

	public Set<Coupon> getAllCouponsInStore() throws MainSystemException {
		Set<Coupon> set = new LinkedHashSet<>();
		try {
			set = couponDBDAO.getAllCoupons();
			return set;
		} catch (MainSystemException e) {
			throw new MainSystemException(null, e);
		}
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		return null;
	}
}