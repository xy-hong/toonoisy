package entity;

public class LockUser {
	private String id;
	private int count;
	private String lastLogin;
	public String getId() {
		return id;
	}
	public int getCount() {
		return count;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
}
