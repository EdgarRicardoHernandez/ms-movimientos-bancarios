package com.mssistemamovimientosbancarios.service;

import java.util.List;

import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.request.CuentaRequest;
import com.mssistemamovimientosbancarios.entity.Cuenta;

public interface CuentaService {
	public List<Cuenta> getCuentas();
	public GenericBooleanResponse registrarCuenta(CuentaRequest cuentaRequest);
	public GenericBooleanResponse actualizarCuenta(CuentaRequest cuentaRequest);
	public GenericBooleanResponse eliminarCuentaPorNumero(String numero);
}