package com.common.pattern.singleton;
/**
 * 双重检查使用volatile保证线程安全
 * @author subo
 *
 */
public class Singleton2 {
	private volatile static Singleton2 singleton;
	private Singleton2() {
		
	}
	public static Singleton2 getInstance() {
		if(singleton == null) {
			synchronized (Singleton2.class) {
				if(singleton == null) {
					singleton = new Singleton2();
				}
			}
		}
		return singleton;
	}
}
