package DAO.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.jdbcUtil;
import entity.User;
import entity.WebFriend;
import DAO.FriendDAO;

public class FriendDAOImpl implements FriendDAO{
	static String tableName;
	
	 
	public void setTname(User o) {
		tableName = o.getId() + "_friend";		
	}
	
	public void createTable() {
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql ="CREATE  TABLE `webchat`.`"+tableName+"` (  `friend_id` VARCHAR(40) NOT NULL ,  `friend_nick` VARCHAR(40) NULL ,  PRIMARY KEY (`friend_id`) )";
			qr.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void dropTable(){
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql="drop table "+tableName+"";
			qr.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	 
	public void insert(WebFriend o) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="insert into "+tableName+" values(?,?)";
			Object[] paramValues={o.getFriend_id(),o.getFriend_nick()};
			qr.update(sql, paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	 
	public void delete(String pk) {
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql = "delete from "+tableName+" where friend_id=?";
			Object paramValue = pk;
			qr.update(sql, paramValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	 
	public void update(WebFriend o,String id) {
		try {
			QueryRunner qr= new QueryRunner(jdbcUtil.getDataSource());
			String sql="update "+tableName+" set friend_id=?,friend_nick=? where friend_id=?";
			Object[] paramValues={o.getFriend_id(),o.getFriend_nick(),id};
			qr.update(sql,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	 
	public WebFriend getDO(String pk) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select friend_id,friend_nick from "+tableName+" where friend_id=?";
			return (WebFriend)qr.query(sql,new BeanHandler(WebFriend.class),new Object[]{pk});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	 
	public List<WebFriend> listDO() {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select friend_id,friend_nick from "+tableName+"";
			return (List<WebFriend>)qr.query(sql,new BeanListHandler(WebFriend.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
