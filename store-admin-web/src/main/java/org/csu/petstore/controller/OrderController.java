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

    @GetMapping("/orders/edit")
    public String editOrder(@RequestParam int orderId,
                            @RequestParam(required = false) String userId,
                            Model model) {
        if(session.getAttribute("loginAccount")==null) {
            return "redirect:/account/signOnForm";
        }
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

        return "admin/order/editOrder";
    }

    @PostMapping("/orders/update")
    public String updateOrder(
            @RequestParam int orderId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String courier,
            @RequestParam(required = false) Integer orderStatus,
            @RequestParam(required = false) String billToFirstName,
            @RequestParam(required = false) String billToLastName,
            @RequestParam(required = false) String billAddress1,
            @RequestParam(required = false) String billAddress2,
            @RequestParam(required = false) String billCity,
            @RequestParam(required = false) String billState,
            @RequestParam(required = false) String billZip,
            @RequestParam(required = false) String billCountry,
            @RequestParam(required = false) String creditCard,
            @RequestParam(required = false) String cardType,
            @RequestParam(required = false) String expiryDate,
            @RequestParam(required = false) String shipToFirstName,
            @RequestParam(required = false) String shipToLastName,
            @RequestParam(required = false) String shipAddress1,
            @RequestParam(required = false) String shipAddress2,
            @RequestParam(required = false) String shipCity,
            @RequestParam(required = false) String shipState,
            @RequestParam(required = false) String shipZip,
            @RequestParam(required = false) String shipCountry,
            Model model) {
        if(session.getAttribute("loginAccount")==null) {
            return "redirect:/account/signOnForm";
        }
        
        try {
            Orders order = orderService.getOrder(orderId);
            if (order == null) {
                model.addAttribute("errorMsg", "Order not found!");
                return "admin/order/editOrder";
            }
            
            // Update order fields
            order.setCourier(courier != null ? courier : "");
            order.setBillToFirstName(billToFirstName != null ? billToFirstName : "");
            order.setBillToLastName(billToLastName != null ? billToLastName : "");
            order.setBillAddress1(billAddress1 != null ? billAddress1 : "");
            order.setBillAddress2(billAddress2 != null ? billAddress2 : "");
            order.setBillCity(billCity != null ? billCity : "");
            order.setBillState(billState != null ? billState : "");
            order.setBillZip(billZip != null ? billZip : "");
            order.setBillCountry(billCountry != null ? billCountry : "");
            order.setCreditCard(creditCard != null ? creditCard : "");
            order.setCardType(cardType != null ? cardType : "");
            order.setExpiryDate(expiryDate != null ? expiryDate : "");
            order.setShipToFirstName(shipToFirstName != null ? shipToFirstName : "");
            order.setShipToLastName(shipToLastName != null ? shipToLastName : "");
            order.setShipAddress1(shipAddress1 != null ? shipAddress1 : "");
            order.setShipAddress2(shipAddress2 != null ? shipAddress2 : "");
            order.setShipCity(shipCity != null ? shipCity : "");
            order.setShipState(shipState != null ? shipState : "");
            order.setShipZip(shipZip != null ? shipZip : "");
            order.setShipCountry(shipCountry != null ? shipCountry : "");
            
            // Set the status to use for update
            int newStatus = orderStatus != null ? orderStatus : (order.getOrderStatus() != 0 ? order.getOrderStatus() : 0);
            
            orderService.updateOrder(order, newStatus);
            model.addAttribute("successMsg", "Order information updated successfully!");
            
            // Reload the order to show updated data
            Orders updatedOrder = orderService.getOrder(orderId);
            List<LineItem> lineItems = orderService.getLineItems(orderId);
            OrderStatus updatedOrderStatus = orderService.getOrderStatus(orderId);
            
            if (updatedOrder != null && updatedOrderStatus != null) {
                updatedOrder.setOrderStatus(updatedOrderStatus.getStatus());
            }
            
            model.addAttribute("order", updatedOrder);
            model.addAttribute("lineItems", lineItems);
            model.addAttribute("orderStatus", updatedOrderStatus);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "Failed to update order information: " + e.getMessage());
            Orders order = orderService.getOrder(orderId);
            List<LineItem> lineItems = orderService.getLineItems(orderId);
            OrderStatus errorOrderStatus = orderService.getOrderStatus(orderId);

            if (order != null && errorOrderStatus != null) {
                order.setOrderStatus(errorOrderStatus.getStatus());
            }
                    
            model.addAttribute("order", order);
            model.addAttribute("lineItems", lineItems);
            model.addAttribute("orderStatus", errorOrderStatus);
        }
        
        return "admin/order/editOrder";
    }
}
