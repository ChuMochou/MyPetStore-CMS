package org.csu.petstore.service;

import org.csu.petstore.Vo.CartVO;
import org.csu.petstore.entity.LineItem;
import org.csu.petstore.entity.OrderStatus;
import org.csu.petstore.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> getAccountOrders(String username);

    List<Orders> getAllOrders();

    void insertOrder(Orders orders);

    void insertLineItem(List<CartVO> cartItemList, int oderId);

    void insertOrderStatus(int orderId);

    int getMaxOrderId();

    OrderStatus getOrderStatus(int orderId);

    Orders getOrder(int orderId);

    List<LineItem> getLineItems(int orderId);

    void deleteOrder(int orderId);

    void shipOrder(int orderId);

    List<Orders> searchOrders(String keyword);
}
