package com.hqh.pet.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import app.models.User;

@Controller
@RequestMapping("/user")
@SessionAttributes(value="loginUser") //凡是以"loginUser"为key存入model中的数据，都将以此key存入session
public class HelloRESTController {
	
	/**
	 * REST-以资源为导向的URI格式
	 * REST服务可以返回：html,jsp,xml,json等格式的数据
	 * 
	 * 转变：
	 * 	http://localhost:8080/pet-web/user?id=100
	 *  ---> http://localhost:8080/pet-web/user/id/100
	 *  
	 * 真正的REST服务是无状态的，没有session！spring提供的REST风格URI是否有session呢？
	 */
	
	private static Map<String,User> users = new LinkedHashMap<String,User>();  
	
    static{  
        users.put("zs", new User("张三",20));  
        users.put("ls", new User("李四",21));  
        users.put("ww", new User("王五",22));  
        users.put("zl", new User("赵六",23));  
    }
	
	@RequestMapping(value="list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "user/list";
	}
	
	//获取添加页面
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(Model model) {
		//传递1个空的user对象到request中，为了解决表单中modelAttribute="user"为null的情况:
		//Neither BindingResult nor plain target object for bean name 'user' available as request attribute
		model.addAttribute(new User());
		return "user/add";
	}
	
	//添加
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(User user) {
		users.put(user.getName(), user);
		//redirect
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX+"/user/list";
	}
	
	/**
	 * REST
	 * 	适用于GET方式获取数据：http://localhost:8080/pet/user/张三
	 * RequestMapping中动态路径：{xxx}
	 * 方法中的请求参数通过@PathVariable进行标注
	 */
	
	//查询
	@RequestMapping(value="{name}", method=RequestMethod.GET)
	public String show(@PathVariable String name, Model model, HttpSession session) {
		checkSession(session);
		model.addAttribute(users.get(name));
		return "user/show";
	}
	
	private void checkSession(HttpSession session) {
		//验证session设置是否有效
		System.out.println(session.getAttribute("loginUser")); //获取已登录用户的信息
		Enumeration<String> enums = session.getAttributeNames();
		while(enums.hasMoreElements()) {
			String obj = enums.nextElement();
			System.out.println(session.getId()); //sessionId通过cookie存放到客户端
		}
	}

	//获取更新
	@RequestMapping(value="{name}/update", method=RequestMethod.GET)
	public String update(@PathVariable String name, Model model) {
		model.addAttribute(users.get(name));
		return "user/update";
	}
	
	//提交更新
	@RequestMapping(value="{name}/update", method=RequestMethod.POST)
	public String update(@PathVariable String name, User user) {
		users.put(name, user);
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX+"/user/list";
	}
	
	//删除
	@RequestMapping(value="{name}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable String name, HttpSession session) {
		System.out.println(session);
		users.remove(name);
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX+"/user/list";
	}
	
	//获取登录页面
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	//登录
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(String name, String password, Model model) {
		if(!users.containsKey(name)) {
			throw new UserException("用户名不存在");
		}
		if(!name.equals(password)) {
			throw new UserException("密码错误");
		}
		if(name.equals(password)) {
			//往model中存储的数据，只要key在session中声明了，就会将对象自动放入session中
			model.addAttribute("loginUser", users.get(name));
		}
		return "redirect:/user/list";
	}
	
	
	//统一处理本Controller中的异常：需指定接收哪些类型的异常
	@ExceptionHandler(value={UserException.class})
	public String handleException(Exception ex, HttpServletRequest request) {
		request.setAttribute("errorMsg", ex.getMessage());
		return "user/error";
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String text = "%E5%BC%A0%E4%B8%89%23%E5%BC%A0%E4%B8%89";
		String src = "张三#张三";
		String dest = URLEncoder.encode(src, "UTF-8");
		System.out.println(dest);
	}
}
