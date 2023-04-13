package com.mssistemamovimientosbancarios.service;

import java.util.List;
import com.mssistemamovimientosbancarios.dto.FechasUsuarioDTO;

public interface ReporteService {
	public List<FechasUsuarioDTO> getReportePorFechasPorUsuario(String fechainicio, String fechafin, Integer clienteid);
}