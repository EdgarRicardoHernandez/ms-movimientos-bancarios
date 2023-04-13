package com.mssistemamovimientosbancarios.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mssistemamovimientosbancarios.entity.Movimiento;


@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	@Query(nativeQuery = true, value = "SELECT movimientoid, entidadid, cuentaid, tipo, fecha, valor, saldoinicial\n"
			+ "	FROM movimientos")
	public List<Movimiento> getMovimientos();
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO movimientos \r\n"
			+ "                            (entidadid, cuentaid, tipo, fecha, valor, saldoinicial) \r\n"
			+ "       VALUES (?2, (select cuentaid from cuentas where numero = ?1), ?3, (select current_timestamp), ?4, (select saldoinicial from cuentas c where c.numero = ?1))")
	public int registrarMovimiento(String numero, Integer entidad, String tipo, Integer valor);
}
