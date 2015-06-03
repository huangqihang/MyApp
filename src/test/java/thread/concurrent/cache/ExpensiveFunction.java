package thread.concurrent.cache;

import java.math.BigInteger;


public class ExpensiveFunction implements Computable<String, BigInteger>{

	public BigInteger compute(String arg) throws InterruptedException {
		// long time to compute....
		return new BigInteger(arg);
	}

}
