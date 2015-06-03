package apache.commons;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import app.common.AppBase;

public class MyCollection extends AppBase {
	
	@Test
	public void test() {
		// ArrayUtils
		// ListUtils
		// SetUtils
		// CollectionUtils
		
		// MapUtils
		Map<String,Object> datas = new HashMap<String,Object>();
		datas.put("name", "张三");
		datas.put("age", "a");
		datas.put("birth", new Date());
		
		String name = MapUtils.getString(datas, "name", "未知");
		int age = MapUtils.getIntValue(datas, "age");
		Date birth = (Date) MapUtils.getObject(datas, "birth", null);
		
		info(name);
		info(age);
		info(birth);
	}
	
	@Test
	public void testMapIter() {
		IterableMap map = new HashedMap();
		
		map.put("1", "a");
		map.put("2", "b");
		
		info(map);
		
		MapIterator it = map.mapIterator();
		while (it.hasNext()) {
		  Object key = it.next();
		  Object value = it.getValue();
		  
		  it.setValue("newValue");
		}
		info(map);
	}
	
	@Test
	public void testSortMap() {
		OrderedMap map = new LinkedMap();
		map.put("FIVE", "5");
		map.put("SIX", "6");
		map.put("SEVEN", "7");
		info(map.firstKey());  // returns "FIVE"
		info(map.nextKey("FIVE"));  // returns "SIX"
		info(map.previousKey("SIX"));  // returns "FIVE"
		info(map.nextKey("SIX"));  // returns "SEVEN"
		
		info(map);
	}
	
	@Test
	public void testBidirectionMap() {
		BidiMap bidi = new TreeBidiMap();
		bidi.put("SIX", "6");
		bidi.put("Seven", "7");
		bidi.put("Seven2", "7");
		bidi.get("SIX");  // returns "6"
		bidi.getKey("6");  // returns "SIX"
		bidi.removeValue("6");  // removes the mapping
		info(bidi);
		
		BidiMap inverse = bidi.inverseBidiMap();  // returns a map with keys and values swapped
		info(inverse);
	}
	
	@Test
	public void testBags() {
		Bag bag = new HashBag();
		bag.add("ONE", 6);  // add 6 copies of "ONE"
		info(ArrayUtils.toString(bag.toArray(), ""));
		
		bag.remove("ONE", 2);  // removes 2 copies of "ONE"
		info(ArrayUtils.toString(bag.toArray(), ""));
		
		bag.getCount("ONE");  // returns 4, the number of copies in the bag (6 - 2)
	}
	
	
}
