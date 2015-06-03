package thread.executor.demo.renderpage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorCompetitionServiceTestDriver {
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		PageRender render = new PageRender(executor);
		
		CharSequence source = new String("A CharSequence");
		
		render.renderPage(source);
		
		executor.shutdownNow();
	}
}
