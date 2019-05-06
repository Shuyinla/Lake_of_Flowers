package Coordinator;
import ShuYin.Commodity;
import ShuYin.Good;
import XiaoFeiYang_HongTao.Manager;

import java.util.regex.Pattern;

import ChenXiang_FuYan.Cart;

public class ProgramRunner {
	public static void main(String[] args) {
		Manager myManager = Manager.getManager(); 
		myManager.serviceStart();
		
	}
}
