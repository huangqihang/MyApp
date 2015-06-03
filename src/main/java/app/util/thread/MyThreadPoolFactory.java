package app.util.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MyThreadPoolFactory {
	/**
	 * 创建1个固定大小的线程池，并采用有界队列以及“调用者运行”的饱和策略
	 */
	public static ThreadPoolExecutor newThreadPoolExecutor() {
		return newThreadPoolExecutor(3, 10, 60000, 500);
	}
	
	/**
	 * 
	 * @param corePoolSize 最小线程数
	 * @param maximumPoolSize 最大线程数
	 * @param keepAliveTime 空闲线程存活时间，单位毫秒
	 * @param CAPACITY 阻塞队列容量
	 * @return
	 */
	public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, int keepAliveTime, int CAPACITY) {
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(CAPACITY);
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				corePoolSize, 
				maximumPoolSize, 
				keepAliveTime, 
				TimeUnit.MILLISECONDS, 
				workQueue,
				new MyThreadFactory("MY_POOL"));
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.prestartCoreThread();
		
		return executor;
	
	}
	
	public static void main(String[] args) {
		ThreadPoolExecutor executor = MyThreadPoolFactory.newThreadPoolExecutor();
		executor.execute(new Runnable() {
			public void run() {
				System.out.println(1/0);
			}
		});
	}
}
