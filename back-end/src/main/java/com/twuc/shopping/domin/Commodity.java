package com.twuc.shopping.domin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commodity {
  private int id;
  private String name;
  private int price;
  private String imgUrl;
  private String unit;
}
