package cn.edu.nju.countdownlatch;

/**     
 * 类名称：Main    
 * 类描述：    主类
 * @author：Administrator    
 * @version：2015年12月7日 上午9:22:05    
 *     
 */
public class Main {

	/**    
	 * 方法作用：  
	 * @return      
	*/
	public static void main(String[] args) {
		Driver driver = new Driver();
		try {
			driver.serveWorker();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
