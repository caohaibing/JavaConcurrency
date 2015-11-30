package cn.edu.nju.handledate;

import java.io.File;

/**     
 * 类名称：ConcurrentUtils    
 * 类描述：    并发工具类
 * @author：Administrator    
 * @version：2015年11月30日 下午5:05:23    
 *     
 */
public class ConcurrentUtils {

	private String[] filePaths;
	private String[] fileNames;
	private String path = "E:\\ProcessFiles\\";

	public ConcurrentUtils() {
		this.getFileList();
	}

	public String[] getPaths() {
		return filePaths;
	}

	public String[] getName() {
		return fileNames;

	}

	/**    
	 * 方法作用：获取文件绝对路径和文件名
	 * @return      
	*/
	public void getFileList() {
		try {
			File root = new File(path);
			File[] wordFiles = root.listFiles(); //获取文件列表
			filePaths = new String[wordFiles.length];
			fileNames = new String[wordFiles.length];
			for (int i = 0; i < wordFiles.length; i++) {
				filePaths[i] = wordFiles[i].getCanonicalPath(); //获取文件的绝对路径;
				fileNames[i] = wordFiles[i].getName();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
