package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.Vo.CartVO;
import org.csu.petstore.entity.Account;
import org.csu.petstore.entity.LineItem;
import org.csu.petstore.entity.OrderStatus;
import org.csu.petstore.entity.Orders;
import org.csu.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpSession session;

    @GetMapping("/orders")
    public String manageOrders(@RequestParam(required = false) String userId,
                               @RequestParam(required = false) String keyword, Model model) {
        if(session.getAttribute("loginAccount")==null) {
            return "redirect:/account/signOnForm";
        }
        List<Orders> orders;

        // If keyword is provided, search orders
        if (keyword != null && !keyword.isEmpty()) {
            orders = orderService.searchOrders(keyword);
            model.addAttribute("keyword", keyword);
        } else if (userId != null && !userId.isEmpty()) {
            // If userId is provided, filter orders by user
            orders = orderService.getAccountOrders(userId);
            model.addAttribute("filterUserId", userId);
        } else {
            orders = orderService.getAllOrders();
        }

        // 为每个订单加载订单项和状态信息
        for (Orders order : orders) {
            List<LineItem> lineItems = orderService.getLineItems(order.getOrderId());
            OrderStatus orderStatus = orderService.getOrderStatus(order.getOrderId());

            // 设置订单状态
            if (orderStatus != null) {
                order.setOrderStatus(orderStatus.getStatus());
            }

            // 设置订单项列表
            order.setLineItems(lineItems);
        }

        model.addAttribute("orders", orders);
        return "admin/order/orders";
    }

    @GetMapping("/orders/details")
    public String viewOrderDetails(@RequestParam int orderId,
                                   @RequestParam(required = false) String userId,
                                   Model model) {
        Orders order = orderService.getOrder(orderId);
        List<LineItem> lineItems = orderService.getLineItems(orderId);
        OrderStatus orderStatus = orderService.getOrderStatus(orderId);

        if (order != null && orderStatus != null) {
            order.setOrderStatus(orderStatus.getStatus());
        }

        model.addAttribute("order", order);
        model.addAttribute("lineItems", lineItems);
        model.addAttribute("orderStatus", orderStatus);

        // Pass userId if provided
        if (userId != null && !userId.isEmpty()) {
            model.addAttribute("filterUserId", userId);
        }

        return "admin/order/orderDetails";
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@RequestParam int orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/order/orders";
    }

    @PostMapping("/orders/ship")
    public String shipOrder(@RequestParam int orderId) {
        orderService.shipOrder(orderId);
        return "redirect:/order/orders";
    }
}
