package XiaoFeiYang_HongTao;

/**
 * 
 * @author XiaoFeiYang&HongTao
 *
 */


public class Manager {
	private static Manager varManager = null; 
	private Manager(){
		
	}
	
	public static Manager getManager() { // 返回全局唯一存在的一个Manager对象
		if (varManager == null){
			varManager = new Manager();
		}
		return varManager; 
	}
	
	public void printList(){ // 打印商店里的所有商品及其库存量
		System.out.println("测试Manager的打印商店功能");
	}
}