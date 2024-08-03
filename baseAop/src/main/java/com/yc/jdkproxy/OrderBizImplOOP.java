package com.yc.jdkproxy;

public class OrderBizImplOOP extends OrderBizImpl{
    @Override
    public void showOrder() {
        checkRights();
        super.showOrder();
    }

    @Override
    public int updateOrder(int orderId, int money) {
        checkRights();
        return super.updateOrder(orderId, money);
    }

    @Override
    public void saveOrder(int orderId) {
        checkRights();
        super.saveOrder(orderId);
    }

    public void checkRights(){
        System.out.println("检查权限");
    }
}
