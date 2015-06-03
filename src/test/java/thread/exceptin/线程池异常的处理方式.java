package thread.exceptin;

public class 线程池异常的处理方式 {
	/**
	线程池提供了2种任务执行方式：
	1. execute	异步任务没有返回值 
	2. submit	异步任务执行有返回值
	
	对于execute提交的任务，才能将它抛出的异常交给未捕获异常处理器----UncaughtExceptionHandler设置将被利用到
	
	而通过submit提交的任务，无论抛出的RuntimeException还是CheckedException，都将被认为是任务返回结果的一部分
	如果由1个submit提交的任务由于抛出异常而结束，
	那么这个异常将被Future.get封装在ExecutionException中重新被抛出！
	
	
	---》容易犯错的地方：通过submit提交了任务，但是又没有通过Future.get获取结果，当发生异常时，异常将丢失！
	避免的办法：
		因此，对于没有返回值的异步任务，请使用execute执行任务。
		或者，更好的做法：
		1. 记得调用get()，获取异步执行的结果，此时有异常会自动抛出。
		2. 自定义线程池类，对于普通线程池，可继承ThreadPoolExecutor
			对于任务调度需求，则继承ScheduledThreadPoolExecutor
			覆盖父类的afterExecute()，将异常用日志记录下来。
			这样可以确保对于那些没有调用future.get()的程序，发生异常时不会丢失异常！！！
	 */
}
