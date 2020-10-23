package com.twuc.shopping.service;

import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {

  private final OrderRepository orderRepo;

  public OrderService(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  public void save(CommodityEntity commodity) {
    Optional<OrderEntity> orderByCommodityId = orderRepo.findByCommodityId(commodity.getId());

    if (orderByCommodityId.isPresent()) {
      orderByCommodityId.get().setCount(orderByCommodityId.get().getCount() + 1);
      orderRepo.save(orderByCommodityId.get());
    } else {
      OrderEntity newOrder = OrderEntity
          .builder()
          .orderId(generateOrderId())
          .commodity(commodity)
          .count(1)
          .build();
      orderRepo.save(newOrder);
    }
  }

  public void save(CommodityEntity commodity, String orderId) {
    Optional<OrderEntity> orderByCommodityId = orderRepo.findByCommodityId(commodity.getId());

    if (orderByCommodityId.isPresent()) {
      orderByCommodityId.get().setCount(orderByCommodityId.get().getCount() + 1);
      orderRepo.save(orderByCommodityId.get());
    } else {
      OrderEntity newOrder = OrderEntity
          .builder()
          .orderId(orderId)
          .commodity(commodity)
          .count(1)
          .build();
      orderRepo.save(newOrder);
    }
  }

  public void save(List<CommodityEntity> commodities) {
    String orderId = generateOrderId();
    commodities.forEach(commodity -> {
      save(commodity, orderId);
    });
  }

  private String generateOrderId() {
    String val = "";
    Random random = new Random();
    for (int i = 0; i < 11; i++) {
      val += String.valueOf(random.nextInt(10));
    }
    return val;
  }

  public List<OrderEntity> findAll() {
    return orderRepo.findAll();
  }

  @Transactional
  public boolean delete(CommodityEntity commodity) {
    Optional<OrderEntity> orderByCommodityId = orderRepo.findByCommodityId(commodity.getId());
    if (orderByCommodityId.isPresent()) {
      orderRepo.deleteByCommodityId(commodity.getId());
      return true;
    }
    return false;
  }
}
