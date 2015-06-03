package thread.exceptin;

import ch.qos.logback.classic.Logger;



/**
 * 自定义的异常捕获处理机制
 * 
 * 默认的处理方式是：将异常输出到控制台
 * 
 * 更好的做法是，将异常记录到日志文件中
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println();
	}
}