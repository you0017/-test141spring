package com.yc.cglib;

public class OrderBizImpl {

    public void showOrder() {
        System.out.println("showOrder");
    }


    public int updateOrder(int orderId, int money) {
        System.out.println("updateOrder,更行订单"+orderId+",金额"+money);
        return 1;
    }


    public void saveOrder(int orderId) {
        System.out.println("saveOrder,保存订单"+orderId);
    }


    public void findAllOrder() {
        System.out.println("findAllOrder");
    }
}
