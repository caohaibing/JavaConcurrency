package cn.edu.nju.consumerproducer;

/**
 * 类名称：Consumer 类描述：
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午8:32:15
 * 
 */
public class Consumer implements Runnable {

	private BoundedBuffer buf;

	public Consumer(BoundedBuffer buf) {
		this.buf = buf;

	}

	@Override
	public void run() {
		while (true) {
			try {
				Resource resource = (Resource) buf.take();
				CommonUtils.print("消费了" + resource);
				Thread.sleep(6000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
