package thread.helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadHelper {
	
	/**
	 * 统一处理ExecutionException异常
	 */
	public static RuntimeException launderThrowable(Throwable t) {
		if(t instanceof RuntimeException) {
			return (RuntimeException)t;
		} else if(t instanceof Error) {
			throw (Error)t;
		} else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}
	
	public static void sleep(TimeUnit unit, long timeout) {
		try {
			unit.sleep(timeout);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // 恢复中断
		}
	}
	
	public static void randomSleep(TimeUnit unit, int timeout) {
		try {
			unit.sleep(new Random().nextInt(timeout));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // 恢复中断
		}
	}
	
	public static void interrupt() {
		Thread.currentThread().interrupt();
	}

	public static String getName() {
		return Thread.currentThread().getName();
	}
	
}
