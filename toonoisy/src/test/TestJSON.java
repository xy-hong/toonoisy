package test;

import com.alibaba.fastjson.JSON;

import toonoisy.Message;

public class TestJSON {
	public static void main(String[] args) {
		Message m = new Message();
		m.setType("text");
		m.setData("豪大大");
		m.setSend("1015@qq.com");
		m.setReceive("public");
		m.setCreateTime("2018/10/28 下午7:30:03");
		
		System.out.println();
		Message ss= JSON.parseObject(m.toJSONString(),Message.class);
		System.out.println(ss.toJSONString());
	}
}
