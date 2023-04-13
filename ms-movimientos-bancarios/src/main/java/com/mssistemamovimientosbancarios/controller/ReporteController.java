package com.mssistemamovimientosbancarios.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.error.ErrorMessage;
import com.mssistemamovimientosbancarios.dto.FechasUsuarioDTO;
import com.mssistemamovimientosbancarios.service.ReporteService;
import com.mssistemamovimientosbancarios.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
	
	@Autowired
	private ReporteService reporteService;
	
	@Operation(summary = "Consulta el listado de movimientos registrados en el sistema de movimientos bancarios por rago de fechas y por usuarios.")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Consulta realizada con Exito",
	                  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericBooleanResponse.class))}),
	@ApiResponse(responseCode = "400", description = "Bad request",
	                  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
	@ApiResponse(responseCode = "404", description = "Transacción no encontrada",
	                  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
	@ApiResponse(responseCode = "500", description = "Error interno de servicio",
	                  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))})
	})
	@GetMapping(value="/ReportePorFechasPorUsuario")
	@ResponseStatus(HttpStatus.OK)
	public GenericBooleanResponse getReportePorFechasPorUsuario(@RequestParam(value = "fechainicio") @NotBlank(message = "Campo requerido fechainicio no encontrado") String fechainicio, @RequestParam(value = "fechafin") @NotBlank(message = "Campo requerido fechafin no encontrado") String fechafin,@RequestParam(value = "clienteid") @NotNull(message = "Campo requerido clienteid no encontrado") Integer clienteid) {	
		log.info("Ingresando al controlador de reporte getReportePorFechasPorUsuario() metodo, fechainicio = {} - fechafin = {} - clienteid = {}  ", fechainicio, fechafin, clienteid);
		List<FechasUsuarioDTO> response = reporteService.getReportePorFechasPorUsuario(fechainicio, fechafin, clienteid);
		log.info("Finalizando entrada al controlador de reporte getReportePorFechasPorUsuario() metodo, fechainicio = {} - fechafin = {} - clienteid = {} ", fechainicio, fechafin, clienteid);
		return GenericBooleanResponse.builder().booleanValue(true).data(response).errorCode(Constants.OK_ERROR_CODE).message(Constants.CONSULTA_OK).build();
	}
	
}