package cn.edu.nju.consumerproducer;

/**
 * 类名称：Producer 类描述：
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午8:32:04
 * 
 */
public class Producer implements Runnable {

	private BoundedBuffer buf; 
	private volatile int i;   //记录生产的资源
	public Producer(BoundedBuffer buf,int i) {
		this.buf = buf;
		this.i=i;
	}
	
	@Override
	public void run() {
		try {
			Resource resource = new Resource(i);
			CommonUtils.print("生产了" + resource);
			Thread.sleep(2000);
			buf.put(resource);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
