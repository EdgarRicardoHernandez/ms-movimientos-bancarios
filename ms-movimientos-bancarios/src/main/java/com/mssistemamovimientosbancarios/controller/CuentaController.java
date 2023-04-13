package com.mssistemamovimientosbancarios.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.error.ErrorMessage;
import com.mssistemamovimientosbancarios.domain.request.CuentaRequest;
import com.mssistemamovimientosbancarios.entity.Cuenta;
import com.mssistemamovimientosbancarios.service.CuentaService;
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
@RequestMapping("/api/cuentas")
public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;
	
	@Operation(summary = "Consulta las cuentas registrados en el sistema de movimientos bancarios.")
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
	@GetMapping(value="/get")
	@ResponseStatus(HttpStatus.OK)
	public GenericBooleanResponse getCuentas() {	
		log.info("Ingresando al controlador de cuenta getCuentas() metodo.");
		List<Cuenta> response = cuentaService.getCuentas();
		log.info("Finalizando entrada al controlador de cuenta getCuentas() metodo.");
		return GenericBooleanResponse.builder().booleanValue(true).data(response).errorCode(Constants.OK_ERROR_CODE).message(Constants.CONSULTA_OK).build();
	}
	
	@Operation(summary = "Registra una cuenta en el sistema de movimientos bancarios. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insertado con éxito",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericBooleanResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
            		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno de servicio",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))})
    })
	@PostMapping(value="/registrar")
    @ResponseStatus(HttpStatus.OK)
    public GenericBooleanResponse registrarCuenta(@Valid @RequestBody  CuentaRequest cuentaRequest) {
        log.info("Inicio de proceso para registrar una cuenta. registrarCuenta, CuentaRequest: {} ", cuentaRequest);
        return cuentaService.registrarCuenta(cuentaRequest);
    }
	
	@Operation(summary = "Registra una cuenta en el sistema de movimientos bancarios. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insertado con éxito",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericBooleanResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
            		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno de servicio",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))})
    })
	@PutMapping(value="/actualizar")
    @ResponseStatus(HttpStatus.OK)
    public GenericBooleanResponse actualizarCuenta(@Valid @RequestBody  CuentaRequest cuentaRequest) {
        log.info("Inicio de proceso para actualizar una cuenta. actualizarCuenta, CuentaRequest: {} ", cuentaRequest);
        return cuentaService.actualizarCuenta(cuentaRequest);
    }
	
	@Operation(summary = "Elimina una cuenta en el sistema de movimientos bancarios por numero de cuenta ")
    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Se ha eliminado la asignación del dispositivo correctamente",
	                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericBooleanResponse.class))}),
	        @ApiResponse(responseCode = "400", description = "Bad request",
	                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))}),
	        @ApiResponse(responseCode = "500", description = "Error interno de servicio",
	                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))})
	})
	@DeleteMapping("/eliminar")
	@ResponseStatus(HttpStatus.OK)
	public GenericBooleanResponse eliminarCuentaPorNumero(@RequestParam(value = "numero") @NotBlank(message = "Campo requerido numero no encontrado") String numero) {
	    log.info("Inicio eliminar eliminarCuentaPorNumero, numero = {} ", numero);
	    return cuentaService.eliminarCuentaPorNumero(numero);
	}
}