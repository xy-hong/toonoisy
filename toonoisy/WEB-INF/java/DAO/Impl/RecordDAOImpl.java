package DAO.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.jdbcUtil;
import entity.User;
import entity.WebFriend;
import entity.WebRecord;
import DAO.RecordDAO;

public class RecordDAOImpl implements RecordDAO{
	static String tableName;
	
	public void setTname(User o) {
		tableName = o.getId() + "_record";		
	}
	
	public void createTable() {
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql = "CREATE  TABLE `"+tableName+"` (  `record_id` INT NOT NULL AUTO_INCREMENT ,  `room_name` VARCHAR(50) NULL ,  `create_time` DATETIME NOT NULL ,  `end_time` DATETIME NOT NULL ,  `record` LONGTEXT NOT NULL ,  PRIMARY KEY (`record_id`) )";
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
	
	@Override
	public void insert(WebRecord o) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="insert into "+tableName+" values(?,?,?,?,?)";
			Object[] paramValues={o.getRecord_id(),o.getRoom_name(),o.getCreate_time(),o.getEnd_time(),o.getRecord()};
			qr.update(sql, paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(WebRecord o,String id) {
		try {
			QueryRunner qr= new QueryRunner(jdbcUtil.getDataSource());
			String sql="update "+tableName+" set record_id=?,room_name=?,create_time=?,end_time=?,record=? where record_id=?";
			Object[] paramValues={o.getRecord_id(),o.getRoom_name(),o.getCreate_time(),o.getEnd_time(),id};
			qr.update(sql,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public WebRecord getDO(int pk) {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select * from "+tableName+" where record_id=?";
			return (WebRecord)qr.query(sql,new BeanHandler(WebRecord.class),new Object[]{pk});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<WebRecord> listDO() {
		try {
			QueryRunner qr=new QueryRunner(jdbcUtil.getDataSource());
			String sql="select * from "+tableName+"";
			return (List<WebRecord>)qr.query(sql,new BeanListHandler(WebRecord.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public void delete(int pk) {
		try {
			QueryRunner qr = new QueryRunner(jdbcUtil.getDataSource());
			String sql = "delete from "+tableName+" where record_id=?";
			Object paramValue = pk;
			qr.update(sql, paramValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
