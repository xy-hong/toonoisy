package DAO.Impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import util.jdbcUtil;
import entity.User;
import entity.UserInfo;
import DAO.UserInfDAO;

public class UserInfDAOImpl implements UserInfDAO{

	@Override
	public void insert(UserInfo o) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="insert into user_inf values(?,?)";
			Object[] paramValues={o.getId(),o.getName()};
			qr.update(sql, paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String pk) {
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql = "delete from user_inf where id=?";
			Object paramValue = pk;
			qr.update(sql, paramValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(UserInfo o) {
		try {
			QueryRunner qr= new QueryRunner(jdbcUtil.getDataSource());
			String sql="update user_inf set id=?,name=? where id=?";
			Object[] paramValues={o.getId(),o.getName()};
			qr.update(sql,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserInfo getDO(String pk) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select id,name from user_inf where id=?";
			return (UserInfo)qr.query(sql,new BeanHandler(UserInfo.class),new Object[]{pk});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
