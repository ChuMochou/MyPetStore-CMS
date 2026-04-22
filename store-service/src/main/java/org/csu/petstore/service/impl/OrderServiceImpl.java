package org.csu.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.servlet.http.HttpSession;
import org.csu.petstore.Vo.CartVO;
import org.csu.petstore.entity.LineItem;
import org.csu.petstore.entity.OrderStatus;
import org.csu.petstore.entity.Orders;
import org.csu.petstore.persistence.LineItemMapper;
import org.csu.petstore.persistence.OrderStatusMapper;
import org.csu.petstore.persistence.OrdersMapper;
import org.csu.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public List<Orders> getAccountOrders(String username) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", username);
        queryWrapper.eq("deleted", 0);
        return ordersMapper.selectList(queryWrapper);
    }

    @Override
    public List<Orders> getAllOrders() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("orderid");
        queryWrapper.eq("deleted", 0);
        return ordersMapper.selectList(queryWrapper);
    }

    @Override
    public void insertOrder(Orders orders) {
        ordersMapper.insert(orders);
    }

    @Override
    public void insertLineItem(List<CartVO> cartList, int orderId) {
        for (CartVO cartItem : cartList) {
            // 查询当前订单的最大 lineNumber
            QueryWrapper<LineItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("orderid", orderId)
                    .orderByDesc("linenum")
                    .last("LIMIT 1");
            LineItem maxLineItem = lineItemMapper.selectOne(queryWrapper);
            int maxLineNumber = (maxLineItem != null) ? maxLineItem.getLineNumber() : 0;
            LineItem lineItem = new LineItem();
            lineItem.setOrderId(orderId);
            lineItem.setItemId(cartItem.getItemId());
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setUnitPrice(cartItem.getListPrice());
            lineItem.setLineNumber(maxLineNumber+1);
            lineItemMapper.insert(lineItem);
        }
    }

    @Override
    public void insertOrderStatus(int orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setStatus(0);
        orderStatus.setTimestamp(new java.util.Date());
        int lineNumber = ((List<CartVO>) session.getAttribute("cartList")).size();
        orderStatus.setLineNumber(lineNumber);
        orderStatusMapper.insert(orderStatus);
    }

    @Override
    public int getMaxOrderId() {
        QueryWrapper<Orders> queryWrapper = Wrappers.query();
        queryWrapper.select("max(orderid) as orderid");
        if(ordersMapper.selectOne(queryWrapper) == null) {
            return 0;
        }
        return ordersMapper.selectOne(queryWrapper).getOrderId();
    }

    @Override
    public OrderStatus getOrderStatus(int orderId) {
        return orderStatusMapper.selectOne(new QueryWrapper<OrderStatus>().eq("orderid", orderId));
    }

    @Override
    public Orders getOrder(int orderId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderid", orderId);
        return ordersMapper.selectOne(queryWrapper);
    }

    @Override
    public List<LineItem> getLineItems(int orderId) {
        QueryWrapper<LineItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderid", orderId);
        queryWrapper.eq("deleted", 0);
        return lineItemMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteOrder(int orderId) {
        //删除订单项
        List<LineItem> lineItems = getLineItems(orderId);
        for (LineItem lineItem : lineItems) {
            lineItem.setDeleted(1);
            QueryWrapper<LineItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("orderid", lineItem.getOrderId());
            queryWrapper.eq("linenum", lineItem.getLineNumber());
            lineItemMapper.update(lineItem,queryWrapper);
        }
        //删除订单状态
        OrderStatus orderStatus = getOrderStatus(orderId);
        orderStatus.setDeleted(1);
        orderStatusMapper.update(orderStatus, new QueryWrapper<OrderStatus>().eq("orderid", orderId));
        //删除订单
        Orders order = getOrder(orderId);
        order.setDeleted(1);
        ordersMapper.update(order, new QueryWrapper<Orders>().eq("orderid", orderId));
    }

    @Override
    public void shipOrder(int orderId) {
        // 更新订单状态为已发货（status = 2）
        OrderStatus orderStatus = getOrderStatus(orderId);
        if (orderStatus != null) {
            orderStatus.setStatus(2);
            orderStatus.setTimestamp(new java.util.Date());
            orderStatusMapper.update(orderStatus, new QueryWrapper<OrderStatus>().eq("orderid", orderId));
        }
    }

    @Override
    public List<Orders> searchOrders(String keyword) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", 0);
        if (keyword != null && !keyword.isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            queryWrapper.and(wrapper -> wrapper
                .apply("LOWER(orderid) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(userid) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(billtofirstname) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(billtolastname) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(shiptofirstname) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(shiptolastname) LIKE {0}", "%" + lowerKeyword + "%")
            );
        }
        queryWrapper.orderByDesc("orderid");
        return ordersMapper.selectList(queryWrapper);
    }
}
