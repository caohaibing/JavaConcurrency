package cn.edu.nju.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**     
 * 类名称：Driver    
 * 类描述：    司机类
 * @author：Administrator    
 * @version：2015年12月7日 上午9:13:03    
 *     
 */
public class Driver {

	private CountDownLatch startCountDown = new CountDownLatch(1); //主线程同步
	private CountDownLatch doneCountDown = new CountDownLatch(10);//子线程之间同步

	/**    
	 * 方法作用：  
	 * @return      
	 * @throws InterruptedException 
	*/
	public void serveWorker() throws InterruptedException {

		System.out.println("司机开始服务乘客");
		for (int i = 0; i < 10; i++) {
			new Thread(new Worker(doneCountDown, i)).start();
			TimeUnit.SECONDS.sleep(1);
		}

		startCountDown.countDown();//让其他线程继续
		doneCountDown.await(); //等待全部线程结束
		System.out.println("全部乘客已经上车（下车）完毕！！！");

	}

}
