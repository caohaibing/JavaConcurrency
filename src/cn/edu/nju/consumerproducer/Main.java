package cn.edu.nju.consumerproducer;

import java.util.concurrent.TimeUnit;

/**
 * 类名称：Main 类描述：
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午8:32:54
 * 
 */
public class Main {
	private static volatile int indexResource = 0;

	/**
	 * 方法作用：
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		BoundedBuffer buf = new BoundedBuffer();

		for (int i = 0; i < 20; i++) {
			Producer producer = new Producer(buf, indexResource++);
			Thread t1 = new Thread(producer);
			TimeUnit.MICROSECONDS.sleep(3000);
			t1.start();
			
		}
		for (int i = 0; i < 5; i++) {
			Consumer consumer = new Consumer(buf);
			Thread t2 = new Thread(consumer);
			t2.start();
			TimeUnit.MICROSECONDS.sleep(3000);
		}
	}

}
