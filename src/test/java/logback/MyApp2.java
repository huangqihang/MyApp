package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class MyApp2 {

	final static Logger logger = LoggerFactory.getLogger(MyApp2.class);

	public static void main(String[] args) {
//		 LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		 StatusPrinter.print(lc);
		
		
		System.out.println("loggerName:\t" + logger.getName());
		logger.debug("Entering application.");
		logger.info("Exiting application.");
	}
}
