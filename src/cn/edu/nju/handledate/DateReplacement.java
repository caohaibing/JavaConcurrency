package cn.edu.nju.handledate;

/**     
 * 类名称：DateReplacement    
 * 类描述：    对文档的日期替换  线程类
 * @author：Administrator    
 * @version：2015年11月30日 上午9:05:44    
 *     
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

public class DateReplacement implements Runnable {

	private FileFactory factory;

	public DateReplacement(FileFactory f) {
		this.factory = f;
	}

	/**
	 *线程方法
	 */
	@Override
	public void run() {
		try {
			while (true) {
				if (factory.getIsExitSignal()) {
					break;
				}
				String name = factory.getFileName();
				String path = factory.getFilePath();
				this.writeWord(path, name);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 线程执行完毕！");
	}

	/**    
	 * 方法作用：  写文档word
	 * 穿入 文档 的路径
	 * @return      
	*/

	public void writeWord(String filePath, String fileName) throws Exception {
		if (fileName == null || fileName.length() <= 0 || filePath == null
				|| filePath.length() <= 0)
			return;

		InputStream is = new FileInputStream(filePath); //创建输入流
		HWPFDocument doc = new HWPFDocument(is); //读写word文档的对象

		Range range = doc.getRange();
		String allText = doc.getDocumentText(); //获取所有文本

		Pattern pt = Pattern.compile("\\d{4}[\\.-]+[0-9]+[\\.-]+[0-9]+"); //匹配日期
		Matcher matcher = pt.matcher(allText);

		while (matcher.find()) { //匹配到就调用自己写的函数，进行处理
			String s = matcher.group();
			String resultStr = replaceYear(s);
			range.replaceText(s, resultStr); //替换

			System.out.println(s); //打印看每个文件匹配，且被修改的有多少
			System.out.println(resultStr);
		}

		System.out.println();
		//readTable(range);
		//System.out.println(doc.getDocumentText());

		OutputStream os = new FileOutputStream(new File("D:\\ResultFiles\\"
				+ fileName)); //输出流
		doc.write(os); //写到目的文件
		os.flush();
		closeStream(os); //关闭流
		closeStream(is);

	}

	/**    
	 * 方法作用：  读取表格
	 * @return      
	*/
	public static void readTable(Range range) {

		TableIterator tableIterator = new TableIterator(range); //获取表格
		Table table;
		TableRow row;
		TableCell cell;

		while (tableIterator.hasNext()) { //迭代表格行和列
			table = tableIterator.next();

			int rowNum = table.numRows();
			for (int i = 0; i < rowNum; i++) {
				row = table.getRow(i);
				int cellNum = row.numCells(); //单元格的数目
				for (int k = 0; k < cellNum; k++) {
					cell = row.getCell(k);
					//输出单元格的文本  
					System.out.print(cell.text().trim() + "  "); //输出单元格的文本

				}
				System.out.println();
			}
		}

	}

	/**    
	 * 方法作用：  对日期进行处理，将2015-03-30 这种格式改为  2015年3月30日
	 * @return      
	*/
	public static String replaceYear(String str) {
		String[] arr = str.split("[\\.-]+");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				arr[i] = arr[i] + "年";
			}
			if (i == 1) {
				String s = arr[i];
				if (s.charAt(0) == '0') {
					s = s.substring(1);
				}
				arr[i] = s + "月";
			}
			if (i == 2) {
				String s = arr[i];
				if (s.charAt(0) == '0') {
					s = s.substring(1);
				}
				arr[i] = s + "日";
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/** 
	   * 关闭输入流 
	   * @param is 
	   */
	private static void closeStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** 
	 * 关闭输出流 
	 * @param os 
	 */
	private static void closeStream(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
