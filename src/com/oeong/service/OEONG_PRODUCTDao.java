package com.oeong.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_PRODUCT;
import com.oeong.entity.OEONG_USER;

public class OEONG_PRODUCTDao {

	public static int insert(OEONG_PRODUCT p) {
		String sql = "insert into OEONG_PRODUCT values(null,?,?,?,?,?,?,?)";

		Object[] params= {
	   			  p.getPRODUCT_NAME(),
	   			  p.getPRODUCT_DESCRIPTION(),
	   			  p.getPRODUCT_PRICE(),
	   			  p.getPRODUCT_STOCK(),
	   			  p.getPRODUCT_FID(),
	   			  p.getPRODUCT_CID(),
	   			  p.getPRODUCT_FILENAME()
	   	  };
		return Basedao.exectuIUD(sql, params);
	}
	
	public static ArrayList<OEONG_PRODUCT> selectALL() {
		ArrayList<OEONG_PRODUCT> list = new ArrayList<OEONG_PRODUCT>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = Basedao.getconn();

		try {
			String sql = "select * from OEONG_PRODUCT";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				OEONG_PRODUCT p = new OEONG_PRODUCT(
						rs.getInt("PRODUCT_ID"),
						rs.getString("PRODUCT_NAME"),
						rs.getString("PRODUCT_DESCRIPTION"),
						rs.getInt("PRODUCT_PRICE"),
						rs.getInt("PRODUCT_STOCK"),
						rs.getInt("PRODUCT_FID"),
						rs.getInt("PRODUCT_CID"),
						rs.getString("PRODUCT_FILENAME")
						);
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	
	public static ArrayList<OEONG_PRODUCT> selectByFid(int fid) {
		ArrayList<OEONG_PRODUCT> list = new ArrayList<OEONG_PRODUCT>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = Basedao.getconn();

		try {
			String sql = "select * from OEONG_PRODUCT where PRODUCT_FID=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);
			rs = ps.executeQuery();

			while (rs.next()) {
				OEONG_PRODUCT p = new OEONG_PRODUCT(
						rs.getInt("PRODUCT_ID"),
						rs.getString("PRODUCT_NAME"),
						rs.getString("PRODUCT_DESCRIPTION"),
						rs.getInt("PRODUCT_PRICE"),
						rs.getInt("PRODUCT_STOCK"),
						rs.getInt("PRODUCT_FID"),
						rs.getInt("PRODUCT_CID"),
						rs.getString("PRODUCT_FILENAME")
						);
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	
	public static ArrayList<OEONG_PRODUCT> selectByCid(int cid) {
		ArrayList<OEONG_PRODUCT> list = new ArrayList<OEONG_PRODUCT>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = Basedao.getconn();

		try {
			String sql = "select * from OEONG_PRODUCT where PRODUCT_CID=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			rs = ps.executeQuery();

			while (rs.next()) {
				OEONG_PRODUCT p = new OEONG_PRODUCT(
						rs.getInt("PRODUCT_ID"),
						rs.getString("PRODUCT_NAME"),
						rs.getString("PRODUCT_DESCRIPTION"),
						rs.getInt("PRODUCT_PRICE"),
						rs.getInt("PRODUCT_STOCK"),
						rs.getInt("PRODUCT_FID"),
						rs.getInt("PRODUCT_CID"),
						rs.getString("PRODUCT_FILENAME")
						);
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
}
