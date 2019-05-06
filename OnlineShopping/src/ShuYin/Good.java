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

	public Good(String Type, String Name, float Price) { // 肖飞杨调整了形参的顺序
		type = Type;
		price = Price;
		name = Name;
	}

	public float getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

}
