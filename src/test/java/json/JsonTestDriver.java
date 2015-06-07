package json;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import app.dao.DataProvider;
import app.models.Person;

import com.alibaba.fastjson.JSON;

public class JsonTestDriver {
	
	/**
	 * 复合对象的转换
	 * JSON.toJSONString 	统一将对象转为JSON字符串
	 * JSON.parseArray		将数组格式的JSON字符串转List
	 * JSON.parseObject		将JSON字符串转普通对象
	 */
	@Test
	public void testListObject() {
		List<Person> persons = DataProvider.getPersonList();
		
		// 转string 存redis
		String jsonArray = JSON.toJSONString(persons, true);
		System.out.println(jsonArray);
		
		// 还原对象
		List<Person> listp = JSON.parseArray(jsonArray, Person.class);
		System.out.println(listp);
		
		Person person = persons.get(0);
		
		// 转string 存redis
		String json = JSON.toJSONString(person, true);
		System.out.println(json);
		
		// 还原对象
		Person p = JSON.parseObject(json, Person.class);
		System.out.println(p);
		
		
	}
	
	/**
	 * 单一Object对象的处理
	 */
	@Test
	public void testListMap() {
		List<Map<String,Object>> list = DataProvider.getListMap();
		
		// 对象转JSON字符串存redis
		String jsonArray = JSON.toJSONString(list, true);
		System.out.println(jsonArray);
		
		// JSON字符串转List
		List<Map> list2 = JSON.parseArray(jsonArray, Map.class);
		System.out.println(list2);
		
		// parseObject 必须确保JSON串不是数组格式的，否则会报syntax error,expect {, actual [
		//Map map = JSON.parseObject(jsonArray, Map.class);

		Map<String, Object> mapObj = list.get(0);
		String json = JSON.toJSONString(mapObj);
		System.out.println(json);
		
		Map map = JSON.parseObject(json, Map.class);
		System.out.println(map);
		
	}
	
	
}
