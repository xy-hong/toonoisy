package DAO.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.jdbcUtil;
import entity.LockUser;
import entity.User;
import entity.UserInfo;
import entity.WebFriend;
/*
CREATE TABLE user_lock(
id VARCHAR(40) PRIMARY KEY,
COUNT INT,
lastLogin DATETIME
);
*/
public class LockDaoImpl {
	public void insert(String pk) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="insert into user_lock values(?,?,?)";
			Object[] paramValues={pk,0,null};
			qr.update(sql, paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delete(String pk) {
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql = "delete from user_lock where id=? and count=3";
			Object paramValue = pk;
			qr.update(sql, paramValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void update(String pk,int count) {
		try {
			QueryRunner qr= new QueryRunner(jdbcUtil.getDataSource());
			String sql="update user_lock set count=? where id=?";
			Object[] paramValues={count,pk};
			qr.update(sql,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void updateTime(String pk,String lastLogin) {
		try {
			QueryRunner qr= new QueryRunner(jdbcUtil.getDataSource());
			String sql="update user_lock set lastLogin=? where id=?";
			Object[] paramValues={lastLogin,pk};
			qr.update(sql,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public LockUser getLk(String pk) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select * from user_lock where id=?";
			return (LockUser)qr.query(sql,new BeanHandler(LockUser.class),new Object[]{pk});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<LockUser> listDO() {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select * from user_lock";
			return (List<LockUser>)qr.query(sql,new BeanListHandler(LockUser.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
