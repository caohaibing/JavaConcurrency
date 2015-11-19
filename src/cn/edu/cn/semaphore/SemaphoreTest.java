package cn.edu.cn.semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：SemaphoreTest1 
 * 类描述：Java并发包  Semaphore类的学习
 * 
 * @author：Administrator
 * @version：2015年9月19日 下午9:57:43
 * 
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(20);//创建一个线程池
		final Semaphore semaphore = new Semaphore(5); //设置一个信号量，其值为5，代表共享区一次只能有5个线程同时访问

		for (int i = 0; i < 100; i++) {
			final int threadIndex = i; //给每个线程一个标志
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						semaphore.acquire();
						System.out.println("Access:" + threadIndex
								+ "线程正在运行.....");
						TimeUnit.MILLISECONDS.sleep(5000);
						semaphore.release();
						System.out.println("还有" + semaphore.availablePermits()
								+ "个资源可以用\n");

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			threadPoolExecutor.execute(runnable); //将线程放入线程池，让线程池去执行
		}
		threadPoolExecutor.shutdown();//结束后要关闭线程池
	}
}
