package app.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.common.AppBase;

public class AppConfig extends AppBase {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	private static final Collection<Configuration> configs = new ConcurrentLinkedQueue<Configuration>();
	
	static {
		initConfigs();
	}

	private static void initConfigs() {
		try {
			// 加载系统属性
			configs.add(new SystemConfiguration());
			
			// 加载主配置
			configs.add(newConfiguration("app.properties", 3000));
			
			// 加载辅助配置
			configs.add(newConfiguration("mail.properties", 3000));
			configs.add(newConfiguration("other.properties", 3000));
			configs.add(newConfiguration("jdbc.properties", 3000));
			
		} catch (Exception e) {
			logger.error("加载配置异常", e);
		}
	}
	
	/**
	 * 
	 * @param fileName 配置文件名称
	 * @param scanPeriod 扫描周期-单位毫秒
	 */
	private static PropertiesConfiguration newConfiguration(String fileName, long scanPeriod)
			throws ConfigurationException {
		PropertiesConfiguration appConfig = new PropertiesConfiguration();
		appConfig.setEncoding(APP_ENCODING);
		appConfig.load(fileName);
		appConfig.setReloadingStrategy(newReloadingStrategy(scanPeriod));
		return appConfig;
	}

	/**
	 * 监听文件变化，自动重新加载
	 * @param millis 扫描间隔，单位毫秒
	 * @return
	 */
	private static FileChangedReloadingStrategy newReloadingStrategy(long millis) {
		FileChangedReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
		reloadingStrategy.setRefreshDelay(millis); // 每隔多少毫秒扫描1次
		return reloadingStrategy;
	}
	
	/**
	 * 统一返回List
	 */
	@SuppressWarnings("rawtypes")
	public static List getList(String key) {
		for(Configuration config : configs) {
			if(config.containsKey(key))
				return config.getList(key);
		}
		return Collections.EMPTY_LIST;
	}
	
	public static String getString(String key) {
		for(Configuration config : configs) {
			if(config.containsKey(key))
				return config.getString(key);
		}
		return null;
	}
	
	public static String getString(String prefix, String key) {
		String complexKey = AppConfig.getString(prefix)+"."+key;
		for(Configuration config : configs) {
			if(config.containsKey(complexKey))
				return config.getString(complexKey);
		}
		return null;
	}
	
	public static Integer getInteger(String key) {
		for(Configuration config : configs) {
			if(config.containsKey(key))
				return config.getInteger(key, null);
		}
		return null;
	}
	

	
	public static void main(String[] args) throws Exception {
		for(;;) {
			System.out.println(AppConfig.getList("application.title"));
			System.out.println(AppConfig.getList("telnumber"));
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
