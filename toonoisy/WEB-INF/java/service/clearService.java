package service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class clearService extends TimerTask{
	
	static {
		Timer timer = new Timer("诗人");
		Date date = new Date(System.currentTimeMillis());
		//long time = 1000*60*60*24*3;  //间隔时间执行
		long time = 1000L*10L;   //测试感觉时间少了一半
		
		timer.schedule(new clearService(), date,time);
		
	}

	@Override
	public void run() {
		
		System.out.println(new Date());
	}
	
	
}
