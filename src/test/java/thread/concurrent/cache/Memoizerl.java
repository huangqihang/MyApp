package thread.concurrent.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import thread.helper.ThreadHelper;

/**
 * 使用ConcurrentHashMap做简单的缓存功能
 * 	key： 计算参数
 *  value: Future计算任务
 *  
 *  未解决的问题：
 *  	缓存逾期问题：自定义FutureTask子类，在子类中设置逾期时间，并用1个线程定期扫描是否逾期
 *  	缓存清理问题：防止消耗过多内存
 */
public class Memoizerl<A, V> implements Computable<A, V> {
	
	private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	
	private final Computable<A, V> computImpl;
	
	public Memoizerl(Computable<A, V> c) {
		this.computImpl = c;
	}

	public V compute(final A arg) throws InterruptedException {
		while(true) {
			Future<V> f = cache.get(arg);
			if(f == null) {
				Callable<V> eval = new Callable<V>() {
					public V call() throws Exception {
						return computImpl.compute(arg);
					}
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg, ft); // 关键： 防止两个线程在同一时刻进行compute计算
				if(f == null) {
					f = ft;
					ft.run();
				}
			}
			
			try {
				return f.get(); //如果发生异常，则while循环继续尝试计算
			} catch (CancellationException e) {
				cache.remove(arg, f); // 关键： 任务被取消，则应该从缓存中清除掉
			} catch (ExecutionException e) {
				throw ThreadHelper.launderThrowable(e.getCause());
			}
		}
	}

}
