package com.mssistemamovimientosbancarios.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoRequest {

    @NotBlank(message = "Campo requerido numerocuenta no encontrado")
    @NotNull(message = "numerocuenta no puede ser nulo")
    private String numerocuenta;
    
    @NotBlank(message = "Campo requerido tipoMovimiento no encontrado")
    @NotNull(message = "tipomovimiento no puede ser nulo")
    private String tipomovimiento;
    
    @NotNull(message = "saldoinicial no puede ser nulo")
    private Integer valor;
 
}