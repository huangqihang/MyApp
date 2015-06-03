package thread.cancel.useinterrupt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class PrimeProducerTestDriver {
	public static void main(String[] args) throws InterruptedException {
		List<BigInteger> primes = aSecondOfPrimes();
		System.out.println(Arrays.toString(primes.toArray()));
	}
	
	private static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
		BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<BigInteger>(10000);
		PrimeProducer producer = new PrimeProducer(queue);
		producer.start();
		try{
			TimeUnit.SECONDS.sleep(1);
		} finally {
			producer.cancel();
		}
		
		List<BigInteger> list = new ArrayList<BigInteger>();
		queue.drainTo(list);
		return list;
	}
}
