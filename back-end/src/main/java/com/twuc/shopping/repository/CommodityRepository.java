package com.twuc.shopping.repository;

import com.twuc.shopping.entity.CommodityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends CrudRepository<CommodityEntity, Integer> {
  @Override
  List<CommodityEntity> findAll();
}
