package util.apache.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class MyBeanUtils {
	public static void main(String[] args) throws Exception {
		test1();
	}

	private static void test1() throws Exception {
		Person bean = new Person(1, "张三", 20, new Date());
		// 对象转MAP
		Map map = BeanUtils.describe(bean);
		System.out.println(map);
		
		
		// 注册日期转换器-解决字符串与Date的互转问题
		ConvertUtils.register(new MyDateConvert(), Date.class);
		
		// MAP转对象
		Person person = new Person();
		BeanUtils.populate(person, map);
		System.out.println(person);
		
		// 拷贝属性
		Person dest = new Person();
		BeanUtils.copyProperties(dest, person);
		System.out.println(dest);
	}
	
}

/**
 * 自动转换日期
 *
 */
class MyDateConvert implements Converter {
	// new Date().toString() 默认按CST格式输出字符串
	private static final String DATE_FROMAT_CST = "EEE MMM dd HH:mm:ss zzz yyyy";
	
	public Object convert(Class type, Object value) {
		Object result = null;
		if(type == Date.class) {
			if(value instanceof Date) {
				result = value;
			}
			if(value instanceof String) {
				try {
					result = new SimpleDateFormat(DATE_FROMAT_CST, Locale.US).parse(value.toString().trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

