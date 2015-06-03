package spring.scheduler.annotation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyExecutor {
	
	 private static ExecutorService executors = Executors.newFixedThreadPool(10);
	 
	 public static void execute(Runnable r) {
		 executors.execute(r);
	 }
	 
	 public static <T> Future<T> submit(Callable<T> task) {
		return  executors.submit(task);
	 }
	 
	 @Bean
	 @Qualifier("otherExecutor")
	 public ExecutorService newExecutor() {
		 return executors;
	 }
	 
}
