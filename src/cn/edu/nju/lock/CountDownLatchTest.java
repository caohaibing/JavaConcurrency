package cn.edu.nju.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**     
 * 类名称：LockTest    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年11月20日 下午5:26:09    
 *     
 */
public class CountDownLatchTest {

	public static void fatherToEat() {
		System.out.println("我乘地铁去饭店需要1小时");

	}

	public static void matherToEat() {
		System.out.println("我坐公交车去需要2小时");
	}

	public static void sonToEat() {
		System.out.println("我骑自行车去需要3小时");
	}

	public static void togetherEat() {
		System.out.println("一家人一起吃饭咯！！");
	}

	private static CountDownLatch countDownLatch = new CountDownLatch(30);
	private static ThreadPoolExecutor threadPool;

	public static void main(String[] args) throws InterruptedException {
		threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(30); //创建一个线程池

		for (int i = 0; i < 10; i++) {

			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					fatherToEat();
					countDownLatch.countDown();
				}
			});
			Thread.sleep(500);
		}
		for (int i = 0; i < 10; i++) {

			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					matherToEat();
					countDownLatch.countDown();
				}
			});
			Thread.sleep(500);
		}
		for (int i = 0; i < 10; i++) {

			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					sonToEat();
					countDownLatch.countDown();

				}
			});
			Thread.sleep(500);
		}

		countDownLatch.await();
		togetherEat();

	}

}
