package thread.executor.demo.travel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 从不同旅游公司获取报价
 *
 */
public class WebSite {

	private static final int NTHREADS = 10;
	private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

	public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo,
			Set<TravelCompany> companies, Comparator<TravelQuote> ranking,
			long timeout, TimeUnit unit) throws InterruptedException {

		List<QuoteTask> tasks = new ArrayList<QuoteTask>();

		for (TravelCompany company : companies) {
			tasks.add(new QuoteTask(company, travelInfo));
		}

		List<Future<TravelQuote>> futures = exec.invokeAll(tasks, timeout, unit); // 超时

		List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());

		for (Future<TravelQuote> f : futures) {
			try {
				if(!f.isCancelled())
					quotes.add(f.get());
			} catch (ExecutionException e) {
				//quotes.add(getFailtureQuote(e.getCause()));
			} catch (CancellationException e) {
				//quotes.add(getTimeoutQutoe(e));
			}
		}

		Collections.sort(quotes, ranking);

		return quotes;
	}

	private TravelQuote getTimeoutQutoe(CancellationException e) {
		TravelQuote quote = new TravelQuote();
		quote.setDesc("CancellationException:"+e.getMessage());
		return quote;
	}

	private TravelQuote getFailtureQuote(Throwable cause) {
		TravelQuote quote = new TravelQuote();
		quote.setDesc("FailtureQuote:"+cause.getMessage());
		return quote;
	}

	public void stop() {
		exec.shutdown();
	}

}
