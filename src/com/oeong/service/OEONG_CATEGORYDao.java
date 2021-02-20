package com.oeong.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_CATEGORY;
import com.oeong.entity.OEONG_USER;

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
	
	public static int insert(OEONG_CATEGORY cate) {
		String sql = "insert into OEONG_CATEGORY values(null, ?, ?)";

		Object[] params = { 
				cate.getCATE_NAME(),
				cate.getCATE_PARENT_ID(),
		};
		
		return Basedao.exectuIUD(sql, params);
	}
}
