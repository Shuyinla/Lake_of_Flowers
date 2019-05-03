package Coordinator;
import XiaoFeiYang_HongTao.Manager;

public class ProgramRunner {
	public static void main(String[] args) {
		Manager myManager = Manager.getManager(); 
		myManager.printList(); 
	}
}
