package Main;


import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import DBDAO.CouponDBDAO;
import Exceptions.MainSystemException;
import JavaBeans.Coupon;

public class DailyCouponExpirationTask implements Runnable {

	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	

	public DailyCouponExpirationTask() {
	}

	@Override
	public void run() {
		
		Set<Coupon> set = new LinkedHashSet<>();
		while (true) { 
			try { 
				set = couponDBDAO.getAllCoupons(); 
				Iterator<Coupon> it = set.iterator();
				while (it.hasNext()) {
					Coupon coupon = it.next();   
					if (coupon.getEndDate().before(new Date(System.currentTimeMillis()))) { //if (coupon.getEndDate().before(new Date(System.currentTimeMillis())))
						couponDBDAO.unlinkCompanyCoupon(coupon.getId());
						couponDBDAO.unlinkCustomerCoupon(coupon.getId());
						couponDBDAO.removeCoupon(coupon);
					}
				}
				// removes coupon, company_coupon,customer_coupon from db in 10 minutes
				couponDBDAO.removeExpiredCoupons();
				Thread.sleep(300000);
			} catch (MainSystemException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}