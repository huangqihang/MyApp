package thread.executor.demo.travel;

import java.util.concurrent.Callable;

public class QuoteTask implements Callable<TravelQuote>{

	private final TravelCompany company;
	private final TravelInfo travelInfo;
	
	public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
		this.company = company;
		this.travelInfo = travelInfo;
	}

	public TravelQuote call() throws Exception {
		return company.solicitQuote(travelInfo);
	}
	
}
