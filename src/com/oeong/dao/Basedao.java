package com.oeong.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Basedao {
	static {
		// 加载驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("数据库连接");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getconn() {
		// 创建一个连接对象
		Connection conn = null;
		try { // 设置数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oeongshop?useSSL=false&serverTimezone=UTC",
					"root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 执行
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int exectuIUD(String sql, Object[] params) {
		int count = 0;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;

		try {
			// 准备SQL
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(null, ps, conn);
		}
		return count;
	}

	public static void closeall(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) // 结果集
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
