package spring.scheduler.annotation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDriver {

	@Test
	public void testSchedule() throws InterruptedException, ExecutionException {
		Class appConfig = AppConfig.class;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(appConfig);
		AsyncService service = context.getBean(AsyncService.class);
		Future<?> future = service.asyncMethod();
		
		// 5s后主线程结束，定时任务线程也会结束-后台线程
		Thread.currentThread().sleep(5000);
		String futureRs = (String) future.get();
		System.out.println(futureRs);
	}
	
}
