package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbsTractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
//		String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND status = ?";
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		
		List<UserModel> user = query(sql.toString(), new UserMapper(), userName, password, status);
		 
		return user.isEmpty() ? null : user.get(0);
	}

}
