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
public class CuentaRequest {

    @NotBlank(message = "Campo requerido numero no encontrado")
    @NotNull(message = "numero no puede ser nulo")
    private String numero;
    
    @NotBlank(message = "Campo requerido tipocuenta no encontrado")
    @NotNull(message = "tipocuenta no puede ser nulo")
    private String tipocuenta;
    
    @NotNull(message = "saldoinicial no puede ser nulo")
    private Integer saldoinicial;
    
    @NotNull(message = "estado no puede ser nulo")
    private Boolean estado;
    
    @NotBlank(message = "Campo requerido nombre no encontrado")
    @NotNull(message = "nombre no puede ser nulo")
    private String nombre;
    
}