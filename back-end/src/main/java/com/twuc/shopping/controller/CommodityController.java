package com.twuc.shopping.controller;

import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.service.CommodityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommodityController {
  private final CommodityService commodityService;

  public CommodityController(CommodityService commodityService) {
    this.commodityService = commodityService;
  }

  @GetMapping("/commodities")
  public ResponseEntity<List<CommodityEntity>> findAll() {
    List<CommodityEntity> result = commodityService.findAll();
    return ResponseEntity.ok(result);
  }

}
