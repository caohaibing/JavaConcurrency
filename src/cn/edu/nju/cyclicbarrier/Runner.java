package cn.edu.nju.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**     
 * 类名称：Runner    
 * 类描述：    运动员类
 * @author：Administrator    
 * @version：2015年12月7日 下午3:40:14    
 *     
 */
public class Runner implements Runnable {
	private CyclicBarrier barrier;   //同步辅助类
	private final int i;

	public Runner(CyclicBarrier cyclicBarrier, int i) {
		this.barrier = cyclicBarrier;
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("运动员 " + i + "已经准备好了！！！");
		try {
			barrier.await();   //运动员等待
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("运动员 " + i + "开始起泡！！");
	}

}
