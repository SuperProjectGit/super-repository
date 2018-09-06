package com.common.pattern.ResponsibilityChain;

/**
 * 处理请求的具体对象
 * 主任对象
 * @author subo
 * @create 2018-09-06 20:52
 **/
public class Director extends Leader {

    public Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest leader) {
        if (leader.getLeaveDays()<=3) {
            System.out.println("请假人："+leader.getEmpName()+",天数："+leader.getLeaveDays()+",理由："+leader.getReason());
            System.out.println("审批人："+this.name+" 主任，审批通过！");
        }else{
            if (this.nextLeader != null ) {//如果有下一个继承者
                //让下一个继承者处理请求
                this.nextLeader.handleRequest(leader);
            }
        }
    }
}
