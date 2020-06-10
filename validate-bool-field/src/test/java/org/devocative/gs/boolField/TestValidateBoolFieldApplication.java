package org.devocative.gs.boolField;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestValidateBoolFieldApplication {
	private static final String BASE_URL = "/api/devices/";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSave() throws Exception {
		for (String str : Arrays.asList("0", "false")) {
			mockMvc
				.perform(post(BASE_URL + "save")
					.content(String.format("{\"name\":\"%1$s\", \"active\":%1$s}", str))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(String.format("DeviceController.DeviceDTO(name=%s, active=false)", str)));
		}

		for (String str : Arrays.asList("1", "true")) {
			mockMvc
				.perform(post(BASE_URL + "save")
					.content(String.format("{\"name\":\"%1$s\", \"active\":%1$s}", str))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(String.format("DeviceController.DeviceDTO(name=%s, active=true)", str)));
		}

		for (String str : Arrays.asList("-1", "2")) {
			mockMvc
				.perform(post(BASE_URL + "save")
					.content(String.format("{\"name\":\"%1$s\", \"active\":%1$s}", str))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		}
	}

	@Test
	void testUpdateAllActiveStatus() throws Exception {

		for (String str : Arrays.asList("0", "false", "False", "FALSE")) {
			mockMvc
				.perform(put(BASE_URL + str))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("false"));
		}

		for (String str : Arrays.asList("1", "true", "True", "TRUE")) {
			mockMvc
				.perform(put(BASE_URL + str))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		}

		for (String str : Arrays.asList("-1", "2", "a", "t", "f")) {
			mockMvc
				.perform(put(BASE_URL + str))
				//.andDo(print())
				.andExpect(status().isBadRequest());
		}
	}
}
