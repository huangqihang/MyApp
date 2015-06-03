package thread.executor.demo.renderpage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import thread.helper.ThreadHelper;

public class PageRender {
	
	// 线程池
	private final ExecutorService executor;

	public PageRender(ExecutorService executor) {
		this.executor = executor;
	}
	
	public void renderPage(CharSequence source) {
		// 使用线程池构造ExecutorCompletionService
		CompletionService<ImageData> completionService = 
				new ExecutorCompletionService<ImageData>(executor);

		List<ImageInfo> infos =  scanForImageInfo(source);
		for(final ImageInfo imageInfo : infos) {
			completionService.submit(new Callable<ImageData>() {
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		}
		
		renderText(source);
		
		try {
			for(int i=0; i<infos.size(); i++) {
				Future<ImageData> f = completionService.take(); // 获取已经完成的任务
				ImageData imageData = f.get();
				renderImageData(imageData);
			}
		} catch (InterruptedException e) {
			ThreadHelper.interrupt();
		} catch (ExecutionException e) {
			ThreadHelper.launderThrowable(e);
		}
	}

	private void renderImageData(ImageData imageData) {
		System.out.println(imageData);
	}

	private void renderText(CharSequence source) {
		System.out.println(source);
	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		List<ImageInfo> list = new ArrayList<ImageInfo>();
		list.add(new ImageInfo());
		list.add(new ImageInfo());
		list.add(new ImageInfo());
		return list;
	}
}
