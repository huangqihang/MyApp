package thread.executor.threadfactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.util.thread.MyAppThread;
import app.util.thread.MyThreadFactory;

public class TestDriver {
	public static void main(String[] args) {
		
		MyAppThread.setDebug(true);
		
		ScheduledExecutorService scheduledExec = 
				new MyScheduledThreadPoolExecutor(10, new MyThreadFactory("MyAppThreadPool"));
		
		scheduledExec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				int i = 1 / 0;
				System.out.println(Thread.currentThread().getUncaughtExceptionHandler());
				System.out.println(Thread.currentThread().getName());
			}
		}, 0, 3, TimeUnit.SECONDS);
		
	}
}
