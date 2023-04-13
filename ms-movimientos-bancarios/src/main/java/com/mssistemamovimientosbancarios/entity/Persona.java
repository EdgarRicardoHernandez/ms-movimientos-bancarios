package com.mssistemamovimientosbancarios.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "public", name = "personas")
@Data
public class Persona implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4833269933632790030L;

	@Id
	@Column
	private Long personaid;
	
	@Column
	private String nombre;
	
	@Column
	private String genero;
	
	@Column
	private String edad;
		
	@Column
	private String identificacion;
	
	@Column
	private String direccion;
	
	@Column
	private String telefono;

}
