package com.twuc.shopping.service;

import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
          .commodity(commodity)
          .count(1)
          .build();
      orderRepo.save(newOrder);
    }
  }

  public void save(List<CommodityEntity> commodities) {
    commodities.forEach(commodity -> {
      save(commodity);
    });
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
