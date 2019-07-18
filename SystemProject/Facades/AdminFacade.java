package Facades;

import java.util.LinkedHashSet;
import java.util.Set;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.MainSystemException;
import JavaBeans.ClientType;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
// AdminFacade class manages all the companies & customers
public class AdminFacade implements CouponClientFacade {
    
	// Login attributs 
	private final String Name = "admin";
	private final String Password = "1234";
	
//	public AdminFacade login(String name, String password, ClientType type) throws Exception {
//		
//		if (!type.equals(ClientType.ADMIN)) {
//			return null;
//		}
//
//		// Checking name & password
//		String que = name + password;
//		String ans = Name + Password; // this was NAME / PASSWORD
//		if (!que.equals(ans)) {
//			return null;
//		}
//		return this;
//	}

	
	
	//DBDAOS
	private CompanyDBDAO companyDBDAO = new CompanyDBDAO();
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	private CustomerDBDAO customerDBDAO = new CustomerDBDAO();
 
	public AdminFacade() {
	}

	public void createCompany(Company company) throws MainSystemException {
		try {
			Company a = companyDBDAO.getCompanyByName(company.getCompName());
			if (a == null) { 
				companyDBDAO.insertCompany(company);
			} else {
				throw new MainSystemException("Error: company name " + company.getCompName() + " already exist");
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
			
		}
	}

	public void removeCompany(Company company) throws MainSystemException {
		Set<Coupon> set = new LinkedHashSet<>();
		try {
			Company a = companyDBDAO.getCompany(company.getId());
			if (a == null) {
				throw new MainSystemException("Error: company " + company.getCompName() + " not exist - cannot remove");
			}
			set = companyDBDAO.getAllCoupons(company);
			if (!set.isEmpty()) {
				for (Coupon coupon : set) {
					couponDBDAO.unlinkCompanyCoupon(coupon.getId());
					couponDBDAO.unlinkCustomerCoupon(coupon.getId());
					couponDBDAO.removeCoupon(coupon);
				}
			}
			companyDBDAO.removeCompany(company);
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public void updateCompany(Company company) throws MainSystemException {
		try {
			Company a = companyDBDAO.getCompany(company.getId());
			if (a == null) {
				throw new MainSystemException(
						"Error: can't update because company " + company.getCompName() + " does not exist");
			}
			if (a.getCompName().equalsIgnoreCase(company.getCompName())) {
				companyDBDAO.updateCompany(company);
			} else {
				throw new MainSystemException("Error: can't update company name");
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public Company getCompany(long id) throws MainSystemException {
		try {
			Company a = companyDBDAO.getCompany(id);
			if (a == null) {
				throw new MainSystemException("Error: company " + id + " does not exist");
			} else {
				return a;
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public Set<Company> getAllCompanies() throws MainSystemException {
		try {
			Set<Company> set = new LinkedHashSet<>();
			set = companyDBDAO.getAllCompanies();
			return set;
		} catch (MainSystemException e) {
			throw new MainSystemException("Error: couldn't show all companies", e);
		}
	}

	public void createCustomer(Customer customer) throws MainSystemException {
		try {
			Customer a = customerDBDAO.getCustomerByName(customer.getCustName());
			if (a == null) {
				customerDBDAO.createCustomer(customer);
			} else {
				throw new MainSystemException("Error: customer name " + customer.getCustName() + " already exist");
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public void removeCustomer(Customer customer) throws MainSystemException {
		try {
			Customer a = customerDBDAO.getCustomerByName(customer.getCustName());
			if (a == null) {
				throw new MainSystemException("Error: customer " + customer.getCustName() + " not exist - can't remove");
			}
			Set<Coupon> set = new LinkedHashSet<>();
			set = customerDBDAO.getAllCoupons(customer);
			if (!set.isEmpty()) {
				for (Coupon coupon : set) {
					couponDBDAO.unlinkCustomerCoupon(coupon.getId());
				}
			}
			customerDBDAO.removeCustomer(customer);
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public void updateCustomer(Customer customer) throws MainSystemException {
		try {
			Customer a = customerDBDAO.getCustomer(customer.getId());
			if (a == null) {
				throw new MainSystemException(
						"Error: can't update because customer " + customer.getCustName() + " not exist");
			}
			if (a.getCustName().equalsIgnoreCase(customer.getCustName())) {
				customerDBDAO.updateCustomer(customer);
			} else {
				throw new MainSystemException("Error: can't update customer name");
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public Customer getCustomer(long id) throws MainSystemException {
		try {
			Customer a = customerDBDAO.getCustomer(id);
			if (a == null) {
				throw new MainSystemException("Error: customer " + id + " not exist");
			} else {
				return a;
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}

	public Set<Customer> getAllCustomers() throws MainSystemException {
		Set<Customer> set = new LinkedHashSet<>();
		try {
			set = customerDBDAO.getAllCustomers();
			if (set.isEmpty()) {
				throw new MainSystemException("There are no customers DataBase");
			}else {
				return set;
			}
		} catch (MainSystemException e) {
			throw new MainSystemException (e.getMessage());
		}
	}
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		return null;
	}

}