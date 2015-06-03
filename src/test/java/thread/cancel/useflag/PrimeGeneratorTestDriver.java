package thread.cancel.useflag;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGeneratorTestDriver {
	public static void main(String[] args) throws InterruptedException {
		List<BigInteger> primes = aSecondOfPrimes();
		System.out.println(Arrays.toString(primes.toArray()));
	}

	private static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try{
			TimeUnit.SECONDS.sleep(1);
		} finally {
			generator.cancel();
		}
		return generator.get();
	}
}
