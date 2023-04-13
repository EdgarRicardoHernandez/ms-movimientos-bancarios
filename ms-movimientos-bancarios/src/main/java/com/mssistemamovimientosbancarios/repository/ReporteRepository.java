package com.mssistemamovimientosbancarios.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mssistemamovimientosbancarios.dto.FechasUsuarioDTO;
import com.mssistemamovimientosbancarios.entity.Movimiento;


@Repository
public interface ReporteRepository extends JpaRepository<Movimiento, Long> {

	@Query(nativeQuery = true, value = "select to_char(m.fecha, 'DD/MM/YYYY HH:MI:SS') fecha, p.nombre, c.numero as numerocuenta, m. tipo, m.saldoinicial, c.estado, m.valor as movimiento, c.saldoinicial as saldodisponible \n"
			+ "	from movimientos m, cuentas c, clientes cl, personas p\n"
			+ "	where m.cuentaid = c.cuentaid	\n"
			+ "	 and c.clienteid = cl.clienteid \n"
			+ "	 and cl.personaid = p.personaid \n"
			+ "	 and c.clienteid = ?3 \n"
			+ "	 and to_char(m.fecha, 'DD/MM/YYYY HH:MI:SS') >= ?1 and to_char(m.fecha, 'DD/MM/YYYY HH:MI:SS') <= ?2")
	public List<FechasUsuarioDTO> getReportePorFechasPorUsuario(String fechainicio, String fechafin, Integer clienteid);
}
