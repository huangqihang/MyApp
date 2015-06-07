package app.web.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.service.CommonService;
import app.web.AppBaseController;

@Controller
@RequestMapping("${application.root}") //通过PropertyPlaceholderConfigurer，读取配置的路径
public class DynamicController extends AppBaseController {
	
	public DynamicController() {
		info("DynamicController...."+this);
	}

	CommonService commonService;
	
	
	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
		info("commonService"+this.commonService);
	}


	// GET localhost/app/execute
	@RequestMapping(value="/execute", method = RequestMethod.GET)
	public void execute() {
		info(currentMethod());
		commonService.doit();
	}
	
}
