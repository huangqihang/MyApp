package logback;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thread.helper.ThreadHelper;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


public class MyApp1 {

	final static Logger logger = LoggerFactory.getLogger(MyApp1.class);
	
	public static void main(String[] args) {
		for(;;) {
			logger.info("Entering application.");
			
			Foo foo = new Foo();
			foo.doIt();
			logger.info("Exiting application.");
			
			ThreadHelper.sleep(TimeUnit.SECONDS, 1);
		}
		
	}
}

class Foo {
	final static Logger logger = LoggerFactory.getLogger(Foo.class);
	public void doIt() {
		logger.debug("Did it again!");
	}}
