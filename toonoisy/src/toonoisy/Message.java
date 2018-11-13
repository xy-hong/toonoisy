package toonoisy;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Message {
	private String type;
	private String send;
	private String receive;
	private String createTime;
	private String data;
	
	public Message() {
		
	}
	
	public Message(String type, String send, String receive, String time, String data) {
		super();
		this.type = type;
		this.send = send;
		this.receive = receive;
		this.createTime = time;
		this.data = data;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String time) {
		this.createTime = time;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toJSONString() {
		return JSON.toJSONString(this);
		
	}
	

}
