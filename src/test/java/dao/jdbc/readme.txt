JDBC操作：
	如果要保持事务的一致性，必须使用同一个Connection进行数据操作
	统一commit()或者rollback()---这也是标准的做法！
	如果业务逻辑分布在不同的方法中，则可将Connection使用ThreadLocal实现共享！
	或者，显示的在方法参数中进行传递。
	
	Connection conn = ...;
	try {
		conn.execute(...);
		// ...
		conn.execute(...);
		// ...
		conn.execute(...);
	} catch(Exception e) {
		conn.roolback()
	} finally {
		conn.close();
	}
	
===============================================
【不同阶段分别执行commit】
Connection conn = ...;
pstmt.execute("insert into xxx ...");
conn.commit();
throws new Exception(); // 后面发生异常不会对之前已commit的操作造成影响
conn.commit();

===============================================
【不同阶段分别执行rollback】
Connection conn = ...;
throws new Exception(); 
conn.rollback();
pstmt.execute("insert into xxx ..."); //前面rollback后，不会影响后面的commit()
conn.commit();
