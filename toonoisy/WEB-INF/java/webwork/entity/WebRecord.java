package entity;

import java.util.Date;

public class WebRecord {
	private Integer record_id;
	private String room_name;
	private String create_time;
	private Date end_time;
	private Date record;
	public Integer getRecord_id() {
		return record_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	@Override
	public String toString() {
		return "WebRecord [record_id=" + record_id + ", room_name=" + room_name
				+ ", create_time=" + create_time + ", end_time=" + end_time
				+ ", record=" + record + "]";
	}
	public String getCreate_time() {
		return create_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public Date getRecord() {
		return record;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public void setRecord(Date record) {
		this.record = record;
	}


}
