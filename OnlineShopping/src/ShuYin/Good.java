package ShuYin;

/**
 * @see 商品子类的定义
 * @author 舒引
 *
 */
public class Good extends Commodity {
	private float price;
	private String name;

	public Good() {
		
	}

	public Good(String a, float b, String c) {
		type = a;
		price = b;
		name = c;
	}

	public float getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

}
