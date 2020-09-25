package com.twuc.shopping.service;

import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRep;

  public OrderService(OrderRepository orderRep) {
    this.orderRep = orderRep;
  }

  public void save(OrderEntity order) {
    orderRep.save(order);
  }
}
