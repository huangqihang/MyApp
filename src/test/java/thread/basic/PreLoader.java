package thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import thread.helper.ThreadHelper;


/**
 * 使用FutureTask提前加载稍后需要的资源
 *
 */
public class PreLoader {
	private final FutureTask<ProductInfo> future =  
			new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
				public ProductInfo call() throws Exception {
					return laodProductInfo();
				}
			});
	
	
	private final Thread thread = new Thread(future);
	
	public void start() {
		thread.start();
	}

	private ProductInfo laodProductInfo() throws DataLoadException {
		ProductInfo prdo = null;
		try {
			prdo = new ProductInfo();
		} catch (Exception e) {
			throw new DataLoadException();
		}
		return prdo;
	}
	
	public ProductInfo get() throws InterruptedException, DataLoadException {
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if(cause instanceof DataLoadException) {
				throw (DataLoadException)cause;
			} else {
				throw ThreadHelper.launderThrowable(cause);
			}
		}
	}
	
}

class ProductInfo {
	
}


class DataLoadException extends Exception {
	
}