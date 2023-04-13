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
public class ClienteRequest {

    @NotBlank(message = "Campo requerido nombre no encontrado")
    @NotNull(message = "nombre no puede ser nulo")
    private String nombre;
    
    @NotNull(message = "genero no puede ser nulo")
    private String genero;
    
    @NotNull(message = "edad no puede ser nulo")
    private String edad;
   
    @NotNull(message = "identificacion no puede ser nulo")
    private String identificacion;
    
    @NotBlank(message = "Campo requerido direccion no encontrado")
    @NotNull(message = "direccion no puede ser nulo")
    private String direccion;
    
    @NotBlank(message = "Campo requerido telefono no encontrado")
    @NotNull(message = "telefono no puede ser nulo")
    private String telefono;
    
    @NotBlank(message = "Campo requerido contrasenia no encontrado")
    @NotNull(message = "contrasenia no puede ser nulo")
    private String contrasenia;
    
    @NotNull(message = "estado no puede ser nulo")
    private Boolean estado;
}