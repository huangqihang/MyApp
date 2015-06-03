package thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask可以用来执行异步任务
 * 
 * 在未来某个时间点可以获取到任务结果---底层有Callable支撑
 * 用法：
 * 	1. 开启异步任务，此任务可能比较耗时（在使用计算结果之前就启动）
 *  2. 执行其它工作
 *  3. 其它工作执行完成后，获取异步任务的结果：此时若任务尚未完成，则等待，否则直接获取到结果
 *  4. 继续后续流程
 */
public class FutureTaskTestDriver {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		traditionalTask();
		futureTask();
	}
	
	// 同步执行耗时 = 任务1耗时 + 任务2耗时
	private static void traditionalTask() {
		long start = System.currentTimeMillis();
		
		String resource = loadResource();
		System.out.println(resource);
		
		otherThing();
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	// 异步执行耗时 = Max(任务1耗时，任务2耗时)
	private static void futureTask() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
				return loadResource();
			}
		});
		new Thread(futureTask).start();
		
		otherThing();
		
		String resource = futureTask.get();
		System.out.println(resource);
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	private static void otherThing() {
		sleep(5000);
	}

	private static String loadResource() {
		sleep(3000);
		return Thread.currentThread().getName()+" \t laod resource from database";
	}
	
	private static void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {}
	}

}
