package ShuYin;

/**
 * @see 商品父类的定义
 * @author 舒引
 *
 */
public class Commodity {
	protected String type;

	public Commodity() {
		// 默认构造函数
	}

	public Commodity(String x) {
		type = x;// �в�
	}

	public String getType() {
		return this.type;
	}

}
