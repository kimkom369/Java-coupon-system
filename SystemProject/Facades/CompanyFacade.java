package Facades;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import Exceptions.MainSystemException;
import JavaBeans.ClientType;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class CompanyFacade implements CouponClientFacade {
	CompanyDBDAO companyDBDAO = new CompanyDBDAO();
	CouponDBDAO couponDBDAO = new CouponDBDAO();
	
	
	private Company company;
	private ClientType clientType;
	private CompanyDAO compCompanyDAO;
//	private final String Name = "";
//	private final String Password = "1234";
	
	// this was added to companyfacade/////////////////////////
	public CompanyFacade(Company company) throws Exception {

		this.company = company;
		this.clientType = ClientType.COMPANY;
		this.compCompanyDAO = new CompanyDBDAO();
		
		
	}

	/////////////////////////////////////////////////////////
	private long logedID = 0;

	public long getLogedID() { 
		return logedID;
	}

	public CompanyFacade(long logedID) {
		this.logedID = logedID;
	}

	public void createCoupon(Coupon coupon) throws MainSystemException {
		try {
			Coupon a = couponDBDAO.getCouponByTitle(coupon.getTitle());
			if (a == null) {
				couponDBDAO.createCoupon(coupon);
				companyDBDAO.link_Company_Coupon(getLogedID(), coupon.getId());
			} else {
				throw new MainSystemException("Error. coupon title " + coupon.getTitle() + " already exist", null);
			}
		} catch (MainSystemException e) {
			throw new MainSystemException(e.getMessage(), e);
		}
	}

	public void removeCoupon(Coupon couponId) throws MainSystemException { // this was Coupon coupon
		try {
			Coupon a = couponDBDAO.getCoupon(couponId.getId());
			if (a == null) {
				throw new MainSystemException("Error. coupon " + couponId.getTitle() + " not exist - can't remove", null);
			}
			couponDBDAO.removeCoupon(couponId);
			couponDBDAO.unlinkCompanyCoupon(couponId.getId());
			couponDBDAO.unlinkCustomerCoupon(couponId.getId());
		} catch (MainSystemException e) {
			throw new MainSystemException(e.getMessage(), e);
		}
	}

	public void updateCoupon(Coupon coupon) throws MainSystemException { //MainSystemException
		try {
			Coupon a = couponDBDAO.getCoupon(coupon.getId());
			if (a == null) {
				throw new MainSystemException("Error. coupon " + coupon.getTitle() + " not exist", null);
			}
			if (coupon.getId() == a.getId() && coupon.getTitle().equalsIgnoreCase(a.getTitle())) {
				couponDBDAO.updateCoupon(coupon);
			} else {
				throw new MainSystemException("Error. you cant only update: price, end date", null);
			}
		} catch (MainSystemException e) {
			throw new MainSystemException(e.getMessage(), e);
		}
	}

	public Coupon getCoupon(long id) throws MainSystemException {
		try {
			Coupon a = couponDBDAO.getCoupon(id);
			if (a == null) {
				throw new MainSystemException("Error. coupon " + id + " not exist", null);
			} else {
				return a;
			}
		} catch (MainSystemException e) {
			throw new MainSystemException(e.getMessage(), e);
		}
	}

	public Set<Coupon> getAllCoupons() throws MainSystemException {
		Set<Coupon> set = new LinkedHashSet<>();
		try {
			set = companyDBDAO.getAllCoupons(companyDBDAO.getCompany(logedID));
			return set;
		} catch (MainSystemException e) {
			throw new MainSystemException("Error. couldn't show coupons", e);
		}
	}

	public Set<Coupon> getCouponsByType(CouponType couponType) throws MainSystemException {
		Set<Coupon> set1 = new LinkedHashSet<>();
		Set<Coupon> set2 = new LinkedHashSet<>();
		try {
			set1 = getAllCoupons();
			for (Coupon coupon : set1) {
				if (coupon.getCouponType().equals(couponType)) {
					set2.add(coupon);
				}
			}
			if (set2.isEmpty()) {
				throw new MainSystemException("There are no coupons from type " + couponType, null);
			}
			return set2;
		} catch (MainSystemException e) {
			throw new MainSystemException("Error. couldn't show Coupons By Type " + couponType.name(), e);
		}
	}

	public Set<Coupon> getCouponsByPrice(double price) throws MainSystemException {
		Set<Coupon> set1 = new LinkedHashSet<>();
		Set<Coupon> set2 = new LinkedHashSet<>();
		try {
			set1 = getAllCoupons();
			for (Coupon coupon : set1) {
				if (coupon.getPrice() <= price) {
					set2.add(coupon);
				}
			}
			if (set2.isEmpty()) {
				throw new MainSystemException("There are no coupons up to price " + price, null);
			}
			return set2;
		} catch (MainSystemException e) {
			throw new MainSystemException("Error. couldn't show Coupons to price " + price, e);
		}
	}
   // get coupon by DATE method ...instead of getCouponsUntilDate
	public Set<Coupon> getCouponsByDate(Date endDate) throws MainSystemException {
		Set<Coupon> set1 = new LinkedHashSet<>();
		Set<Coupon> set2 = new LinkedHashSet<>();
		try {
			set1 = getAllCoupons();
			for (Coupon coupon : set1) {
				if (coupon.getEndDate().before(endDate)) {
					set2.add(coupon);
				}
			}
			if (set2.isEmpty()) {           // this was changed
				throw new MainSystemException("Failed to get Coupons By Date " + endDate.toString(), null);
			}
			return set2;
		} catch (MainSystemException e) { // this was changed
			throw new MainSystemException("Error: coupons by Date not found " + endDate.toString(), e);
		}
	} 
	// this was added to company facade
	public Company getCompany() throws Exception {

		try {
			System.out.println(compCompanyDAO.getCompany(logedID));
			return compCompanyDAO.getCompany(logedID);
		} catch (Exception e) {
			throw new Exception("Company failed to get company details. companyId: " + this.company.getCompName());
		}
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void updateCoupon(int i, String string, Date date) {
//		try {
//			Coupon a = couponDBDAO.getCoupon(coupon.getId());
//			if (a == null) {
//				throw new MainSystemException("Error. coupon " + coupon.getTitle() + " not exist", null);
//			}
//			if (coupon.getId() == a.getId() && coupon.getTitle().equalsIgnoreCase(a.getTitle())) {
//				couponDBDAO.updateCoupon(coupon);
//			} else {
//				throw new MainSystemException("Error. you cant only update: price, end date", null);
//			}
//		} catch (MainSystemException e) {
//			throw new MainSystemException(e.getMessage(), e);
//		}
//		
//	}
}