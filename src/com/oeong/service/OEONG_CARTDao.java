package com.oeong.service;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_CART;

public class OEONG_CARTDao {
	public static int insert(OEONG_CART cart) {
		String sql = "insert into cart values(?, ?, ?, ?, ?, ?, ?, 1)";
		Object[] params = {
				cart.getCart_p_filename(),
				cart.getCart_p_name(),
				cart.getCart_p_price(),
				cart.getCart_quantity(),
				cart.getCart_p_stock(),
				cart.getCart_p_id(),
				cart.getCart_u_id()
		};
		return Basedao.exectuIUD(sql, params);
	}
}
