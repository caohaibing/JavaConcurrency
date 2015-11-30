package cn.edu.nju.reetrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**     
 * 类名称：Storage    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年11月23日 上午9:02:40    
 *     
 */
public class Storage {

	private Lock lock = new ReentrantLock();
	private int size = 0;
	private final int length = 10;

	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public Storage(int size) {
		this.size = size;
	}

	/**    
	 * 方法作用：  将物品放入缓冲区
	 * @return      
	*/
	public void put(int x) {
		try {
			lock.lock();
			while (x >= length) {
				notFull.await();
				System.out.println("缓冲区已经满了！！请稍等！！！");
			}
			size += x;
			System.out.println(Thread.currentThread().getName() + "  放入了" + x
					+ " ;现在总共有:" + size);
			notFull.signal();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int take(int x) {
		try {
			lock.lock();
			while (x <= 0) {
				notEmpty.await();
				System.out.println("缓冲区已经空了，请稍等！！！");
			}
			size -= x;
			System.out.println(Thread.currentThread().getName() + "  拿走" + x
					+ "  ;现在剩余有" + size);
			notEmpty.signal();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();

		}
		return -1;
	}

}
