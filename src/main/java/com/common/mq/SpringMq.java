package com.common.mq;

import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;

public class SpringMq {
	public static void main(String[] args) {
		ActiveMQQueue am = new ActiveMQQueue();
		System.out.println(am instanceof ActiveMQDestination);
	}
}
