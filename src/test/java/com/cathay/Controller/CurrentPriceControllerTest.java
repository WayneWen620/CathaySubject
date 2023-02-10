package com.cathay.Controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrentPriceControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	@DisplayName("呼叫api測試")
	public void test_getApiData() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCoindeskAPI");

		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is(200))
				.andExpect(jsonPath("$.disclaimer", notNullValue())).andExpect(jsonPath("$.chartName", notNullValue()))
				.andExpect(jsonPath("$.time", notNullValue())).andExpect(jsonPath("$.bpi", notNullValue())).andReturn();

		String body = result.getResponse().getContentAsString();
		System.out.println("返回的response body:" + body);
	}

	@Test
	@DisplayName("呼叫api,並提供新版api測試")
	public void test_getApiChangeData() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/V1/getCoindeskAPI");

		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is(200))
				.andExpect(jsonPath("$.currentDataList", hasSize(3)))
				.andExpect(jsonPath("$.currentDataList", notNullValue()))
				.andExpect(jsonPath("$.updated", notNullValue())).andReturn();

		String body = result.getResponse().getContentAsString();
		System.out.println("返回的response body:" + body);
	}

	@Transactional
	@Test
	@DisplayName("新增幣別測試")
	public void test_craete() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("currency", "HKD");
		map.put("currencyName", "港幣");
		map.put("updateBy", "Tets01");
		map.put("rate", 123.21);
		map.put("memo", "港幣測試");

		JSONObject json = new JSONObject(map);
		RequestBuilder builder = MockMvcRequestBuilders.post("/create/currency").contentType(MediaType.APPLICATION_JSON)
				.content(json.toString());

		mockMvc.perform(builder).andDo(print()).andExpect(jsonPath("$.currency", equalTo("HKD")))
				.andExpect(jsonPath("$.currencyName", equalTo("港幣"))).andExpect(jsonPath("$.rate", equalTo(123.21)))
				.andExpect(jsonPath("$.memo", equalTo("港幣測試"))).andExpect(jsonPath("$.updateBy", equalTo("Tets01")))
				.andExpect(jsonPath("$.createdBy", equalTo("Tets01")))
				.andExpect(jsonPath("$.creationDate", notNullValue()))
				.andExpect(jsonPath("$.updateDate", notNullValue())).andExpect(status().is(201));

	}
	
	@Test
	@DisplayName("讀取幣別測試")
	public void test_read() throws Exception {
		created_data();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/{currency}", "VND");

		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is(200)).andReturn();

		String body = result.getResponse().getContentAsString();
		System.out.println("返回的response body:" + body);
	}

	@Test
	@DisplayName("更新幣別測試")
	public void test_updateCurrentPrice() throws Exception {
		created_data();
		Map<String, Object>map =new HashMap<>();
		map.put("currency", "VND");
		map.put("currencyName", "越南盾");
		map.put("updateBy", "TetsA");
		map.put("rate", 555);
		map.put("memo", "越南盾測試");

		JSONObject json =  new JSONObject(map);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/update/currency/{currency}", "VND")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toString());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andDo(print());

    }
	
	
	@Transactional
	@Test
	@DisplayName("刪除幣別測試")
	public void test_del() throws Exception {
		created_data();
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/del/currency/{currency}", "VND");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
		
	}
	private void created_data() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("currency", "VND");
		map.put("currencyName", "越南盾");
		map.put("updateBy", "Tets123");
		map.put("rate", 111);
		map.put("memo", "越南盾測試");

		JSONObject json = new JSONObject(map);
		RequestBuilder builder = MockMvcRequestBuilders.post("/create/currency").contentType(MediaType.APPLICATION_JSON)
				.content(json.toString());

		mockMvc.perform(builder).andExpect(status().is(201));
	}

}
