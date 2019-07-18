package Facades;

import JavaBeans.ClientType;

// interface class that determines login type Admin/Customer/Company
public interface CouponClientFacade  {
	
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception;
	
}
	
	