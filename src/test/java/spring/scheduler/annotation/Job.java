package spring.scheduler.annotation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {
	
	@Autowired
	BusinessService service;
	
	@Scheduled(fixedRate = 1000) // 以固定频率每隔1000ms执行1次
	public void execute() {
		String message = Thread.currentThread().getName()+"===>"+UUID.randomUUID();
		int i = 1/0; //发生异常，调度不会终止。//如果使用java提供的原生scheduled，遇到异常就不会再调度了。
		service.printMessage(message);
	}
	
}
