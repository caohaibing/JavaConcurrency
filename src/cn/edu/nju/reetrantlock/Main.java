package cn.edu.nju.reetrantlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**     
 * 类名称：Main    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年11月23日 上午9:32:24    
 *     
 */
public class Main {

	/**    
	 * 方法作用：  
	 * @return      
	 * @throws InterruptedException 
	*/
	public static void main(String[] args) throws InterruptedException {
		Storage storage = new Storage(0);
		Random random = new Random();

		for (int i = 0; i < 5; i++) {
			Consumer producer = new Consumer(storage, random.nextInt(100)
					% (100 - 1) + 1);
			Thread t = new Thread(producer);
			t.start();
		}
		TimeUnit.MICROSECONDS.sleep(3000);
		for (int i = 0; i < 5; i++) {
			Producer producer = new Producer(storage, random.nextInt(100)
					% (100 - 1) + 1);
			Thread t = new Thread(producer);
			t.start();
		}

	}

}
