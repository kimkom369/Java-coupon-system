package JavaBeans;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


  public class Company implements Serializable{
  private static final long serialVersionUID = 1L;

//DATA MEMEBERS//
  private long id; // Companies ID
  private String compName; // Companies name
  private String password; // Companies password
  private String email; // Companies email
  private Set <Coupon> coupons = new LinkedHashSet<>();


  public Company() {
	//super();
}

///////////getters & setters////////
   public long getId() {
	return id;
}
   public void setId(long id) {
	this.id = id;
}
   public String getCompName() {
	return compName;
}
   public void setCompName(String compName) {
	this.compName = compName;
}
   public String getPassword() {
	return password;
}
   public void setPassword(String password) {
	this.password = password;
}
   public String getEmail() {
	return email;
}
   public void setEmail(String email) {
	this.email = email;
}
   public Set<Coupon> getCoupons() {
	return coupons;
}
   public void setCoupons(Set<Coupon> coupons) {
	this.coupons = coupons;
}
   @Override
   public String toString() {/////////////// returns all the objects as string format
	return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email ;
}
   public Company(long id, String compName, String password, String email) {
	super();
	this.id = id;
	this.compName = compName;
	this.password = password;
	this.email = email;
}

}