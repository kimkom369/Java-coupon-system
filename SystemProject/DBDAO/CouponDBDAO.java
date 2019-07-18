package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import DAO.CouponDAO;
import DataBase.ConnectionPool;
import Exceptions.MainSystemException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class CouponDBDAO implements CouponDAO {

	@Override
	public void createCoupon(Coupon coupon) throws MainSystemException {
		String sql = "insert into coupon values (?,?,?,?,?,?,?,?,?)"; // insert the number of values into the table
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setLong(1, coupon.getId());
				stm.setString(2, coupon.getTitle());
				stm.setDate(3, new Date(coupon.getStartDate().getTime()));
				stm.setDate(4, new Date(coupon.getEndDate().getTime()));
				stm.setInt(5, coupon.getAmount());
				stm.setString(6, coupon.getCouponType().toString());
				stm.setString(7, coupon.getMessage());
				stm.setDouble(8, coupon.getPrice());
				stm.setString(9, coupon.getImage());
				stm.executeUpdate();
			}
			System.out.println("coupon " + coupon.getId() + " created in DB");
		} catch (SQLException e) {
			String x = "Error while trying to creat coupon " + coupon.getId() + " in DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override
	public void removeCoupon(Coupon coupon) throws MainSystemException {
		String sql = "delete from coupon where ID = ?";
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setLong(1, coupon.getId());
				stm.executeUpdate();
			}
			System.out.println("coupon " + coupon.getId() + " removed from DB");
		} catch (SQLException e) {
			String x = "Error while trying to removed coupon " + coupon.getId() + " from DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws MainSystemException {
		String sql = "update coupon set TITLE = ?, START_DATE = ?, END_DATE = ?, AMOUNT = ?, ";
		sql += "TYPE = ?, MESSAGE = ?, PRICE = ?, IMAGE = ? where ID = ?";
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setString(1, coupon.getTitle());
				stm.setDate(2, new Date(coupon.getStartDate().getTime()));
				stm.setDate(3, new Date(coupon.getEndDate().getTime()));
				stm.setInt(4, coupon.getAmount());
				stm.setString(5, coupon.getCouponType().toString());
				stm.setString(6, coupon.getMessage());
				stm.setDouble(7, coupon.getPrice());
				stm.setString(8, coupon.getImage());
				stm.setLong(9, coupon.getId());
				stm.executeUpdate();
			}
			System.out.println("coupon " + coupon.getId() + " updated in DB");
		} catch (SQLException e) {
			String x = "Error while trying to updated coupon " + coupon.getId() + " in DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override
	public Coupon getCoupon(long id) throws MainSystemException {
		String sql = "select * from coupon where ID = ?";
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setLong(1, id);
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					Coupon a = new Coupon();
					a.setId(rs.getLong("ID"));
					a.setTitle(rs.getString("TITLE"));
					a.setStartDate(rs.getDate("START_DATE"));
					a.setEndDate(rs.getDate("END_DATE"));
					a.setAmount(rs.getInt("AMOUNT"));
					a.setCouponType(CouponType.valueOf(rs.getString("TYPE")));
					a.setMessage(rs.getString("MESSAGE"));
					a.setPrice(rs.getDouble("PRICE"));
					a.setImage(rs.getString("IMAGE"));
					return a;
				}
				return null;
			}
		} catch (SQLException e) {
			String x = "Error while trying to read coupon " + id + " from DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override
	public Set<Coupon> getAllCoupons() throws MainSystemException {
		String sql = "select * from coupon";
		Connection con = null;
		Set<Coupon> set = new LinkedHashSet<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (Statement stm = con.createStatement();) {
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					Coupon a = new Coupon();
					a.setId(rs.getLong("ID"));
					a.setTitle(rs.getString("TITLE"));
					a.setStartDate(rs.getDate("START_DATE"));
					a.setEndDate(rs.getDate("END_DATE"));
					a.setAmount(rs.getInt("AMOUNT"));
					a.setCouponType(CouponType.valueOf(rs.getString("TYPE")));
					a.setMessage(rs.getString("MESSAGE"));
					a.setPrice(rs.getDouble("PRICE"));
					a.setImage(rs.getString("IMAGE"));
					set.add(a);
				}
				return set;
			}
		} catch (SQLException e) {
			String x = "Error while trying to read all coupons from DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override
	public Set<Coupon> getCouponByType(CouponType couponType) throws MainSystemException {
		Set<Coupon> set = new LinkedHashSet<>();
		String sql = "select * from coupon where type = ?";
		Connection con = null;
		int y = 0;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setString(1, couponType.toString());
				ResultSet rs = stm.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						Coupon a = getCoupon(rs.getLong("ID"));
						set.add(a);
						if (a.getId() != 0) {
							y++;
						}
					}
					if (y == 0) {
						throw new MainSystemException("no coupons found from type - " + couponType);
					} else {
						return set;
					}
				} else {
					throw new MainSystemException(
							"Error while trying to read coupons By Type " + couponType + " from DB");
				}
			}
		} catch (SQLException e) {
			String x = "Error while trying to read coupons By Type " + couponType + " from DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}
    // this method unlinks the customer_coupon using the foreign key by 
	@Override
	public void unlinkCustomerCoupon(long id) throws MainSystemException { 
		String sql = "delete from customer_coupon where coupon_id = ?";
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setLong(1, id);
				stm.executeUpdate();
			}
			System.out.println("coupon " + id + " unlinked from customers");
		} catch (SQLException e) {
			String x = "Error while trying to unlink and remove coupon " + id;
			throw new MainSystemException(x);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override //public void unlinkCompanyCoupon(long id) throws MainSystemException {
	public void unlinkCompanyCoupon(long id) throws MainSystemException {
		String sql = "delete from company_coupon where coupon_id = ?";
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setLong(1, id);
				stm.executeUpdate();
			}
			System.out.println("coupon " + id + " unlinked from linked Company");
		} catch (SQLException e) {
			String x = "Error while trying to unlink and remove coupon " + id;
			throw new MainSystemException(x);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}

	@Override
	public Coupon getCouponByTitle(String title) throws MainSystemException {
		String sql = "select * from coupon where title = ?";
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setString(1, title);
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					Coupon a = new Coupon();
					a.setId(rs.getLong("ID"));
					a.setTitle(rs.getString("TITLE"));
					a.setStartDate(rs.getDate("START_DATE"));
					a.setEndDate(rs.getDate("END_DATE"));
					a.setAmount(rs.getInt("AMOUNT"));
					a.setCouponType(CouponType.valueOf(rs.getString("TYPE")));
					a.setMessage(rs.getString("MESSAGE"));
					a.setPrice(rs.getDouble("PRICE"));
					a.setImage(rs.getString("IMAGE"));
					return a;
				}
				return null;
			}
		} catch (SQLException e) {
			String x = "Error while trying to read coupon " + title + " from DB";
			throw new MainSystemException(x, e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}
		}
	}
	
	
	@Override
	public void removeExpiredCoupons() throws MainSystemException {
		String DELET_EXPIRED = "delete from coupon where END_DATE < CURRENT_DATE"; // the sql form of the text used to delete expired date
		Connection con = null; 
		try { 
			con = ConnectionPool.getInstance().getConnection(); 
			try (PreparedStatement stm = con.prepareStatement(DELET_EXPIRED)) {
				stm.executeUpdate();
			}
		} catch (Exception e) {
			throw new MainSystemException(e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnConnection(con);
			}

		}
	}

}