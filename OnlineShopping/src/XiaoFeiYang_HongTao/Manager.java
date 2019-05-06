package XiaoFeiYang_HongTao;

import ShuYin.Good;
import ShuYin.User;

import java.util.Scanner;
import java.util.regex.*;

/**
 * 
 * @author XiaoFeiYang&HongTao
 *
 */


public class Manager {
	private static Manager varManager = null; 
	private static Good[] inventory = {new Good("Food", "Beef", 50), new Good("Food", "Mutton", 45), new Good("Food", "Candy", 2), new Good("Electronics", "Watch", 30), new Good("Clothes", "T-shirt", 20)};
	private static int[] invenQuantity = {3, 8, 2, 15, 10};
	private User[] usrDatabase = {}; 
	
	private Manager(){
		
	}
	
	public static Manager getManager() { // 返回全局唯一存在的一个Manager对象
		if (varManager == null){
			varManager = new Manager();
		}
		return varManager; 
	}
	
	/**
	 * @author 肖飞杨
	 */
	public void printList(){ // 打印商店里的所有商品及其库存量
		System.out.println("商店库存情况如下：");
		for(int i=0; i<inventory.length;i++){
			System.out.println("商品名："+inventory[i].getName()+"，库存："+invenQuantity[i]);
		}
	}
	
	/**
	 * 
	 * @author 肖飞杨
	 * @param itemName 欲搜索的商品的名称
	 */
	public int searchExactlyFor(String itemName){ // 按商品名称精准搜索某件商品
		for(int i=0;i<inventory.length;i++){
			if(itemName.equals(inventory[i].getName())){
				int itemNo = i+1;
				System.out.println("商品编号："+itemNo+"，商品名称："+inventory[i].getName()+"，价格："+inventory[i].getPrice());
				return i; 
			}
		}
		System.out.println("商店中无此商品！");
		return -1; 
	}
	
	/**
	 * @author 肖飞杨
	 * @param itemName 欲搜索的商品的名称
	 */
	public void searchVaguelyFor(String itemName){ // 按商品名称模糊搜索某件商品，利用正则表达式
		String pattern = ".*"+itemName+".*"; 
		System.out.println("搜索结果如下：");
		for(int i=0;i<inventory.length;i++){
			if(Pattern.matches(pattern, inventory[i].getName())){
				int itemNo = i+1;
				System.out.println("商品编号："+itemNo+"，商品名称："+inventory[i].getName()+"，价格："+inventory[i].getPrice());
			}
		}
		System.out.println("搜索完成！");
	} 
	/**
	 * @author 肖飞杨
	 */
	public int serviceStart(){ 
		System.out.println("欢迎使用在线购物系统！");
		
		Scanner myScanner = new Scanner(System.in); 
		
		while(true)
		{
		showMainMenu(); 
		int usrChoice = myScanner.nextInt(); 
		switch(usrChoice){
			case 1:
				continue; 
			case 2:
				this.printList();
				continue;
			case 3:
				System.out.println("请输入您欲搜索的商品的名称：");
				this.searchExactlyFor(myScanner.next());
				continue; 
			case 4:
				System.out.println("请输入您欲搜索的商品的名称：");
				this.searchVaguelyFor(myScanner.next());
				continue;
			case 5:
				this.showUserMenu();
				continue;
			case 6:
				break; 
		}
		break;
		}
		
		return 0; 
	}
	
	private void showMainMenu(){ // 显示主菜单
		System.out.println("请选择您的操作类型：");
		System.out.println("1. 切换用户");
		System.out.println("2. 显示仓库库存");
		System.out.println("3. 精准查询商品");
		System.out.println("4. 模糊查询商品");
		System.out.println("5. 用户相关操作");
		System.out.println("6. 退出");
	}
	
	private void showUserMenu(){
		System.out.println("请选择您的操作类型：");
		System.out.println("1. 切换用户");
		System.out.println("2. 显示仓库库存");
		System.out.println("3. 精准查询商品");
		System.out.println("4. 模糊查询商品");
		System.out.println("5. 用户相关操作");
	}
}