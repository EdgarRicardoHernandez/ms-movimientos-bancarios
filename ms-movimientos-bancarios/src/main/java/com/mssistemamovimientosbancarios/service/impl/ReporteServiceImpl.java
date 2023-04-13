package com.mssistemamovimientosbancarios.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mssistemamovimientosbancarios.dto.FechasUsuarioDTO;
import com.mssistemamovimientosbancarios.repository.ReporteRepository;
import com.mssistemamovimientosbancarios.service.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	private ReporteRepository reporteRepository;

	@Override
	public List<FechasUsuarioDTO> getReportePorFechasPorUsuario(String fechainicio, String fechafin, Integer clienteid) {
		return reporteRepository.getReportePorFechasPorUsuario(fechainicio, fechafin, clienteid);
	}


}
