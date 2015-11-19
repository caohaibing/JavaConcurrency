package cn.edu.nju.condition;

/**
 * 类名称：Resource 类描述：
 * 
 * @author：Administrator
 * @version：2015年9月20日 下午9:06:10
 * 
 */
public class Resource {
	private int i;

	public Resource(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return "资源" + i;
	}
}
