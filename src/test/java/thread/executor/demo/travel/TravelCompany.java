package thread.executor.demo.travel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import thread.helper.ThreadHelper;

public class TravelCompany {
	private String name;
	private final Map<String, Map<String, Object>> travelMap = new HashMap<String, Map<String, Object>>();

	public TravelCompany(String companyName, String... cities) {
		this.name = companyName;
		init(cities);
	}

	private void init(String... cities) {
		for(String city : cities) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("desc", city+"1日游");
			map.put("price", new Random().nextInt(10000000));
			travelMap.put(city, map);
		}
	}

	public TravelQuote solicitQuote(TravelInfo travelInfo) {
		TravelQuote quote = new TravelQuote();
		quote.setCompanyName(this.name);
		Map<String, Object> map = travelMap.get(travelInfo.getTarget());
		if(map != null) {
			quote.setDesc((String) map.get("desc"));
			quote.setPrice(Double.valueOf(map.get("price").toString()));
		} else {
			quote.setDesc("无数据");
		}

		ThreadHelper.sleep(TimeUnit.MILLISECONDS,
				new Random().nextInt(5000) + 1000);

		return quote;
	}

}
