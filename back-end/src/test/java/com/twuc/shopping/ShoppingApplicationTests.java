package com.twuc.shopping;

import com.twuc.shopping.controller.CommodityController;
import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.repository.CommodityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShoppingApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	CommodityController commodityController;

	@Autowired
	CommodityRepository commodityRepo;

	@BeforeEach
	void intDataBase() {
		commodityRepo.save(CommodityEntity.builder().name("可乐1").price(1).imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐2").price(1).imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐3").price(1).imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐4").price(1).imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐5").price(1).imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐6").price(1).imgUrl("./img.png").build());
	}

	@Test
	void shouldFindAll() throws Exception {
		mockMvc.perform(get("/commodities"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(6)));
	}
}
