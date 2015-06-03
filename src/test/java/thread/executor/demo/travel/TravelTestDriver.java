package thread.executor.demo.travel;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TravelTestDriver {
	public static void main(String[] args) throws InterruptedException {

		TravelInfo travelInfo = new TravelInfo("香港", new Date());
		
		Set<TravelCompany> companies = new HashSet<TravelCompany>();
		companies.add(new TravelCompany("艺龙", "香港", "澳门"));
		companies.add(new TravelCompany("携程", "香港", "澳门"));
		companies.add(new TravelCompany("去哪", "香港", "澳门"));
		companies.add(new TravelCompany("途牛", "四川", "澳门"));

		Comparator<TravelQuote> ranking = new Comparator<TravelQuote>() {
			public int compare(TravelQuote o1, TravelQuote o2) {
				return (int) (o1.getPrice() - o2.getPrice());
			}
		};
		
		long timeout = 3000;
		
		TimeUnit unit = TimeUnit.MILLISECONDS;
		
		WebSite site = new WebSite();
		List<TravelQuote> quotes = 
				site.getRankedTravelQuotes(travelInfo, companies, ranking, timeout, unit);
		
		for(TravelQuote quote : quotes) {
			System.out.println(quote);
		}
		
		site.stop();
	}
}
