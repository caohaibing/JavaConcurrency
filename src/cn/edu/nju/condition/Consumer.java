package cn.edu.nju.condition;


/**
 * 类名称：Consumer 类描述：消费者类
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

	/**
	 *线程run方法
	 */
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {  //开启20个消费者，互不相干
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
