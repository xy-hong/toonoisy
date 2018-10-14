package DAO.Impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import util.jdbcUtil;
import entity.User;
import DAO.UserDAO;

public class UserDAOImpl implements UserDAO{

	@Override
	public void insert(User o) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="insert into user values(?,?)";
			Object[] paramValues={o.getId(),o.getPassword()};
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
			String sql = "delete from user where id=?";
			Object paramValue = pk;
			qr.update(sql, paramValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(User o) {
		try {
			QueryRunner qr= new QueryRunner(jdbcUtil.getDataSource());
			String sql="update user set id=?,password=? where id=?";
			Object[] paramValues={o.getId(),o.getPassword()};
			qr.update(sql,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getDO(String pk) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select id,password from user where id=?";
			return (User)qr.query(sql,new BeanHandler(User.class),new Object[]{pk});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
