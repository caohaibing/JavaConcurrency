package cn.edu.nju.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**     
 * 类名称：Finonicci    
 * 类描述：    通过forkjoin来实现   斐波那契数列 
 * @author：Administrator    
 * @version：2015年11月30日 上午11:17:11    
 *     
 */
/**     
 * 类名称：Fibonicci    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年11月30日 下午12:33:09    
 *     
 */
public class Fibonicci extends RecursiveTask<Integer> {

	private static final long serialVersionUID = -8743553496349856789L;
	private final int n;

	public Fibonicci(int n) {
		this.n = n;
	}

	/**    
	 * 方法作用：  主函数
	 * @return      
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	*/
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		ForkJoinTask<Integer> forkJoinTask = new Fibonicci(7);
		ForkJoinPool fj = new ForkJoinPool(); //创建forkjoin池
		ForkJoinTask<Integer> future = fj.submit(forkJoinTask); //提交任务
		System.out.println(future.get()); //获取结果

		System.out.println("-------------");

		System.out.println(fj.invoke(forkJoinTask)); //通过invoke来调用获取结果
	}

	/**
	 *重写方法
	 *计算斐波那契数列   forkjoin实现
	 */
	@Override
	protected Integer compute() {
		if (n <= 1)
			return n;
		Fibonicci f1 = new Fibonicci(n - 1);
		f1.fork(); //fork
		Fibonicci f2 = new Fibonicci(n - 2);
		return f2.compute() + f1.join(); //join

	}

}
