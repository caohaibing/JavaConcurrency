package cn.edu.nju.condition;


/**
 * 类名称：Producer 类描述：生产者
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午8:32:04
 * 
 */
public class Producer implements Runnable {

	private BoundedBuffer buf;

	public Producer(BoundedBuffer buf) {
		this.buf = buf;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Resource resource = new Resource(i);
				CommonUtils.print("生产了" + resource);
				Thread.sleep(1000);
				buf.put(resource);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
