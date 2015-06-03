package spring.scheduler.xml;

import org.springframework.stereotype.Component;

@Component
public class BeanA {
	public void methodA() {
		System.out.println(Thread.currentThread().getName()+"\tBeanA.methodA()-上次任务完成后延迟x毫秒后，再执行");
	}
}
