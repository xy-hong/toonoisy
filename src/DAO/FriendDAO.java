package DAO;

import java.util.List;

import entity.User;
import entity.WebFriend;

public interface FriendDAO {
	public void setTname(User o);
	public void dropTable();
	public void createTable();
	public void insert(WebFriend o);
	public void delete(String pk);
	public void update(WebFriend o,String id);
	public WebFriend getDO(String pk);
	public List<WebFriend> listDO();
	//TODO ++FriendDAO(String userId)
}
