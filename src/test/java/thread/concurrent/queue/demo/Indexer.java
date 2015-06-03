package thread.concurrent.queue.demo;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
	private final BlockingQueue<File> queue;
	
	public Indexer(BlockingQueue<File> queue) {
		super();
		this.queue = queue;
	}
	
	public void start() {
		new Thread(this).start();
	}

	public void run() {
		try {
			while(true) {
				indexFile(queue.take());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}

	private void indexFile(File file) {
		System.out.println("Index file: "+ file.getAbsolutePath());
	}

}
