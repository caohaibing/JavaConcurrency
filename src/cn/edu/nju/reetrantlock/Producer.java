package cn.edu.nju.reetrantlock;

/**     
 * 类名称：Producer    
 * 类描述：    
 * @author：Administrator    
 * @version：2015年11月23日 上午9:12:20    
 *     
 */
public class Producer implements Runnable {

	private Storage storage;
	private int x;

	/**    
	 * 创建一个新的实例 Producer.    
	 *        
	 */
	public Producer(Storage storage, int x) {
		this.storage = storage;
		this.x = x;
	}

	private void put() {
		storage.put(x);
	}

	@Override
	public void run() {
		this.put();
	}

}
