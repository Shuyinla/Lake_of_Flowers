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
	private User[] usrDatabase = {new User("0x0001",1000),new User("0x0002",1500),new User("0x0003",500)}; 
	private User currentUsr = null;
	
	private Manager(){
		currentUsr = usrDatabase[0];
	}
	
	public static Manager getManager() { // 返回全局唯一存在的Manager对象
		if (varManager == null){
			varManager = new Manager();
		}
		return varManager; 
	}
	
	/**
	 * @author 肖飞杨
	 */
	private void printList(){ // 打印商店里的所有商品及其库存量
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
	private int searchExactlyFor(String itemName){ // 按商品名称精准搜索某件商品
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
	private void searchVaguelyFor(String itemName){ // 按商品名称模糊搜索某件商品，利用正则表达式
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
		
		while(true)
		{
			showMainMenu(); 
			int usrChoice = getUsrInput_INT("请选择您的操作类型："); 
			switch(usrChoice){
				case 1:
					switchUsr();
					continue; 
				case 2:
					this.printList();
					continue;
				case 3:
					service_searchFor(); 
					continue; 
				case 4:
					service_Buy(getUsrInput_STRING("请输入欲购买的商品名"), getUsrInput_INT("请输入欲购买的数量")); 
					continue;
				case 5:
					currentUsr.inspectCart();
					continue;
				case 6:
					service_Remove(getUsrInput_STRING("请输入欲删除的商品名")); 
					continue; 
				case 7:
					service_clearCart(); 
					continue; 
				case 8:
					service_editQuantity();
					continue;
				case 9:
					break;
				default:
					System.out.println("输入错误");
					continue;
			}
			break; 
		}
		
		System.out.println("感谢您的使用，再见！");
		return 0; 
	}
	
	private void showMainMenu(){ // 显示主菜单
		System.out.println("请选择您的操作类型：");
		System.out.println("1. 切换用户");
		System.out.println("2. 显示仓库库存");
		System.out.println("3. 搜索商品");
		System.out.println("4. 购买商品");
		System.out.println("5. 查看购物车内容");
		System.out.println("6. 删除商品");
		System.out.println("7. 清空购物车");
		System.out.println("8. 修改购买量");
		System.out.println("9. 退出");
	}
	
	private void switchUsr(){ // 切换操作的用户
		for(int i=0;i<usrDatabase.length;i++){
			System.out.println("用户编号："+(i+1)+"，用户ID："+usrDatabase[i].getUsrID());
		}
		int usrChoice = 0;
		
		while(true){
			usrChoice = getUsrInput_INT("请输入要切换到的用户的编号：");
			
			try{
				currentUsr = usrDatabase[usrChoice-1]; 
				System.out.println("已切换用户");
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("不存在此编号的用户！");
				continue;
			}
			break;
		}
	}
	
	/**
	 * @see 搜索功能的总方法
	 * @author 肖飞杨
	 */
	private void service_searchFor(){ // 搜索业务的总逻辑
		String content = getUsrInput_STRING("请输入您的搜索内容："); 
		int usrChoice = getUsrInput_INT("请选择您的搜索类型：1.精准搜索 2.模糊搜索"); 
		
		while(true){
			switch(usrChoice){
				case 1:
					searchExactlyFor(content);
					break;
				case 2:
					searchVaguelyFor(content);
					break;
				default:
					continue;
			}
		}
	}	

	/**
	 * @author 肖飞杨
	 */
	private int service_Buy(String itemName, int quantity){ // 购买商品的业务总逻辑
		for(int i=0;i<inventory.length;i++){ // 使用循环遍历库存数组检查是否有此商品
			if(itemName.equals(inventory[i].getName())){ // 检测到名称有匹配
				if(invenQuantity[i]>=quantity){ // 如果库存量足够
					currentUsr.Buy(inventory[i], quantity); // 调用当前用户对象的购买方法
					invenQuantity[i] -= quantity; // 库存量响应改变
					System.out.println("商品已加入购物车！");
					return 0; 
				}else{ // 若库存不足够
					System.out.println("购买量大于库存量！");
					return 0;
				}
			}
		}
		System.out.println("商店中无此商品！");
		return 0;
	}
	
	/**
	 * @param itemName 欲删除的购物车中的商品名称
	 * @author 肖飞杨
	 */
	private int service_Remove(String itemName){ // 删除购物车内某件商品的业务总逻辑
		for(int i=0;i<inventory.length;i++){
			if(itemName.equals(inventory[i].getName())){
				int isRemoved = currentUsr.removeOrder(inventory[i]); // 尝试删除商品并返回购物车中删除的该商品的数量
				
				if(isRemoved!=0){
					System.out.println("订单已删除");
					invenQuantity[i] += isRemoved; // 把删除的商品的数量补回到库存量中
					return 1; 
				}else{
					System.out.println("购物车内无此商品！");
					return 0; 
				}
				
			}
		}
		System.out.println("我们没有卖这种商品！");
		return 0;
	}
	
	
	private void service_clearCart(){
		for(int i=0; i<inventory.length; i++){
			invenQuantity[i] += currentUsr.removeOrder(inventory[i]); 
		}
		currentUsr.clearCart(); 
	}
	
	private void service_editQuantity(){ // 修改购物车内某件商品的购买量
		String itemName = getUsrInput_STRING("请输入欲修改数量的购物车内的商品名称：");
		int itemQuantity = getUsrInput_INT("请输入购买量：");
		int itemIndex = locateItemIndex(itemName);
		
		if(itemIndex != -1){
			if(invenQuantity[itemIndex]<itemQuantity){
				int oldQuantity = currentUsr.setQuantity(inventory[itemIndex], itemQuantity);
				if(oldQuantity>0){
					invenQuantity[itemIndex] -= itemQuantity-oldQuantity;
					System.out.println("修改成功！");
				}else{
					System.out.println("购物车内无此商品！");
				}
			}else{
				System.out.println("库存不足！");
			}
		}else{
			System.out.println("我们可不卖这种商品！");
		}
		
	}
	/**
	 * 
	 * @author 肖飞杨
	 * @param itemName 欲定位的商品的名称
	 * @return 符合名称的商品下标
	 */
	private int locateItemIndex(String itemName){ // 根据商品名返回该商品在数组中的下标
		for(int i=0; i<inventory.length;i++){
			if(itemName.equals(inventory[i].getName())){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param hint 用户输入前的提示语
	 * @author 肖飞杨
	 */
	private int getUsrInput_INT(String hint){ // 要求用户输入数字并返回
		@SuppressWarnings("resource")
		Scanner myScanner = new Scanner(System.in);
		System.out.println(hint);
		int usrChoice = myScanner.nextInt(); 
		return usrChoice; 
	}
	
	/**
	 * @param hint 用户输入前的提示语
	 * @author 肖飞杨
	 */
	private String getUsrInput_STRING(String hint){ // 要求用户输入字符串并返回
		@SuppressWarnings("resource")
		Scanner myScanner = new Scanner(System.in);
		System.out.println(hint);
		String usrChoice = myScanner.next(); 
		return usrChoice; 
	}
}