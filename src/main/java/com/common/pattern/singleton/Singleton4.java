package com.common.pattern.singleton;

/**
 * 内部类单例
 * @author subo
 *
 */
public class Singleton4 {
	private Singleton4() {
		
	}
	
	private static class SingletonHolder {
		private final static Singleton4 SINGLETON = new Singleton4();
	}
	
	public static Singleton4 getInstance() {
		return SingletonHolder.SINGLETON;
	}
}
