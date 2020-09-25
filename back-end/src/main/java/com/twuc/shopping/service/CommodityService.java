package com.twuc.shopping.service;

import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.repository.CommodityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService {

  private final CommodityRepository commodityRepo;

  public CommodityService(CommodityRepository commodityRepo) {
    this.commodityRepo = commodityRepo;
  }

  public List<CommodityEntity> findAll() {
    return commodityRepo.findAll();
  }
}
