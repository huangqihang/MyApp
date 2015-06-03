package thread.basic;

import java.util.concurrent.Callable;

public class CallableTestDriver {
	public static void main(String[] args) {
		new Callable<Void>() {
			public Void call() throws Exception {
				System.out.println("没有返回值的Callable");
				return null;
			}
		};
	}
}
