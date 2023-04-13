package com.mssistemamovimientosbancarios.service;

import java.util.List;

import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.request.MovimientoRequest;
import com.mssistemamovimientosbancarios.entity.Movimiento;

public interface MovimientoService {
	public List<Movimiento> getMovimientos();
	public GenericBooleanResponse registrarMovimiento(MovimientoRequest movimientoRequest);
}