package cn.edu.nju.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类名称：BoundedBuffer 
 * 类描述：缓冲区，通过Java并发包 Condition来控制
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午8:32:37
 * 
 */

class BoundedBuffer {
	final Lock lock = new ReentrantLock(); //新建一个显示锁
	final Condition notFull = lock.newCondition(); //判断缓冲区不满的condition
	final Condition notEmpty = lock.newCondition(); //判断缓冲区不空的condition

	final Object[] items = new Object[5]; //设置一个缓冲区的大小为5
	int putptr, takeptr, count; //放、取的指针

	/**    
	 * 方法作用：  往缓冲区放入资源
	 * @return      
	*/
	public void put(Object x) throws InterruptedException {
		lock.lock(); //锁住临界区
		try {
			while (count == items.length) { //先判断是否缓冲区满了
				notFull.await(); //缓冲区满了，就设置后来的线程等待
				CommonUtils.print("缓冲区已经满了.....");
			}
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal(); //放入资源后就通知等待取的线程
		} finally {
			lock.unlock(); //释放
		}
	}

	/**    
	 * 方法作用：  从临界区取资源的方法
	 * @return      
	*/
	public Object take() throws InterruptedException {
		lock.lock(); //锁住临界区
		try {
			while (count == 0) {
				CommonUtils.print("缓冲区已经空了.....");
				notEmpty.await();
			}
			Object x = items[takeptr]; //取走资源
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal(); //发出信号，通知其他线程可以进入
			return x;
		} finally {
			lock.unlock(); //释放
		}
	}
}
