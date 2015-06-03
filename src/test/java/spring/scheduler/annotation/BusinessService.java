package spring.scheduler.annotation;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	
	public void printMessage(String message) {
		System.out.println(message);
	}
	
	
}
