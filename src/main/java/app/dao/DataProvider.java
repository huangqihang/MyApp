package app.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import app.model.Address;
import app.model.Person;

public class DataProvider {
	
	public static List<Person> getPersonList() {
		Person bean1 = new Person(1, "张三1", 21, new Date(), new Address("四川", "洪雅"));
		Person bean2 = new Person(2, "张三2", 22, new Date(), new Address("四川", "洪雅"));
		Person bean3 = new Person(3, "张三3", 23, new Date());
		
		List<Person> list = new ArrayList<Person>();
		list.add(bean1);
		list.add(bean2);
		list.add(bean3);
		return list;
	}
	
	/**
	 * @return Mabatis查询数据库的结果集
	 */
	public static List<Map<String, Object>> getListMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		
		Map<String, Object> row1 = new HashMap<String, Object>();
		row1.put("field1", 100);
		row1.put("field2", new BigDecimal("100000.80"));
		row1.put("field3", "123456abc");
		
		Map<String, Object> row2 = new HashMap<String, Object>();
		row2.put("field1", 200);
		row2.put("field2", new BigDecimal("200000.80"));
		row2.put("field3", "xxxxc");
		
		list.add(row1);
		//list.add(row2);
		
		return list;
	}
}
