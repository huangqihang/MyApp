package thread.concurrent.queue.demo;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	
	private static final int N_CONSUMERS = 3;
	private static BlockingQueue<File> queue = new LinkedBlockingQueue<File>(50);

	public static void main(String[] args) {
		File root = new File("f:/");
		startIndexing(root);
	}

	private static void startIndexing(File root) {
		
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return !file.getName().endsWith(".java");
			}
		};
		
		new FileCrawler(queue, filter, root).start();
		
		for(int i=0; i<N_CONSUMERS; i++) {
			new Indexer(queue).start();
		}
			
		System.out.println("Main down");
		System.out.println(Arrays.toString(queue.toArray()));
	}
}
