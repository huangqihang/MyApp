package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import thread.helper.ThreadHelper;

public class ExecutorsTestDriver {
	
	public static void main(String[] args) {
//		fixThreadPool();
//		cachedThreadPool();
//		singleThreadPool();
//		scheduledThreadPool();
	}
	

	// 具有任务调度功能的线程池：延迟、固定频率
	private static void scheduledThreadPool() {
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);
		Runnable command = new Runnable() {
			public void run() {
				System.out.println(ThreadHelper.getName()+"\t"+System.currentTimeMillis());
				ThreadHelper.sleep(TimeUnit.SECONDS, 3); // 执行时间大于period调度频率，则下一次调度将被推迟

				int i =  1 / 0; // 发生异常，将导致后面的任务调度被终止
			}
		};
		
		long initialDelay = 1000; // 延迟1000ms
		long period = 2000; // 每隔2000ms调度1次
		scheduledExecutor.scheduleAtFixedRate(command, initialDelay, period, TimeUnit.MILLISECONDS);
	}

	// 单一线程池：如果线程异常结束，会创建新的线程来替代
	private static void singleThreadPool() {
		ExecutorService exe = Executors.newSingleThreadExecutor();
		
	}

	// 线程池规模不受限制：当规模超出处理需求时，将自动回收空闲线程
	private static void cachedThreadPool() {
		ExecutorService exe = Executors.newCachedThreadPool();
	}

	// 固定大小的线程池：如果某个线程发生Exception而结束，线程池将自动补充新的线程
	private static void fixThreadPool() {
		ExecutorService exe = Executors.newFixedThreadPool(10);
	}
	
}
