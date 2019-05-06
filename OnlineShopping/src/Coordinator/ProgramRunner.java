package Coordinator;
import ShuYin.Commodity;
import ShuYin.Good;
import XiaoFeiYang_HongTao.Manager;
import ChenXiang_FuYan.Cart;

public class ProgramRunner {
	public static void main(String[] args) {
		Cart myCart = new Cart(); 
		Good good1 = new Good("Food", "Sausage", 8);
		Good good2 = new Good("Food", "Candy", 2);
		
		myCart.additem(good1, 3);
		myCart.additem(good2, 5);
		myCart.editQuantity(good2, 1);
		myCart.printList();
		myCart.getSum();
		
		myCart.removeItem(good1);
		myCart.printList();
	}
}
