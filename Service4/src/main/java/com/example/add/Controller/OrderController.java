package com.example.add.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.add.service.OrderService;
import com.example.entity.Orders;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get all orders and show in the JSP page
    @GetMapping
    public String getAllOrders(Model model) {
        List<Orders> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);  // Add orders to model for displaying in JSP
        return "orders";  // Return the view name for the JSP page
    }

    // Get filtered orders based on query parameters and show in the JSP page
    @GetMapping("/filter")
    public String getFilteredOrders(@RequestParam(required = false) Date date,
                                     @RequestParam(required = false) String productName,
                                     @RequestParam(required = false) String buyerName,
                                     Model model) {
        // Handle filtering logic even if some parameters are missing
        List<Orders> orders = orderService.getFilteredOrders(date, productName, buyerName);
        model.addAttribute("orders", orders);  // Add filtered orders to model
        return "orders";  // Return the view name for the JSP page
    }

    // Update order status
    @PostMapping("/{orderId}/status")
    public String updateOrderStatus(@PathVariable Long orderId, 
                                    @RequestParam String status, 
                                    Model model) {
        // Update order status in the service layer
        orderService.updateOrderStatus(orderId, status);

        // Redirect to the orders page after status update
        return "redirect:/admin/orders";  // Correct redirect path to orders page
    }

}
