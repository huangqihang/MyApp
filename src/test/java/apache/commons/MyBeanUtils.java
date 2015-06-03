package apache.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanPropertyValueChangeClosure;
import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

import app.dao.DataProvider;
import app.model.Address;
import app.model.Person;

public class MyBeanUtils {

	public static void main(String[] args) throws Exception {
		// test1();
		// test2();
		test3();
	}

	/**
	 * BeanComparator 排序
	 * ComparableComparator 排序规则
	 * ReverseComparator 反转排序规则/倒序
	 */
	private static void test3() {
		
		List<Person> peopleCollection = DataProvider.getPersonList();
		
		String sortProperty = "age";
		
		BeanComparator<Person> naturalOrderBeanComparator = new BeanComparator<Person>(
				sortProperty, ComparableComparator.getInstance());
		Collections.sort(peopleCollection, naturalOrderBeanComparator);
		System.out.println(peopleCollection);
		
		BeanComparator<Person> reversedNaturalOrderBeanComparator = new BeanComparator<Person>(
				sortProperty, new ReverseComparator(ComparableComparator.getInstance()));
		Collections.sort(peopleCollection, reversedNaturalOrderBeanComparator);
		System.out.println(peopleCollection);
	}

	/**
	 * BeanPropertyValueChangeClosure 为一组对象中的某个指定属性设置相同的值
	 * BeanPropertyValueEqualsPredicate 从一组对象中删除属性值与给定值不匹配的对象，实现过滤操作
	 * BeanToPropertyValueTransformer 收集一组对象中某个属性的所有值，用新的集合返回
	 */
	private static void test2() {
		List<Person> peopleCollection = DataProvider.getPersonList();

		System.out.println(peopleCollection);

		// 统一设置list中每个person的age=10
		BeanPropertyValueChangeClosure closure = new BeanPropertyValueChangeClosure(
				"age", 10);
		CollectionUtils.forAllDo(peopleCollection, closure);
		System.out.println(peopleCollection);

		// 忽略Null的情况(Person的address属性可能为Null)
		boolean ignoreNull = true;
		// 统一设置list中每个bean的age
		BeanPropertyValueChangeClosure closure2 = new BeanPropertyValueChangeClosure(
				"address.city", "黄坪", ignoreNull);
		CollectionUtils.forAllDo(peopleCollection, closure2);
		System.out.println(peopleCollection);

		// 从集合中过滤掉属性值不匹配的对象
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate(
				"address.city", "黄坪", ignoreNull);

		// filter the Collection
		CollectionUtils.filter(peopleCollection, predicate);
		System.out.println(peopleCollection);

		// create the transformer
		BeanToPropertyValueTransformer transformer = new BeanToPropertyValueTransformer(
				"id", ignoreNull);

		// transform the Collection
		Collection peoplesCities = CollectionUtils.collect(peopleCollection,
				transformer);
		System.out.println(peoplesCities);

	}

	/**
	 * BeanUtils.describe 将Bean转为Map类型 ConvertUtils.register 注册自定义类型转换器
	 * PropertyUtils.setSimpleProperty
	 * 动态地为某个对象的属性赋值（属性名，属性值由变量分别传入，然后调用此方法为对象设置值） BeanUtils.populate
	 * 将Map转入对象Bean BeanUtils.copyProperties 拷贝对象属性
	 */
	private static void test1() throws Exception {
		Person bean = DataProvider.getPersonList().get(0);
		// 对象转MAP
		Map map = BeanUtils.describe(bean);
		System.out.println(map);

		// 注册日期转换器-解决字符串与Date的互转问题
		ConvertUtils.register(new MyDateConvert(), Date.class);
		ConvertUtils.register(new MyAddressConvert(), Address.class);

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
		if (type == Date.class) {
			if (value instanceof Date) {
				result = value;
			}
			if (value instanceof String) {
				try {
					result = new SimpleDateFormat(DATE_FROMAT_CST, Locale.US)
							.parse(value.toString().trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

/**
 * 自定义类型转换器
 */
class MyAddressConvert implements Converter {
	public Object convert(Class type, Object value) {
		Object result = null;
		if (type == Address.class) {
			if (value instanceof Address) {
				result = value;
			}
			if (value instanceof String) {
				Address address = new Address();
				// Address [province=四川, city=洪雅]
				// 找出字符串中的名值对
				Pattern p = Pattern.compile("(\\w+)=([\\w\u4e00-\u9fa5]*)");
				Matcher m = p.matcher((String) value);
				while (m.find()) {
					try { // m.group(1) : name , m.group(2): value
						PropertyUtils.setSimpleProperty(address, m.group(1),
								m.group(2));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				result = address;
			}
		}
		return result;
	}
}
