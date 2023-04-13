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
public class Movimiento implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4833269933632790029L;

	@Id
	@Column
	private Long movimientoid;
	
	@Column
	private Long entidadid;
	
	@Column
	private Long cuentaid;

	@Column
	private String tipo;
	
	@Column
	private String fecha;
	
	@Column
	private Long valor;
	
	@Column
	private Long saldoinicial;
	
}
