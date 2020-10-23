package com.twuc.shopping.controller;

import com.twuc.shopping.domin.Order;
import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/order")
  public ResponseEntity saveOrder(@RequestBody CommodityEntity commodity) {
    orderService.save(commodity);
    return ResponseEntity.created(null).build();
  }

  @PostMapping("/orders")
  public ResponseEntity saveOrders(@RequestBody List<CommodityEntity> commodities) {
    orderService.save(commodities);
    return ResponseEntity.created(null).build();
  }

  @GetMapping("/orders")
  public ResponseEntity<List<OrderEntity>> findAll() {
    List<OrderEntity> allOrders = orderService.findAll();
    return ResponseEntity.ok(allOrders);
  }

  @DeleteMapping("/order")
  public ResponseEntity deleteOrder(@RequestBody CommodityEntity commodity) {
    boolean isDeleteSuccess = orderService.delete(commodity);
    return isDeleteSuccess ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
  }



  private OrderEntity convertOrderToOrderEntity(Order order) {
    return OrderEntity.builder()
        .commodity(order.getCommodity())
        .count(order.getCount())
        .id(order.getId())
        .build();
  }
}
