package thread.concurrent.syncutil;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch- 可统一控制若干线程的“开始执行时机”，以及最终的结束时机
 * 倒计时：当内部值减为0时，表示所有线程都已准备就绪，所有线程的await()被释放，开始执行
 * 
 * 启动门：控制线程的并发开始时间，这样可以让所有线程在同一起跑线上等待
 * 结束门：等待最后1个线程执行完成后，所有线程才能通过结束门
 * 
 * 应用场景：
 * 	1.等待直到某个操作的所有参与者都准备就绪后，才开始继续执行。---如所有游戏玩家都准备就绪后，才开始发牌。
 *  2.确保某个服务所依赖的其他服务都已经启动后，才启动自己。---在其他服务的闭锁上等待，这些服务都启动完成后，闭锁被释放
 *  3.确保某个计算在需要的其它资源都被初始化后才继续执行。---“资源R已被初始化”，所有需要R资源的操作必须在此闭锁上等待
 */
public class CountDownLatchTestDriver {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatchTestDriver driver = new CountDownLatchTestDriver();
		
		Runnable task = new Runnable() {
			public void run() {
				int mills = new Random().nextInt(1000);
				System.out.println(Thread.currentThread().getName()+" awake, sleep " + mills);
				try {
					Thread.sleep(mills);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		int nThreads = 100;
		
		long times = driver.timeTasks(nThreads, task);
		System.out.println("takes " + times);
	}

	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1); // 初始值为1
		final CountDownLatch endGate = new CountDownLatch(nThreads); // 计数值为线程总数
		
		for(int i=0; i< nThreads; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						startGate.await();// 一直等待，直到latch减为0
						try {
							task.run();
						} finally {
							endGate.countDown(); // 任务完成后，将结束门的latch减1
						}
					} catch (InterruptedException e) {} 
				}
			});
			
			t.start(); // 启动线程
		}
		
		long start = System.currentTimeMillis();
		
		startGate.countDown(); // 打开启动门（初始值为1，执行1次countDown就可以将启动门打开）
		
		endGate.await(); // 在结束门处等待，直到所有线程都执行完成（每个线程执行完成后，都会调用1次countDown）
		
		long end = System.currentTimeMillis();
		return end - start;
	}
	
}
