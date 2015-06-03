package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 将客户端请求封装为Runnable任务
 * 将任务提交给线程池执行
 */
public class TaskExecutionWebServer {
	
	private static final int NTHREADS = 100;
	private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);
	
	public void start() throws IOException {
		ServerSocket server = new ServerSocket(80);
		while(true) {
			final Socket connection = server.accept();
			Runnable task = new Runnable() {
				public void run() {
					handleRequest(connection);
				}
			};
			exec.execute(task);
		}
	}
	
	protected void handleRequest(Socket connection) {
		try {
			InputStream is = connection.getInputStream();
			System.out.println("client in :" + is.toString());
			
			OutputStream os = connection.getOutputStream();
			String respText = DateFormat.getInstance().format(new Date());
			os.write(respText.getBytes());
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new TaskExecutionWebServer().start();
	}
}	
