package com.yc.myproxy;


//目标类:增强的类
//需求: 对这个类中的 showOrder,saveOrder,updateOrder增强功能
        //在上面三个方法运行前做权限验证
        //在上面三个方法运行后做日志记录
public class OrderBizImpl implements OrderBiz {
    @Override
    public void showOrder() {
        System.out.println("showOrder");
    }

    @Override
    public int updateOrder(int orderId, int money) {
        System.out.println("updateOrder,更行订单"+orderId+",金额"+money);
        return 1;
    }

    @Override
    public void saveOrder(int orderId) {
        System.out.println("saveOrder,保存订单"+orderId);
    }

    @Override
    public void findAllOrder() {
        System.out.println("findAllOrder");
    }
}
