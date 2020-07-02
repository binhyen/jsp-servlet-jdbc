package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		UserModel user = new UserModel();
		try {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("username"));
			user.setFullName(rs.getString("fullname"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
