package thread.concurrent.queue;

// 取消了中间环节，没有中间的阻塞队列，生产者直接将任务交给消费者
// 好比直接将文件交给同事，而不是把文件放到邮箱中
// 当有足够多的消费者，并且总有1个消费者准备好获取交付的任务时，才适合使用同步队列
public class SynchronousQueueTestDriver {

}
