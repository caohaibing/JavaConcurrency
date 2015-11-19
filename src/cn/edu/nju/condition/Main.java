package cn.edu.nju.condition;

/**     
 * 类名称：Main    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年9月20日 下午8:32:54    
 *     
 */
public class Main {

	/**    
	 * 方法作用：  
	 * @return      
	 */
	public static void main(String[] args) {
		BoundedBuffer buf=new BoundedBuffer();
		Producer producer=new Producer(buf);
		Consumer consumer=new Consumer(buf);
		
		Thread t1=new Thread(producer);
		Thread t2=new Thread(consumer);
		
		t1.setPriority(Thread.MAX_PRIORITY);//设置生产者优先级高
		t2.setPriority(Thread.MIN_PRIORITY);//消费者优先级低
		
		t1.start();
		t2.start();
	}

}
