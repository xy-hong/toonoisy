package toonoisy;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Message {
	private String type;
	private String send;
	private String receive;
	private Date time;
	private String data;
	
	public Message(String type, String send, String receive, Date time, String data) {
		super();
		this.type = type;
		this.send = send;
		this.receive = receive;
		this.time = time;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
