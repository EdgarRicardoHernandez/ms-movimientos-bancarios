package com.mssistemamovimientosbancarios.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.mssistemamovimientosbancarios.service.impl.CuentaServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CuentaController.class)
class CuentaControllerTest {
	
	String PATH = "/api/cuentas";

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CuentaServiceImpl mockService;
	
	private MockHttpServletResponse executeGet(String context) throws Exception {
		final MockHttpServletResponse response = mockMvc.perform(get(PATH + context).accept(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();
		return response;
	}
	
	@Test
	void getCuentas() throws Exception {
		MockHttpServletResponse response = executeGet("/get");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}