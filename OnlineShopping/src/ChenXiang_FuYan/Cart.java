package ChenXiang_FuYan;
/**
 * 
 * @author 陈想&付艳
 *
 */
import java.util.Arrays;
import ShuYin.Good;

public class Cart {

	private Good itemList[] = new Good[1];
	private int quantityList[] = new int[1];
	
	public void printList() {//打印购物车中的所有物品及其数量
		System.out.println("购物车内的商品及其数量如下："); // 肖飞杨修改了显示的格式
		if(this.itemList[0]!=null){ // 肖飞杨添加了当购物车为空时的显示内容
			for(int i = 0; i < this.itemList.length; i++){
				System.out.println("商品名："+this.itemList[i].getName()+"，数量："+this.quantityList[i]);
			}
		}
		else{
			System.out.println("当前购物车为空！");
		}
	}
	
	public float getSum() {//计算并返回购物车中的物品总价
		if(this.itemList[0]!=null){ // 肖飞杨添加了当购物车为空时的返回内容
			float sum=0;
			for(int i = 0; i < this.itemList.length; i++) {
				sum += this.itemList[i].getPrice() * this.quantityList[i];  
			}
			return sum;
		}
		else {
			return 0; 
		}
	}
	
	public void additem(Good item, int quantity) {//将某件商品加入购物车 肖飞杨修改需求添加形参：购买数量
		if(this.itemList[0] == null) {//当购物车为空的时候加入商品   肖飞杨更正判断为空的条件
			this.itemList[0] = item;
			this.quantityList[0] = quantity;
		}
		else {//当购物车不为空的时候加入商品
			int good_index = this.isExist(item); // 检查欲添加的商品是否已添加进购物车，若是则返回下标
			
			if(good_index!=-1){ // 肖飞杨添加注释：当购物车内已存在此商品时，只需修改数量
				this.quantityList[good_index] += quantity; 
			}
			else{ // 肖飞杨补充注释：否则当购物车内还没有这件商品时
				this.itemList = Arrays.copyOf(itemList,itemList.length+1);
				this.quantityList = Arrays.copyOf(quantityList, quantityList.length+1);
				this.itemList[itemList.length-1] = item;
				this.quantityList[quantityList.length-1] = quantity;
			}
		}
	}
	
	public int editQuantity(Good item , int quantity) {//修改某件物品的数量
		if(quantity != 0){ // 肖飞杨补充逻辑：修改数量不能为0
			for(int i = 0; i < itemList.length; i++) {
				if(this.itemList[i].getName().equals(item.getName())) {
					int oldQuantity = this.quantityList[i]; 
					this.quantityList[i] = quantity;
					return oldQuantity;
				}
			}
			return 0; 
		}
		else {
			System.out.println("商品数量不能修改为0！");
			return 0; 
		}
	} 
	
	/**
	 * 
	 * @return 被删除的商品的数量
	 * @param item 欲删除的商品对象
	 * @return
	 */
	public int removeItem(Good item) {//删除购物车中的某件商品 肖飞杨重写
		int num = 0;
		int good_index = this.isExist(item); 
		
		if(good_index != -1){ // 当购物车内有这件商品时才……
			
			if(this.itemList.length == 1){ // 当删除的这件商品是购物车内的唯一商品时
				int quantity_removed = quantityList[0]; 
				this.itemList = new Good[1]; // 直接把属性中的2个数组赋值为2个初始空组即可
				this.quantityList = new int[1]; 
				return quantity_removed; //删除成功
			}
			else{ // 不是唯一一件，就慢慢遍历咯
				int quantity_removed = 0;
				Good[] tmp = new Good[this.itemList.length-1];
				int[] tmp_quantity = new int[this.quantityList.length-1]; 
				
				for(int i = 0; i < this.itemList.length; i++) { // 通过逐一赋值元素的方式得到一个不含被删商品的数组
					if(i!=good_index) {
						tmp[num] = itemList[i];
						tmp_quantity[num] = quantityList[i]; 
						num++; 
					}
					if(i==good_index) {
						quantity_removed = quantityList[i];
					}
				}
				this.quantityList = tmp_quantity; 
				this.itemList = tmp; 
				return quantity_removed; // 删除成功
			}
		}
		else{ // 要删除的商品根本没有在购物车内
			// System.out.println("您要删除的商品不存在！"); 提示信息转由Manager类提供，故删除此提示
			return 0;
		}
		
	}
	
	public int isExist(Good item){ // 肖飞杨补充方法：检查某件商品Good是否已存在于非空购物车中，是则返回所在下标，否则返回-1
		if(this.itemList[0]==null){ // 购物车为空
			return -1; // 不用遍历检测，直接不存在
		}
		
		for(int i=0; i<this.itemList.length; i++){
			if(this.itemList[i].getName() == item.getName()){
				return i; 
			}
		}
		
		return -1; 
	}
	
	public void clearAll(){ // 肖飞杨补充方法：清空购物车 
		this.itemList = new Good[1]; 
		this.quantityList = new int[1]; 
	}
}
