package app.util.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAppThread extends Thread {
	
	public static final String DEFAULT_NAME = "MyAppThread";
	private static volatile boolean debugLifeCycle = false;
	private static final AtomicInteger created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	private static final Logger log = Logger.getAnonymousLogger();
	
	public MyAppThread(Runnable r) {
		this(r, DEFAULT_NAME);
	}

	public MyAppThread(Runnable r, String name) {
		super(r, name + "-" + created.incrementAndGet());
		/*this.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
			}
		});*/
	}
	
	public void run() {
		boolean debug = debugLifeCycle;
		if(debug) log.log(Level.FINE, "Created" + getName());
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if(debug) log.log(Level.FINE, "Exiting " + getName());
		}
	}
	
	public static int getThreadsCreated() { return created.get(); }
	public static int getThreadsAlive() { return alive.get(); }
	
	public static boolean getDebug() { 
		return debugLifeCycle; 
	}
	public static void setDebug(boolean b) { 
		debugLifeCycle = b;
	}
	
}
