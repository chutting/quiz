package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.controller.CommodityController;
import com.twuc.shopping.domin.Commodity;
import com.twuc.shopping.domin.Order;
import com.twuc.shopping.entity.CommodityEntity;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.CommodityRepository;
import com.twuc.shopping.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	@Autowired
	OrderRepository orderRepo;

	@BeforeEach
	void intDataBase() {
		commodityRepo.save(CommodityEntity.builder().name("可乐1").price(1).unit("瓶").imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐2").price(1).unit("瓶").imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐3").price(1).unit("瓶").imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐4").price(1).unit("瓶").imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐5").price(1).unit("瓶").imgUrl("./img.png").build());
		commodityRepo.save(CommodityEntity.builder().name("可乐6").price(1).unit("瓶").imgUrl("./img.png").build());
	}

	@Test
	void shouldFindAll() throws Exception {
		mockMvc.perform(get("/commodities"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(6)));
	}

	@Test
	void shouldCouldAddOrder() throws Exception {

		CommodityEntity commodityEntity  = commodityRepo.findById(1).get();

		Order order = Order.builder()
				.commodity(commodityEntity)
				.build();

		mockMvc.perform(post("/order")
				.content(convertOrderToJsonString(order))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());

		assertEquals(1, orderRepo.findAll().size());

		mockMvc.perform(post("/order")
				.content(convertOrderToJsonString(order))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());

		assertEquals(1, orderRepo.findAll().size());
		assertEquals(2, orderRepo.findByCommodityId(1).get().getCount());
	}

	@Test
	void shouldGetAllOrders() throws Exception{
		CommodityEntity commodityEntity1  = commodityRepo.findById(1).get();
		CommodityEntity commodityEntity2  = commodityRepo.findById(2).get();


		OrderEntity order1 = OrderEntity.builder()
				.count(2)
				.commodity(commodityEntity1)
				.build();

		OrderEntity order2 = OrderEntity.builder()
				.commodity(commodityEntity2)
				.count(7)
				.build();

		orderRepo.save(order1);
		orderRepo.save(order2);

		mockMvc.perform(get("/orders"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void shouldDeleteOrder() throws Exception{
		CommodityEntity commodityEntity1  = commodityRepo.findById(1).get();
		CommodityEntity commodityEntity2  = commodityRepo.findById(2).get();


		OrderEntity order1 = OrderEntity.builder()
				.count(2)
				.commodity(commodityEntity1)
				.build();

		OrderEntity order2 = OrderEntity.builder()
				.commodity(commodityEntity2)
				.count(7)
				.build();

		orderRepo.save(order1);
		orderRepo.save(order2);

		mockMvc.perform(delete("/order")
				.content(convertCommodityEntityToJsonString(commodityEntity1))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNoContent());

		assertEquals(1, orderRepo.findAll().size());
	}

//	private Commodity convertCommodityEntityToCommodity(CommodityEntity commodityEntity) {
//		return Commodity.builder()
//				.name(commodityEntity.getName())
//				.price(commodityEntity.getPrice())
//				.unit(commodityEntity.getUnit())
//				.imgUrl(commodityEntity.getImgUrl())
//				.id(commodityEntity.getId())
//				.build();
//	}

	private String convertOrderToJsonString(Order order) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(order);

//		StringBuilder stringBuilder =new StringBuilder(jsonString.substring(0, jsonString.length() - 1));
//		String userJson = objectMapper.writeValueAsString(user);
//		StringBuilder output = stringBuilder.append(",\"user\":").append(userJson).append("}");
		return jsonString;
	}

	private String convertCommodityEntityToJsonString(CommodityEntity commodity) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(commodity);

//		StringBuilder stringBuilder =new StringBuilder(jsonString.substring(0, jsonString.length() - 1));
//		String userJson = objectMapper.writeValueAsString(user);
//		StringBuilder output = stringBuilder.append(",\"user\":").append(userJson).append("}");
		return jsonString;
	}
}
