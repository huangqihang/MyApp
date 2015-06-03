package thread.concurrent.syncutil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierTestDriver {
	
	private final CyclicBarrier barrier;
	private final Worker[] workers;
	
	public BarrierTestDriver() {
		int count = Runtime.getRuntime().availableProcessors();
		this.barrier = new CyclicBarrier(count, new Runnable() {
			public void run() { // 最后达到barrier的线程将执行此barrierAction
				System.out.println("barrierAction："+Thread.currentThread().getName()+"\t All threads arrived barrier");
			}
		});
		
		this.workers = new Worker[count];
		for(int i=0;i<count;i++) {
			workers[i] = new Worker();
		}
	}

	private class Worker implements Runnable {
		public void run() {
			try {
				int rand = new Random().nextInt(2000);
				System.out.println(Thread.currentThread().getName() + "\tsleep="+rand);
				Thread.sleep(rand);
				
				// 等待其他线程，当所有线程都达到栅栏后，才能继续执行
				int index = barrier.await(); // index = getParties() - 1. 最后到达的线程index=0
				
				System.out.println(Thread.currentThread().getName() + "已到达， index="+index);
			} catch (InterruptedException e) {
				return;
			} catch (BrokenBarrierException e) {
				return;
			}
		}
	}
	
	public void start() {
		for(int i=0; i<workers.length; i++) {
			new Thread(workers[i]).start();
		}
	}
	
	public static void main(String[] args) {
		new  BarrierTestDriver().start();
	}
}	
