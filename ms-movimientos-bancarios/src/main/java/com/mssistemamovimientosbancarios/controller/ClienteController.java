package com.mssistemamovimientosbancarios.controller;

import java.util.List;

import javax.validation.Valid;
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
import com.mssistemamovimientosbancarios.domain.request.ClienteRequest;
import com.mssistemamovimientosbancarios.entity.Cliente;
import com.mssistemamovimientosbancarios.service.ClienteService;
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
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Operation(summary = "Consulta los clientes registrados en el sistema de movimientos bancarios.")
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
	public GenericBooleanResponse getClientes() {	
		log.info("Ingresando al controlador de cliente getClientes() metodo.");
		List<Cliente> response = clienteService.getClientes();
		log.info("Finalizando entrada al controlador de cliente getClientes() metodo.");
		return GenericBooleanResponse.builder().booleanValue(true).data(response).errorCode(Constants.OK_ERROR_CODE).message(Constants.CONSULTA_OK).build();
	}
	
	@Operation(summary = "Registra un usuario o cliente en el sistema de movimientos bancarios. ")
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
    public GenericBooleanResponse registrarUsuario(@Valid @RequestBody  ClienteRequest clienteRequest) {
        log.info("Inicio de proceso para registrar un usuario o cliente. registrarUsuario, ClienteRequest: {} ", clienteRequest);
        return clienteService.registrarUsuario(clienteRequest);
    }
	
	@Operation(summary = "Modifica un usuario o cliente en el sistema de movimientos bancarios. ")
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
	@PutMapping	(value="/actualizar")
    @ResponseStatus(HttpStatus.OK)
    public GenericBooleanResponse actualizarUsuario(@Valid @RequestBody  ClienteRequest clienteRequest) {
        log.info("Inicio de proceso para actualizar un usuario o cliente. actualizarUsuario, ClienteRequest: {} ", clienteRequest);
        return clienteService.actualizarUsuario(clienteRequest);
    }
	
	@Operation(summary = "Elimina un usuario o cliente en el sistema de movimientos bancarios por clienteid y por personaid. ")
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
	public GenericBooleanResponse eliminarUsuarioPorClienteidPorPersonaId(@RequestParam(value = "clienteid") @NotNull(message = "Campo requerido clienteid no encontrado") Integer clienteid, @RequestParam(value = "personaid") @NotNull(message = "Campo requerido personaid no encontrado") Integer personaid) {
	    log.info("Inicio eliminar eliminarUsuarioPorClienteidPorPersonaId, clienteid = {}, personaid = {} ", clienteid, personaid);
	    return clienteService.eliminarUsuarioPorClienteid(clienteid, personaid);
	}
	
}