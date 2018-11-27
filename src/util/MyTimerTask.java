package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import entity.LockUser;
import DAO.Impl.LockDaoImpl;

public class MyTimerTask implements ServletContextListener{
	private Timer timer;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(timer!=null) timer.cancel();
	}
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				work();
			}
		}, 1000, 1*1000*60);// 1mintues
	}
	
	private void work() {
		try {
			LockDaoImpl ld=new LockDaoImpl();
			List<LockUser> lk=ld.listDO();
			if(!lk.isEmpty()){
				for(LockUser user:lk){
					if(user.getLastLogin()!=null&&user.getCount()>=1){
					Date lastTime=sdf1.parse(user.getLastLogin());
					//System.out.println(getDatePoor(new Date(),lastTime));
					if(getDatePoor(new Date(),lastTime)>=10){
						ld.update(user.getId(), 0);
						ld.updateTime(user.getId(), null);
					}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static long getDatePoor(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;

		long nh = 1000 * 60 * 60;

		long nm = 1000 * 60;

		long diff = endDate.getTime() - nowDate.getTime();

		// 计算差多少天

		long day = diff / nd;

		// 计算差多少小时

		long hour = diff % nd / nh;

		// 计算差多少分钟

		long min = diff % nd % nh / nm;

		// 计算差多少秒//输出结果

		// long sec = diff % nd % nh % nm / ns;

		return min;

		}
}
