package cn.edu.nju.handledate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		ExecutorService threadPool = Executors.newFixedThreadPool(5);  //创建线程池  ，开启五个线程，经验表明，线程数=电脑CPU核数+1效率最高
		for (int i = 0; i < 5; i++) {
			threadPool.execute(new DateReplacement(factory));   //把线程加入线程池
		}

		threadPool.shutdown();  //关闭线程
	}

}
