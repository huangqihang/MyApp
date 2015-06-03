package thread.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 替代同步的基于散列的Map
// ConcurrentHashMap 采用机制：分段锁
public class ConcurrentHashMapTestDriver {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("x", "1");
		map.put("x", "2");
		System.out.println(map);
		
		
		ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>(10);
		String val1= concurrentMap.putIfAbsent("x", "1"); // 若没有则添加
		String val2 = concurrentMap.putIfAbsent("x", "2"); // key已存在，不再添加，返回key对应的value

		System.out.println(val1);
		System.out.println(val2);
		System.out.println(concurrentMap);

		concurrentMap.replace("x", "3");
		System.out.println(concurrentMap);

		concurrentMap.remove("x", "3");
		System.out.println(concurrentMap);
		
	}
}
