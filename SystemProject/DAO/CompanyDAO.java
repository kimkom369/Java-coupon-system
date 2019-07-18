package DAO;

import java.util.List;
//DAO = Data Access Object
import java.util.Set;

import Exceptions.MainSystemException;
import JavaBeans.Company;
import JavaBeans.Coupon;

//CompanyDAO relate to all the functions that are in the companies table
public interface CompanyDAO {

void insertCompany (Company company) throws MainSystemException ; // INSERT into company table

void removeCompany (Company company) throws MainSystemException ; // REMOVE from company table

void updateCompany (Company company) throws MainSystemException ; // UPDATE company table

Company getCompany (long id) throws MainSystemException ; 
//was changed to LIST instead of SEt
Set<Company> getAllCompanies () throws MainSystemException ; // gets all the companies using "Set" 

Set<Coupon> getAllCoupons (Company company) throws MainSystemException ; // gets all the coupons using "Set"

boolean login (String Comp_name , String password) throws MainSystemException ; 

void link_Company_Coupon (long companyID, long couponID) throws MainSystemException;

void unlink_all_Company_Coupons (Company company)throws MainSystemException;

Company getCompanyByName (String comp_name) throws MainSystemException ; 

}