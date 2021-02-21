package com.oeong.service;

import com.oeong.dao.Basedao;
import com.oeong.entity.OEONG_PRODUCT;

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
}
