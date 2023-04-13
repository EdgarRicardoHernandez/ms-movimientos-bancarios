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
@Table(schema = "public", name = "cuentas")
@Data
public class Cuenta implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4833269933632790027L;

	@Id
	@Column
	private Long cuentaid;

	@Column
	private Long entidadid;
	
	@Column
	private Long clienteid;

	@Column
	private String tipo;
	
	@Column
	private String numero;
	
	@Column
	private Long saldoinicial;
	
	@Column
	private Boolean estado;

}
