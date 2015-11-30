package cn.edu.nju.reetrantlock;

/**     
 * 类名称：Consumer    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年11月23日 上午9:28:39    
 *     
 */
public class Consumer implements Runnable {
	
	private Storage storage;
	private int x;
	
	public Consumer(Storage storage,int x){
		this.storage=storage;
		this.x=x;
	}
	
	private int take(){
		return storage.take(x);
	}
	
	@Override
	public void run() {
		this.take();
	}

}
