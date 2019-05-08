package ShuYin;

import ChenXiang_FuYan.Cart;

public class User {
	private float userBalance;// 余额
	private String userID;// 用户ID
	private static float count;// 记录累计消费
	private Cart userCart = new Cart();// 购物车对象 肖飞杨添加private修饰
	// 充值方法

	public User() {

	}

	public User(String id, float a) {
		userBalance = a;
		userID = id;
	}

	public String getUsrID(){
		return this.userID;
	}
	
	public void deposit(int a) {// 充值方法
		userBalance += a;
	}

	public void checkout() {// 结账方法
		double sum;
		sum=0;
		String tip;
		//标识等级,每次调用该方法进行余额判断，从而实现结账时根据余额自动刷新等级
		if(userBalance>3000) {
			tip="gold";
		}else if(userBalance<=3000 && userBalance>1000) {
			tip="silver";
		}else {
			tip="copper";
		}
		if(userBalance>=userCart.getSum()){
		//判断用户等级和累积消费数额进行相应计算
		if(tip=="gold") {
			sum = userCart.getSum() * 9.7;
			userBalance-=sum;
		}else if(tip=="silver" && count>=10000 ) {
			sum = userCart.getSum() * 9.7;
			userBalance-=sum;
			count=0;
		}else if(tip=="silver") {
			sum = userCart.getSum() * 9.8;
			userBalance-=sum;
			count+=userCart.getSum();
		}else if(tip=="copper" && count>=10000 ) {
			sum = userCart.getSum() * 9.8;
			userBalance-=sum;
			count=0; 
		}else if(tip=="copper") {
			sum = userCart.getSum() * 9.9;
			userBalance-=sum;
			count+=userCart.getSum();
			}
		userCart.clearAll();
	}
	}else{
	System.out.println("余额不足，付款失败！");
	}

	public void Buy(Good item, int quantity) {
		userCart.additem(item, quantity);
	}

	public int removeOrder(Good item) {
		return userCart.removeItem(item);
	}

	public void inspectCart() {
		userCart.printList();
	}
	public void clearCart(){ // 肖飞杨补充方法：清空购物车
		userCart.clearAll();
	}
	
	public int setQuantity(Good item, int quantity){
		return userCart.editQuantity(item, quantity);
	}
}
