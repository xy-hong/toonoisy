package DAO;

import entity.User;

public interface UserDAO {
	public void insert(User o);
	public void delete(String pk);
	public void update(User o);
	public User getDO(String pk);
}
