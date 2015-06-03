package thread.executor.demo.renderpage;

import java.util.concurrent.TimeUnit;

import thread.helper.ThreadHelper;

class ImageInfo {
	
	public ImageData downloadImage() {
		
		ThreadHelper.randomSleep(TimeUnit.SECONDS, 5);
		
		return new ImageData();
	}
	
}