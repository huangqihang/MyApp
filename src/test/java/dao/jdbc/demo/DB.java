package dao.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.config.AppConfig;

/**
 * 封装数据库连接的相关API
 * 
 * 待改进：
 *  1. 从配置文件读取数据库连接配置
 * 	2. 应用数据库连接池---C3P0
 */
public class DB {
	
	private static final String DRIVER = AppConfig.getString("env", "db.driver");
	private static final String URL = AppConfig.getString("env", "db.url.spy");
	private static final String USERNAME = AppConfig.getString("env", "db.username");
	private static final String PASSWORD = AppConfig.getString("env", "db.password");
	
	public static Connection getConnect() {
		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				if(!stmt.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				if(!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
