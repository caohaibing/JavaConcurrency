package cn.edu.nju.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**     
 * 类名称：FutureTask    
 * 类描述：     futuretask的测试
  
 *     
 */
public class FutureTaskTest {

	/**    
	 * 方法作用：  main方法
	 * @return      
	*/
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5); //线程池
		CountNum c1 = new CountNum();

		FutureTask<Integer> futureTask = new FutureTask<Integer>(c1); //创建一个futuretask
		executorService.submit(futureTask); //将任务提交到线程池里

		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("获得结果为:" + futureTask.get()); //直接通过之前的对象来获取结果

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}

/**     
 * 类名称：CountNum    
 * 类描述：    计算和的任务，继承了callable接口
 *     
 */
class CountNum implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 1000; i++) {
			sum += i;
		}

		return new Integer(sum);
	}

}
