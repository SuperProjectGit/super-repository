package com.common.pattern;
/**
 * 线程安全的单例，但是性能不好
 * @author subo
 *
 */
public class Singleton1 {
	private static Singleton1 singleton = null;
	private Singleton1() {
		
	}
	
	public static synchronized Singleton1 getInstance() {
		if(singleton == null) {
			singleton = new Singleton1();
		}
		return singleton;
	}
}
