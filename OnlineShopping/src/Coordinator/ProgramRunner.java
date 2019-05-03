package Coordinator;
import ShuYin.Commodity;
import ShuYin.Good;
import XiaoFeiYang_HongTao.Manager;

public class ProgramRunner {
	public static void main(String[] args) {
		Manager myManager = Manager.getManager(); 
		myManager.printList(); 
		
		Commodity com = new Commodity("Food");
		System.out.println("商品的类型是"+com.getType());
		
		Good good = new Good(); 
		System.out.println("商品的类型是"+good.getType());
	}
}
