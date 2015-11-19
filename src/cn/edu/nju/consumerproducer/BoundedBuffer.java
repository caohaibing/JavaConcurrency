package cn.edu.nju.consumerproducer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类名称：BoundedBuffer 类描述：
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午8:32:37
 * 
 */
// public class BoundedBuffer {
// private final Lock lock = new ReentrantLock();
// private Condition isFull = lock.newCondition();
// private Condition isEmpty = lock.newCondition();
// private Object buf[];
// private int putPtr;
// private int takePtr;
// private int count;
//
// public BoundedBuffer(int size) {
// this.buf = new Object[size];
// }
//
// public void put(Object object) {
// lock.lock();
// try {
// while (count == putPtr) {
// CommonUtils.print("缓冲区已经满了！");
// isFull.await();
// }
// buf[putPtr++] = object;
// if (putPtr == buf.length) {
// putPtr = 0;
// }
// count++;
// CommonUtils.print("目前缓冲区可用资源为:" + count);
// isFull.signal();
// } catch (Exception e) {
// e.printStackTrace();
// } finally {
// lock.unlock();
// }
// }
//
// public Object take() {
// lock.lock();
// try {
// while (count == 0) {
// isEmpty.await();
// }
// Object x = buf[takePtr++];
// if (takePtr == buf.length) {
// CommonUtils.print("缓冲区是空的！");
// takePtr = 0;
// }
// count--;
// CommonUtils.print("目前缓冲区可用资源为:" + count);
// isEmpty.signal();
// return x;
//
// } catch (Exception e) {
// e.printStackTrace();
// return null;
// } finally {
// lock.unlock();
// }
// }
//
// }

class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[5];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				notFull.await();
				CommonUtils.print("缓冲区已经满了.....");
			}
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0){
				CommonUtils.print("缓冲区已经空了.....");
				notEmpty.await();
			}
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}
