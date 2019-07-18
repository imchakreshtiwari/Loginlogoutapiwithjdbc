package com.acis.login.LoginApi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acis.login.LoginApi.model.AppUser;

public class AppUserMapper implements RowMapper<AppUser> {

	  public static final String BASE_SQL = "Select u.User_Id, u.User_Name, u.Encryted_Password From App_User u ";
	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		long User_Id=rs.getLong("User_Id");
		String User_Name=rs.getString("User_Name");
		String Encryted_Password=rs.getString("Encryted_Password");
		
		return new AppUser(User_Id,User_Name,Encryted_Password);
	}
	

	
	
}
