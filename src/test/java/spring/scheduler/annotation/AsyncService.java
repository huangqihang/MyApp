package spring.scheduler.annotation;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
	
	@Async("otherExecutor")
	public Future<String> asyncMethod() {
		System.out.println(Thread.currentThread().getName());
		Callable<String> call = new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(10000);
				System.out.println(Thread.currentThread().getName()+"\t done");
				return "async ok";
			}
		};
		return MyExecutor.submit(call);
	}
}
