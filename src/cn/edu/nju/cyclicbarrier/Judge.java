package cn.edu.nju.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**     
 * 类名称：Judge    
 * 类描述：    裁判类
 * @author：Administrator    
 * @version：2015年12月7日 下午3:43:57    
 *     
 */
public class Judge {
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

	/**    
	 * 方法作用：  开始比赛
	 * @return      
	*/
	public void startCompetition() {

		for (int i = 0; i < 10; i++) {
			new Thread(new Runner(cyclicBarrier, i)).start(); //开启十个运动员线程
		}
	}

}
