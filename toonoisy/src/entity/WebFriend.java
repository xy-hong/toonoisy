package entity;

public class WebFriend {
	private String friend_id;
	private String friend_nick;
	public String getFriend_id() {
		return friend_id;
	}
	public String getFriend_nick() {
		return friend_nick;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	public void setFriend_nick(String friend_nick) {
		this.friend_nick = friend_nick;
	}
	@Override
	public String toString() {
		return "WebFriend [friend_id=" + friend_id + ", friend_nick="
				+ friend_nick + "]";
	}
	
}
