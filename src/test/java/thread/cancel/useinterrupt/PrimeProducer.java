package thread.cancel.useinterrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;

	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		super();
		this.queue = queue;
	}
	
	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while(!Thread.interrupted()) {
			p = p.nextProbablePrime();
			try {
				queue.put(p);
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void cancel() {
		System.out.println("使用中断来取消任务");
		this.interrupt();
	}
}
