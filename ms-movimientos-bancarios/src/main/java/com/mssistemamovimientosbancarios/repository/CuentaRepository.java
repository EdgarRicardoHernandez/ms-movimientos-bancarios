package com.mssistemamovimientosbancarios.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mssistemamovimientosbancarios.entity.Cuenta;


@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	@Query(nativeQuery = true, value = "SELECT cuentaid, entidadid, clienteid, tipo, numero, saldoinicial, estado\n"
			+ "	FROM cuentas")
	public List<Cuenta> getCuentas();
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO cuentas \n"
			+ "                            (entidadid, clienteid, tipo, numero, saldoinicial, estado) \n"
			+ "       VALUES (?1, (select clienteid from clientes where personaid = (select personaid from personas where nombre = ?6)), ?3, ?2, ?4, ?5)")
	public int registrarCuenta(Integer entidad, String numero, String tipo, Integer saldoinicial, Boolean estado, String nombre);
	
	@Query(nativeQuery = true, value = "SELECT saldoinicial\n"
			+ "	FROM cuentas WHERE numero = ?1")
	public Integer getSaldoCuenta(String numero);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE cuentas \n"
			+ "                            SET saldoinicial = ?2 \n"
			+ "       WHERE numero = ?1")
	public int actualizarSaldoCuenta(String numero, Integer valor);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE cuentas \n"
			+ "     SET entidadid=?1, tipo=?3, saldoinicial=?4, estado=?5 \n"
			+ "     WHERE clienteid = (select clienteid from clientes where personaid = (select personaid from personas where nombre = ?6)) \n"
			+ "      AND  numero=?2")
	public int actualizarCuenta(Integer entidad, String numero, String tipo, Integer saldoinicial, Boolean estado, String nombre);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM cuentas \n"
			+ "       WHERE numero = ?1 ")
	public int eliminarCuentaPorNumero(String numero);
}
