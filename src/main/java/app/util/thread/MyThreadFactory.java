package app.util.thread;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
	
	private final String poolName;
	
	public MyThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	public Thread newThread(Runnable r) {
		Thread t = new MyAppThread(r, poolName);
		return t;
	}

}
