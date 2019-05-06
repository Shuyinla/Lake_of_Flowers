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
		for(int i = 0; i < this.itemList.length; i++){
			System.out.println(this.itemList[i].getName()+" "+this.quantityList[i]);
		}
		
	}
	
	public float getSum() {//计算并返回购物车中的物品总价
		float sum=0;
		for(int i = 0; i < this.itemList.length; i++) {
			sum += this.itemList[i].getPrice() * this.quantityList[i];  
		}
		return sum;
	}
	
	public void additem(Good item) {//将某件商品加入购物车
		if(itemList.length == 1) {//当购物车为空的时候加入商品
			this.itemList[0] = item;
			this.quantityList[0] = 1 ;
		}
		else {//当购物车不为空的时候加入商品
			if(item.getName().equals(itemList[itemList.length].getName())){
				this.quantityList[quantityList.length] += 1; 
			}
			else{
				this.itemList = Arrays.copyOf(itemList,itemList.length+1);
				this.quantityList = Arrays.copyOf(quantityList, quantityList.length+1);
				this.itemList[itemList.length] = item;
				this.quantityList[quantityList.length] = 1;
			}
		}
	}
	
	public void editQuantity(Good item , int quantity) {//修改某件物品的数量
		for(int i = 0; i < itemList.length; i++) {
			if(this.itemList[i].getName().equals(item.getName())) {
				this.quantityList[i] = quantity;
			}
		}
	} 
	
	public void removeItem(Good item) {//删除购物车中的某件商品
		int num = 0;
		for(int i = 0; i < this.itemList.length; i++) {
			if(!this.itemList[i].getName().equals(item.getName())) {
				itemList[num] = itemList[i];
			}
		}
	}
}
