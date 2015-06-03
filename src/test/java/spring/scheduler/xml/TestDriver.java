package spring.scheduler.xml;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDriver {
	
	@Test
	public void test() throws InterruptedException {
		
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/test/java/spring/scheduler/xml/beans.xml");
		
		Thread.sleep(10000);
		
	}
}
