package thread.concurrent.cache.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import thread.concurrent.cache.Computable;
import thread.concurrent.cache.Memoizerl;

public class Factorizer extends GenericServlet {

	private static final long serialVersionUID = -4871721532235938561L;

	private final Computable<BigInteger, BigInteger> c = 
			new Computable<BigInteger, BigInteger>() {
		public BigInteger compute(BigInteger arg) throws InterruptedException {
			return factor(arg);
		}
	};
	
	private final Computable<BigInteger, BigInteger> cache = 
			new Memoizerl<BigInteger, BigInteger>(c);

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		try {
			encodeIntoResponse(res, cache.compute(i));
		} catch (InterruptedException e) {
			encodeError(res, "factorization interrupted");
		}
	}

	private BigInteger extractFromRequest(ServletRequest req) {
		return  new BigInteger("7");
	}

	protected BigInteger factor(BigInteger arg) {
		// long time to factor
		return new BigInteger("123456");
	}
	
	private void encodeError(ServletResponse res, String string) {
		try {
			PrintWriter pw = res.getWriter();
			pw.write(string);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void encodeIntoResponse(ServletResponse res, BigInteger compute) {
		try {
			PrintWriter pw = res.getWriter();
			pw.write(compute+"");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
