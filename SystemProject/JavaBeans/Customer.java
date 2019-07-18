package JavaBeans;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
   
	//DATA MEMEBERS//
	private long id; // Customers ID
	private String custName; // Customers name
	private String password; // Customers password
	private Set <Coupon> coupons = new LinkedHashSet<>(); //gets customer coupons
	
	public Customer() {
		super();
	}
    //getter & setters//
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString() {/////////////// returns all the objects as string format
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password ;
	}
	public Customer(long id, String custName, String password) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	
}