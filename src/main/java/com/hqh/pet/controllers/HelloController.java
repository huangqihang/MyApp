package com.hqh.pet.controllers;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import thread.helper.ThreadHelper;

@Controller
public class HelloController {
	
	/**
	 * 演示：
	 * 1.获取请求参数
	 * 2.向视图传递数据
	 * 3.获取HttpRequest对象，HttpSession对象
	 * 4.REST
	 */
	
	@PostConstruct
	public void log() {
		final Logger logger = LoggerFactory.getLogger(HelloController.class);
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				for(;;) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {}
					logger.info(DateTime.now().toString("yyyy-MM-dd"));
				}
			}
		}).start();
	}
	
	public static final String VIEW = "hello";
	
	@RequestMapping(value="sayHello") //http://localhost:8080/pet/sayHello?name=zs&age=20
	public ModelAndView sayHello(String name, Integer age) {
		System.out.println("name="+name);
		System.out.println("age="+age);
		System.out.println("由于方法参数没有使用@RequestParam，则请求URI中参数可有可无，URI中没有出现的参数将被置为null。"
				+ "因此，方法参数不能是原始类型，必须是其包装类类型");
		ModelAndView mav = new ModelAndView(VIEW); //使用MadelAndView对象，封装数据和视图名称
		mav.addObject("result", "sayHello-Hello : " + name);
		return mav;
	}
	
	@RequestMapping(value="sayHello2") //http://localhost:8080/pet/sayHello2?name=zs
	public String sayHello2(@RequestParam String name, Map<String, Object> map) {
		System.out.println("name="+name);
		System.out.println("方法参数使用了@RequestParam，则该参数必须出现在请求的URI中，否则404");
		map.put("result", "sayHello2-Hello : " + name); //使用方法参数Map，传递数据
		return VIEW;
	}
	
	@RequestMapping(value="sayHello3")	//http://localhost:8080/pet/sayHello3?pname=zs
	public String sayHello3(@RequestParam(value="pname") String name, Model model) {
		System.out.println("name="+name);
		System.out.println("方法参数使用了@RequestParam，且将URI参数转换为方法参数"
				+ "因此，请求URI中的参数名应该为pname"); 
		model.addAttribute("result", "sayHello3-Hello : " + name); //使用方法参数Model传递参数
		model.addAttribute("没有key的值，jsp中通过值类型获取：如，String字符串，则用${string}获取");
		return VIEW;
	}
	
	@RequestMapping(value="sayHello4") //http://localhost:8080/pet/sayHello4?username=zsan
	public String sayHello4(HttpServletRequest req, HttpSession session, Model model) {
		String username = req.getParameter("username");
		System.out.println("如果需要访问原始的HttpServletRequest或HttpSession，直接在方法参数上声明即可，spring会自动传入");
		model.addAttribute("result", "sayHello4-username=" + username);
		return VIEW;
	}
	
}
