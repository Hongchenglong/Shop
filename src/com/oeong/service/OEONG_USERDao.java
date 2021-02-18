package com.oeong.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_USER;

public class OEONG_USERDao {
	/**
	 * 加入数据库
	 * 
	 * @param u
	 * @return
	 */
	public static int insert(OEONG_USER u) {
		String sql = "insert into OEONG_USER values(?,?,?,?, DATE_FORMAT(?, '%Y-%m-%d'),?,?,?,?,?)";

		Object[] params = { u.getUSER_ID(), u.getUSER_NAME(), u.getUSER_PASSWORD(), u.getUSER_SEX(),
				u.getUSER_BIRTHDAY(), u.getUSER_IDENTITY_CODE(), u.getUSER_EMAIL(), u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(), u.getUSER_STATUS() };
		return Basedao.exectuIUD(sql, params);
	}
	
	/**
	 * 更新用户信息
	 * 
	 * @param u
	 * @return
	 */
	public static int update(OEONG_USER u) {
  	    String sql = "update OEONG_USER set USER_NAME=?, USER_PASSWORD=?, USER_SEX=?, "
  	    		+ "USER_BIRTHDAY=DATE_FORMAT(?, '%Y-%m-%d'), USER_IDENTITY_CODE=?, "
  	    		+ "USER_EMAIL=?, USER_MOBILE=?, USER_ADDRESS=?, USER_STATUS=? where USER_ID=?"; 

		Object[] params = { 
				u.getUSER_NAME(), u.getUSER_PASSWORD(), u.getUSER_SEX(),
				u.getUSER_BIRTHDAY(), u.getUSER_IDENTITY_CODE(), 
				u.getUSER_EMAIL(), u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(), u.getUSER_STATUS(), u.getUSER_ID()};
		System.out.println("更新成功");
		return Basedao.exectuIUD(sql, params);
	}


	/**
	 * 获取总记录数和总页数
	 * 
	 * @param count
	 * @return
	 */
	public static int[] totalPage(int count, String keywords) {
		// 总记录数，总页数
		int arr[] = { 0, 1 };
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = Basedao.getconn();

		try {
			String sql = "";
			if (keywords != null) {
				sql = "select * from OEONG_USER where USER_NAME like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + keywords + "%");
			} else {
				sql = "select count(*) from OEONG_USER";
				ps = conn.prepareStatement(sql);
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				arr[0] = rs.getInt(1);

				if (arr[0] % count == 0) {
					arr[1] = arr[0] / count;
				} else {
					arr[1] = arr[0] / count + 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}

		return arr;

	}

	public static ArrayList<OEONG_USER> selectALL(int cpage, int count, String keywords) {
		ArrayList<OEONG_USER> list = new ArrayList<OEONG_USER>();

		// 声明结果集
		ResultSet rs = null;
		PreparedStatement ps = null;
		// 获取连接对象
		Connection conn = Basedao.getconn();

		try {
			String sql = "";
			if (keywords != null) { // 模糊搜索
				sql = "select * from OEONG_USER where USER_NAME like ? order by USER_BIRTHDAY desc limit ?, ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + keywords + "%");
				ps.setInt(2, (cpage - 1) * count);
				ps.setInt(3, count);
			} else {
				sql = "select * from OEONG_USER order by USER_BIRTHDAY desc limit ?, ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (cpage - 1) * count);
				ps.setInt(2, count);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				OEONG_USER u = new OEONG_USER(rs.getString("USER_ID"), rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"), rs.getString("USER_BIRTHDAY"),
						rs.getString("USER_IDENTITY_CODE"), rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}

	public static OEONG_USER selectById(String id) {
		OEONG_USER u = null;
		
		// 声明结果集
		ResultSet rs = null;
		PreparedStatement ps = null;
		// 获取连接对象
		Connection conn = Basedao.getconn();

		try {
			String sql = "";
			sql = "select o.*, DATE_FORMAT(o.user_birthday, '%Y-%m-%d')birthday from OEONG_USER o where USER_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new OEONG_USER(rs.getString("USER_ID"), rs.getString("USER_NAME"), rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"), rs.getString("birthday"), rs.getString("USER_IDENTITY_CODE"),
						rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"), rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return u;
	}
}
