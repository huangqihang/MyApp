package util.io;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * 观察者模式
 *
 */
public class FileAlterationDemo {
	public static void main(String[] args) throws Exception {
		File file = new File("src"); // 被监控的目录及其子目录
		System.out.println(file.getAbsolutePath());
		
		FileAlterationObserver observer = new FileAlterationObserver(file); // 观察者
		
		observer.addListener(new MyFileListner()); // 观察者接收到通知后转发给监听器
		
		long interval = TimeUnit.SECONDS.toMillis(3);
		FileAlterationMonitor monitor = new FileAlterationMonitor(interval); // 监视文件变化 
		monitor.addObserver(observer);
		 
		monitor.start();
		
	}
	
	
}

/**
 * 监听器
 */
class MyFileListner implements FileAlterationListener {

	public void onStart(FileAlterationObserver observer) {
		System.out.println("start");
	}

	public void onDirectoryCreate(File directory) {
	}

	public void onDirectoryChange(File directory) {
	}

	public void onDirectoryDelete(File directory) {
	}

	public void onFileCreate(File file) {
		System.out.println("create a new file: " + file.getName());
	}

	public void onFileChange(File file) {
		System.out.println("file changed: " + file.getName());
	}

	public void onFileDelete(File file) {
		
	}

	public void onStop(FileAlterationObserver observer) {
		
	}
	
}
