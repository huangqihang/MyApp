package spring.scheduler.xml;

import org.springframework.stereotype.Component;

@Component
public class BeanC {
	public void methodC() {
		System.out.println(Thread.currentThread().getName()+"\tBeanC.methodC()");
	}
}
