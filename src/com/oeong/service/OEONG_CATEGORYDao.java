package com.oeong.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_CATEGORY;
import com.oeong.entity.OEONG_USER;

//修改数据库Object[] params 
//查询数据库ps.setString()
public class OEONG_CATEGORYDao {

	public static ArrayList<OEONG_CATEGORY> selectALL() {
		ArrayList<OEONG_CATEGORY> list = new ArrayList<OEONG_CATEGORY>();

		ResultSet rs = null; // 结果集
		PreparedStatement ps = null; // 预处理
		Connection conn = Basedao.getconn(); 

		try {
			String sql = "";
			sql = "select * from OEONG_CATEGORY";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				OEONG_CATEGORY c = new OEONG_CATEGORY(rs.getInt("CATE_ID"), rs.getString("CATE_NAME"), rs.getInt("CATE_PARENT_ID"));
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
	
	public static OEONG_CATEGORY selectById(int id) {
		OEONG_CATEGORY cate = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = Basedao.getconn();

		try {
			String sql = "";
			sql = "select * from OEONG_CATEGORY  where CATE_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				cate = new OEONG_CATEGORY(rs.getInt("CATE_ID"), rs.getString("CATE_NAME"), rs.getInt("CATE_PARENT_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return cate;
	}
	
	public static int insert(OEONG_CATEGORY cate) {
		String sql = "insert into OEONG_CATEGORY values(null, ?, ?)";

		Object[] params = { 
				cate.getCATE_NAME(),
				cate.getCATE_PARENT_ID(),
		};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int update(OEONG_CATEGORY cate) {
		String sql = "update OEONG_CATEGORY set CATE_NAME=?, CATE_PARENT_ID=? where CATE_ID=?";
		
		Object[] params = { 
				cate.getCATE_NAME(),
				cate.getCATE_PARENT_ID(),
				cate.getCATE_ID()
		};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int del(int id) {
		String sql = "delete from OEONG_CATEGORY where CATE_ID=?";
		Object[] params = {id};
		return Basedao.exectuIUD(sql, params);
	}
}
