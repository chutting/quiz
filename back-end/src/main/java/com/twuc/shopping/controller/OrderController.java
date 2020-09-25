package com.twuc.shopping.controller;

import com.twuc.shopping.domin.Order;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/order")
  public ResponseEntity saveOrder(@RequestBody Order order) {
    orderService.save(convertOrderToOrderEntity(order));
    return ResponseEntity.created(null).build();
  }

  private OrderEntity convertOrderToOrderEntity(Order order) {
    return OrderEntity.builder()
        .commodity(order.getCommodity())
        .count(order.getCount())
        .build();
  }
}
