package ShuYin;

import ChenXiang_FuYan.Cart;

public class User {
	private float userBalance;// ���
	private String userID;// �û�ID
	private static float count;// ��¼�ۼ�����
	private Cart userCart = new Cart();// ���ﳵ���� Ф�������private����
	// ��ֵ����

	public User() {

	}

	public User(String id, float a) {
		userBalance = a;
		userID = id;
	}

	public String getUsrID(){
		return this.userID;
	}
	
	public void deposit(int a) {// ��ֵ����
		userBalance += a;
	}

	public void checkout() {// ���˷���
		double sum;
		sum=0;
		String tip;
		//��ʶ�ȼ�,ÿ�ε��ø÷�����������жϣ��Ӷ�ʵ�ֽ���ʱ��������Զ�ˢ�µȼ�
		if(userBalance>3000) {
			tip="gold";
		}else if(userBalance<=3000 && userBalance>1000) {
			tip="silver";
		}else {
			tip="copper";
		}
		//�ж��û��ȼ����ۻ��������������Ӧ����
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

	public void Buy(Good item, int quantity) {
		userCart.additem(item, quantity);
	}

	public int removeOrder(Good item) {
		return userCart.removeItem(item);
	}

	public void inspectCart() {
		userCart.printList();
	}
	public void clearCart(){ // Ф����䷽������չ��ﳵ
		userCart.clearAll();
	}
	
	public int setQuantity(Good item, int quantity){
		return userCart.editQuantity(item, quantity);
	}
}
