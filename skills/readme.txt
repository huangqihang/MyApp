WEB-INF是Java的WEB应用的安全目录。客户端无法访问，只有服务端可以访问的目录。
如果想在页面中直接访问其中的文件，必须通过web.xml文件对要访问的文件进行相应映射才能访问。
对于静态文件：css,js文件，则应该直接放置到webapp目录下，让客户端可以直接访问。

【项目结构】
pet(webapp)
	public(静态资源文件目录)
	WEB-INF(安全目录)
		classes
		lib
		web.xml
		configs
			spring-config.xml hibernate-config.xml 等配置文件
		views
			jsp html 
			
======================================================




