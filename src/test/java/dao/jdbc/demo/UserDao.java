package dao.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.User;

public class UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	//增
	public void save(User user) {
		String sql = "insert into t_user (name, age, sex, photo, address, birthday, reg_date)";
		sql += " values (?,?,?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConnect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.setString(3, String.valueOf(user.getSex()));
			pstmt.setString(4, user.getPhoto());
			pstmt.setString(5, user.getAddress());
			pstmt.setDate(6, new java.sql.Date(user.getRegDate().getTime())); //yyyy-MM-dd
			pstmt.setTimestamp(7, new java.sql.Timestamp(user.getRegDate().getTime())); //yyyy-MM-dd HH:mm:ss
			
			pstmt.executeUpdate();
			
			//DB.commit(conn);
			DB.rollback(conn);
			
			// 提交之后，接着执行另一个操作，由于前面已经commit提交了，后面发生异常也不会影响到之前的操作。
			logger.info("auto commit: {}", conn.getAutoCommit()); //false
			String sql2 = "update t_user set name = 'ohe2' order by id desc limit 1";
			Statement stmt = conn.createStatement();
			stmt.execute(sql2);
			DB.commit(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			DB.rollback(conn);
		} finally {
			DB.close(conn);
			DB.close(pstmt);
		}
	}

	//改
	public void update(User user) {
		String sql = "update t_user set name=?, age=?, sex=?, photo=?, address=?, birthday=?, reg_date=?";
		sql += " where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConnect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.setString(3, String.valueOf(user.getSex()));
			pstmt.setString(4, user.getPhoto());
			pstmt.setString(5, user.getAddress());
			pstmt.setDate(6, new java.sql.Date(user.getRegDate().getTime())); //yyyy-MM-dd
			pstmt.setTimestamp(7, new java.sql.Timestamp(user.getRegDate().getTime())); //yyyy-MM-dd HH:mm:ss
			pstmt.setInt(8, user.getId());
			
			pstmt.executeUpdate();
			
			DB.commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			DB.rollback(conn);
		} finally {
			DB.close(conn);
			DB.close(pstmt);
		}
	}
	
	//删
	public void delete(int id) {
		String sql = "delete from t_user where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConnect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			DB.commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			DB.rollback(conn);
		} finally {
			DB.close(conn);
			DB.close(pstmt);
		}
	}
	
	//单查
	public User get(int id) {
		String sql = "select * from t_user where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		User user = new User();
		
		try {
			conn = DB.getConnect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex").toCharArray()[0]);
				user.setPhoto(rs.getString("photo"));
				user.setAddress(rs.getString("address"));
				user.setBirthday(rs.getDate("birthday"));
				user.setRegDate(rs.getDate("reg_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(pstmt);
			DB.close(rs);
		}
		return user;
	}
	
	//多查
	public List<User> query(String name) {
		String sql = "select * from t_user where name like ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<User> users = new ArrayList<User>();
		
		try {
			conn = DB.getConnect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+name+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				users.add(user);
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex").toCharArray()[0]);
				user.setPhoto(rs.getString("photo"));
				user.setAddress(rs.getString("address"));
				user.setBirthday(rs.getDate("birthday"));
				user.setRegDate(rs.getDate("reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(pstmt);
			DB.close(rs);
		}
		return users;
	}
}
