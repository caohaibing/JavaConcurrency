package cn.edu.nju.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**     
 * 类名称：CallableTask    
 * 类描述：     callable + future
 *     
 */
public class CallableTask {

	/**    
	 * 方法作用：  
	 * @return      
	 * @throws InterruptedException 
	*/
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Task task = new Task();

		Future<Integer> result = executorService.submit(task); //提交任务，并且得到future

		executorService.shutdown();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("主线程在执行任务");

		try {
			System.out.println("计算结果为:" + result.get()); //获取计算结果
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
	}

}

/**     
 * 类名称：Task    
 * 类描述：    任务

 *     
 */
class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("子线程正在计算");
		int sum = 0;
		for (int i = 0; i < 1000; i++) { //作求和计算
			sum += i;
		}
		TimeUnit.SECONDS.sleep(3); //睡眠3秒
		return new Integer(sum);
	}

}
