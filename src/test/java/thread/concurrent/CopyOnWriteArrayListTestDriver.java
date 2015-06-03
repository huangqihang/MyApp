package thread.concurrent;

// 遍历操作为主要操作的情况下替代同步的List
// 核心概念：写入时复制- 只要发生了写操作，就会重新拷贝一个副本容器
// 适用场景：迭代操作远远多于修改操作时，才应该使用“写入时复制”。因为复制副本开销大，所以不适用于写操作频繁的场景。
// 如： 事件通知系统：注册和注销事件监听器的操作远远少于接收事件通知的操作，此时就可以使用CopyOnWriteArrayList
public class CopyOnWriteArrayListTestDriver {

}
