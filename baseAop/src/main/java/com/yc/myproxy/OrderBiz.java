package com.yc.myproxy;

public interface OrderBiz {
    public void showOrder();
    public int updateOrder(int orderId,int money);
    public void saveOrder(int orderId);
    public void findAllOrder();
}
