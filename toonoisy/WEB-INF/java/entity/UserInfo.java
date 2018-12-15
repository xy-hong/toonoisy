package entity;

public class UserInfo {
	private String id;
	private String name = "";
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + "]";
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
