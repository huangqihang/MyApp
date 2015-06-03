package remote.jmx.standard2;

public interface HelloMBean {
	
	public void printHelloWorld();

	public String getName();

	public void setName(String name);
	
	// 另一个线程读取HelloMBean的属性，控制运行或停止
	public void stopRunner(); // 如果已开启，则停止
	
}