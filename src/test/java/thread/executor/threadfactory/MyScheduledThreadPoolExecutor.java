package thread.executor.threadfactory;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

	private static final Log log = LogFactory.getLog(MyScheduledThreadPoolExecutor.class);
	
	public MyScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}

	public MyScheduledThreadPoolExecutor(int corePoolSize,
			RejectedExecutionHandler handler) {
		super(corePoolSize, handler);
	}

	public MyScheduledThreadPoolExecutor(int corePoolSize,
			ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, threadFactory, handler);
	}



	public MyScheduledThreadPoolExecutor(int corePoolSize,
			ThreadFactory threadFactory) {
		super(corePoolSize, threadFactory);
	}



	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		printException(r, t);
	}

	private static void printException(Runnable r, Throwable t) {
		if (t == null && r instanceof Future<?>) {
	        try {
	            Future<?> future = (Future<?>) r;
	            if (future.isDone())
	                future.get();
	        } catch (CancellationException ce) {
	            t = ce;
	        } catch (ExecutionException ee) {
	            t = ee.getCause();
	        } catch (InterruptedException ie) {
	            Thread.currentThread().interrupt(); // ignore/reset
	        }
	    }
	    if (t != null)
	        log.error(t.getMessage(), t);
	}




}
