package spring.scheduler.xml;

import org.springframework.stereotype.Component;

@Component
public class BeanB {
	public void methodB() {
		System.out.println(Thread.currentThread().getName()+"\tBeanB.methodB()-以固定频率执行，不管前一个任务是否已经完成");
	}
}
