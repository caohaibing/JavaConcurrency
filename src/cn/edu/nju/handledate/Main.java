package cn.edu.nju.handledate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**     
 * 类名称：Main    
 * 类描述：     执行类，开始处理文档
 * @author：Administrator    
 * @version：2015年11月30日 下午4:29:12    
 *     
 */
public class Main {

	/**    
	 * 方法作用：  主函数
	 * @return      
	*/

	public static void main(String[] args) {
		FileFactory factory = new FileFactory();
		ExecutorService threadPool = Executors.newFixedThreadPool(5); //创建线程池  ，开启五个线程，经验表明，线程数=电脑CPU核数+1效率最高
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			threadPool.execute(new DateReplacement(factory)); //把线程加入线程池
			System.out.println(Thread.currentThread().getName() + " 线程执行完毕！");
		}

		threadPool.shutdown(); //关闭线程
		// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。

		try {
			// 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行
			// 设置最长等待10秒
			threadPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("总共耗时: " + (System.currentTimeMillis() - startTime));
	}

}
