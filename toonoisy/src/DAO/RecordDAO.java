package DAO;

import java.util.List;

import entity.User;
import entity.WebFriend;
import entity.WebRecord;

public interface RecordDAO {
	public void setTname(User o);
	public void createTable();
	public void dropTable();
	public void insert(WebRecord o);
	public void delete(int pk);
	public void update(WebRecord o,String id);
	public WebRecord getDO(int pk);
	public List<WebRecord> listDO();
	//TODO ++RecordDAO(String userId)
}
