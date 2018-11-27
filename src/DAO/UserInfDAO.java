package DAO;

import entity.UserInfo;

public interface UserInfDAO {
	public void insert(UserInfo o);
	public void delete(String pk);
	public void update(UserInfo o);
	public UserInfo getDO(String pk);
}
