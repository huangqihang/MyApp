package thread.concurrent.syncutil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;


public class BoundedHashSetTestDriver {
	public static void main(String[] args) throws InterruptedException {
		BoundedHashSet<String> set = new BoundedHashSet<String>(3);
		set.add("1");
		set.add("2");
		set.add("3");
	}
	
}

/**
 * 利用信号量实现有界集合
 *
 */
class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore sem;
	
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.sem = new Semaphore(bound);
	}
	
	public boolean add(T o) throws InterruptedException {
		sem.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(o);
			return wasAdded;
		} finally {
			if(!wasAdded)
				sem.release(); // 添加失败，则释放本次获取的信号量
		}
	}
	
	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if(wasRemoved)
			sem.release(); // 删除成功，则释放1个信号量
		return wasRemoved;
	}
	
}
