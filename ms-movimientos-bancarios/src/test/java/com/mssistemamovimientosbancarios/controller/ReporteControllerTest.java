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

import com.mssistemamovimientosbancarios.service.impl.ReporteServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReporteController.class)
class ReporteControllerTest {
	
	String PATH = "/api/reportes";

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReporteServiceImpl mockService;
	
	private MockHttpServletResponse executeGet(String context) throws Exception {
		final MockHttpServletResponse response = mockMvc.perform(get(PATH + context).param("fechainicio", "11/4/2023")
				.param("fechafin", "12/4/2023").param("clienteid", "2").accept(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();
		return response;
	}
	
	@Test
	void getReportePorFechasPorUsuario() throws Exception {
		MockHttpServletResponse response = executeGet("/ReportePorFechasPorUsuario");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}