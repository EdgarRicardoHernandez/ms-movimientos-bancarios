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
@Table(schema = "public", name = "entidades")
@Data
public class Entidad implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4833269933632790028L;

	@Id
	@Column
	private Long entidadid;

	@Column
	private String nombre;
	
}
