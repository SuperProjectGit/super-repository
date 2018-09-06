package com.common.pattern.ResponsibilityChain;

/**
 * 处理请假请求抽象类
 *
 * @author subo
 * @create 2018-09-06 20:51
 **/
public abstract class Leader {

    protected String name;
    protected Leader nextLeader;//下一个继承者
    public Leader(String name) {
        super();
        this.name = name;
    }
    //设置责任链上的下一个继承者
    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }
    //处理请求的抽象方法
    public abstract void handleRequest(LeaveRequest leader);
}
