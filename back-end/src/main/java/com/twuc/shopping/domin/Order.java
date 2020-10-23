package com.twuc.shopping.domin;

import com.twuc.shopping.entity.CommodityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

  private int id = 0;

  private CommodityEntity commodity;

  private int count;
}
