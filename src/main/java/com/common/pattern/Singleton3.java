package com.common.pattern;
/**
 * 饿汉式单例，内存使用效率不高
 * @author subo
 *
 */
public class Singleton3 {
	private static Singleton3 singleton = new Singleton3();
	
	private Singleton3() {
		
	}
	
	public static Singleton3 getInstance() {
		return singleton;
	}
}

