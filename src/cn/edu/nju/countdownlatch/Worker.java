package cn.edu.nju.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**     
 * 类名称：Worker    
 * 类描述：    乘客类
 * @author：Administrator    
 * @version：2015年12月7日 上午9:13:47    
 *     
 */
public class Worker implements Runnable {
	private CountDownLatch doneCountDown;
	private final int i;

	public Worker(CountDownLatch countDownLatch, int i) {
		this.doneCountDown = countDownLatch;
		this.i = i;

	}

	/**    
	 * 方法作用：  乘客上车或者下车
	 * @return      
	*/
	private void upOrDownCar() {
		System.out.println("乘客  " + i + "上(下)车");
	}

	@Override
	public void run() {
		doneCountDown.countDown();
		upOrDownCar();
	}

}
