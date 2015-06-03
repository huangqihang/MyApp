package thread.basic;

/**
 * 线程被中断并不是线程被终止！！！
 * 被中断的线程，是否继续运行，取决于实际业务需求。
 * 
 * 一般，线程被中断后，可在catch块中重新中断此线程，让调用者处理
 * 或者，设置线程运行的标记为false，通常此标记为循环条件
 */
public class InterruptTestDriver {
	public static void main(String[] args) throws InterruptedException {
		MyRunner runner = new MyRunner();
		Thread t = new Thread(runner);
		t.start();
		
		while(true) {
			Thread.currentThread().sleep(1800);
			t.interrupt();
		}
	}
	
}
class MyRunner implements Runnable {
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread().getName()+" interrupted!");
				//Thread.currentThread().interrupt();
			}
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}
