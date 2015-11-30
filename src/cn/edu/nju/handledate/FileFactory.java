package cn.edu.nju.handledate;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**     
 * 类名称：FileFactory    
 * 类描述：    工厂类，共享区，共享每次获取的文件
 * @author：Administrator    
 * @version：2015年11月30日 下午8:39:49    
 *     
 */
public class FileFactory {
	//	private List<String> filePaths = new Vector<String>();
	//	private List<String> fileNames = new Vector<String>();
	private List<String> filePaths = new CopyOnWriteArrayList<String>();
	private List<String> fileNames = new CopyOnWriteArrayList<String>();

	private Lock lock = new ReentrantLock(); //可重入锁
	private volatile boolean isExit = false; //当集合中没有元素时，要结束线程

	public FileFactory() {
		String[] p = new ConcurrentUtils().getPaths();
		String[] n = new ConcurrentUtils().getName();
		if (p == null || p.length <= 0 || n == null || n.length <= 0) {
			System.out.println("没有数据");
			System.exit(0);
		}

		for (int i = 0; i < p.length && p[i] != null; i++) {
			filePaths.add(p[i]);
			fileNames.add(n[i]);
		}
	}

	/**    
	 * 方法作用：  返回是否让线程结束的信号
	 * @return      
	*/
	public boolean getIsExitSignal() {
		return this.isExit;
	}

	/**    
	 * 方法作用：  获取文件名
	 * @return      
	*/
	public String getFileName() {
		try {
			lock.lock(); //开启锁
			String str = null;
			if (fileNames != null && fileNames.size() > 0) {
				str = fileNames.get(0);
				fileNames.remove(0);
			} else {
				this.isExit = true;
			}
			return str;

		} finally {
			lock.unlock(); //锁释放
		}
	}

	/**    
	 * 方法作用：  获取文件路径
	 * @return      
	*/
	public String getFilePath() {
		try {
			lock.lock(); //加锁
			String str = null;
			if (filePaths != null && filePaths.size() > 0) {
				str = filePaths.get(0);
				filePaths.remove(0);
			} else {
				this.isExit = true;
			}
			return str;
		} finally {
			lock.unlock(); //锁释放
		}
	}

}
