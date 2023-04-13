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
@Table(schema = "public", name = "clientes")
@Data
public class Cliente implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4833269933632790026L;

	@Id
	@Column
	private Long clienteid;

	@Column
	private Long personaid;

	@Column
	private String contrasenia;
	
	@Column
	private Boolean estado;

}
