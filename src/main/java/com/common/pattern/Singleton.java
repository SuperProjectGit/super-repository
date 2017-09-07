package com.common.pattern;
/**
 * 多线程环境有线程安全问题
 * @author subo
 *
 */
public class Singleton {
	private static Singleton singleton = null;
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
}
