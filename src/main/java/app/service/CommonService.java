package app.service;

import org.springframework.stereotype.Service;

@Service
public class CommonService extends AppBaseService {
	
	public void doit() {
		logger.info(currentMethod());
	}
	
}
