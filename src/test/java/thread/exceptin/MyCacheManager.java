package thread.exceptin;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import thread.executor.threadfactory.MyScheduledThreadPoolExecutor;
import thread.helper.ThreadHelper;
import app.util.thread.MyThreadFactory;

public class MyCacheManager {
	
	private static final int POOLSZ = 1;
	
	private final static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();
	
	private final ScheduledExecutorService scheduledExecutor;
	
	public MyCacheManager() {
		
		scheduledExecutor = new MyScheduledThreadPoolExecutor(10, new MyThreadFactory("myThreadFactory"));

		// 定时刷新缓存
		scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			public void run() {
				refresh();
			}
		}, 0, 1, TimeUnit.SECONDS);
		
	}
	
	private void refresh() {
		// load config from db
		
		// 这里发生的异常，将在自定义的线程池的afterExecute()方法中北记录。
		System.out.println(ThreadHelper.getName()+"\t refresh" + (1/0)); 
		cache.clear();
		cache.put("key", new Random().nextLong());
	}
	
	public Map<String, Object> getCache() {
		return cache;
	}
	
	public static void main(String[] args) {
		MyCacheManager cache = new MyCacheManager();
		while(true) {
			ThreadHelper.sleep(TimeUnit.MILLISECONDS, 600);
			Map<String,Object> map = cache.getCache();
			System.out.println(map);
		}
	}
	
}

