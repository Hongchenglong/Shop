package com.oeong.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_CART;

public class OEONG_CARTDao {
	public static int insert(OEONG_CART cart) {
		String sql = "insert into OEONG_CART values(0, ?, ?, ?, ?, ?, ?, ?, 1)";
		Object[] params = { cart.getCart_p_filename(), cart.getCart_p_name(), cart.getCart_p_price(),
				cart.getCart_quantity(), cart.getCart_p_stock(), cart.getCart_p_id(), cart.getCart_u_id() };
		return Basedao.exectuIUD(sql, params);
	}

	public static ArrayList<OEONG_CART> getCart(String id) {
		ArrayList<OEONG_CART> list = new ArrayList<OEONG_CART>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = Basedao.getconn();

		try {
			String sql = "select * from OEONG_CART where CART_U_ID=? and CART_VALID=1 order by CART_ID desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				OEONG_CART c = new OEONG_CART(rs.getInt("cart_id"), rs.getString("cart_p_filename"),
						rs.getString("cart_p_name"), rs.getInt("cart_p_price"), rs.getInt("cart_quantity"),
						rs.getInt("cart_p_stock"), rs.getInt("cart_p_id"), rs.getString("cart_u_id"),
						rs.getInt("cart_valid"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}

	public static OEONG_CART getCartShop(String uid, String pid) {
		OEONG_CART es = null;
		ResultSet rs = null; // 结果集
		PreparedStatement ps = null; // 预处理
		Connection conn = Basedao.getconn(); // 获取连接对象

		try {
			String sql = "select * from OEONG_CART where CART_U_ID=? "
					+ "and CART_P_ID=? and CART_VALID=1 order by CART_ID desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, Integer.parseInt(pid));
			rs = ps.executeQuery();

			while (rs.next()) {
				es = new OEONG_CART(rs.getInt("cart_id"), rs.getString("cart_p_filename"), rs.getString("cart_p_name"),
						rs.getInt("cart_p_price"), rs.getInt("cart_quantity"), rs.getInt("cart_p_stock"),
						rs.getInt("cart_p_id"), rs.getString("cart_u_id"), rs.getInt("cart_valid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return es;
	}

	public static int updatenum(int esid, int count) {
		String sql = "update OEONG_CART set cart_quantity=? where cart_id=?";
		Object[] params = { count, esid };
		return Basedao.exectuIUD(sql, params);
	}

	public static int getDeleteDD(int id) {
		String sql = "delete from OEONG_CART where cart_id=?";
		Object[] params = { id };
		return Basedao.exectuIUD(sql, params);
	}
	
	public static OEONG_CART getCartShop(String id) {
		OEONG_CART es = null;
		ResultSet rs = null; // 结果集
		PreparedStatement ps = null; // 预处理
		Connection conn = Basedao.getconn(); // 获取连接对象

		try {
			String sql = "select * from OEONG_CART where CART_ID=? "
					+ " and CART_VALID=1 order by CART_ID desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				es = new OEONG_CART(rs.getInt("cart_id"), rs.getString("cart_p_filename"), rs.getString("cart_p_name"),
						rs.getInt("cart_p_price"), rs.getInt("cart_quantity"), rs.getInt("cart_p_stock"),
						rs.getInt("cart_p_id"), rs.getString("cart_u_id"), rs.getInt("cart_valid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return es;
	}

}
