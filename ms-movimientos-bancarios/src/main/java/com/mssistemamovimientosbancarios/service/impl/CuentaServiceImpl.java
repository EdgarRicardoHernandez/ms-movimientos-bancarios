package com.mssistemamovimientosbancarios.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.request.CuentaRequest;
import com.mssistemamovimientosbancarios.entity.Cuenta;
import com.mssistemamovimientosbancarios.repository.CuentaRepository;
import com.mssistemamovimientosbancarios.service.CuentaService;
import com.mssistemamovimientosbancarios.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public List<Cuenta> getCuentas() {
		return cuentaRepository.getCuentas();
	}

	@Override
	public GenericBooleanResponse registrarCuenta(CuentaRequest cuentaRequest) {
		log.info("Iniciando registrarCuenta CuentaRequest: {} ", cuentaRequest);
		Integer resultCuentas = cuentaRepository.registrarCuenta(Constants.ENTIDAD_BANCO_PICHINCHA, cuentaRequest.getNumero(), cuentaRequest.getTipocuenta(), cuentaRequest.getSaldoinicial(), cuentaRequest.getEstado(), cuentaRequest.getNombre());
		if (resultCuentas==1) {
			return GenericBooleanResponse.builder().booleanValue(true).message(cuentaRequest.getNumero().concat(" ").concat(Constants.REGISTRO_CUENTA_OK_MSG)).errorCode(Constants.OK_ERROR_CODE).build();
		}else {
			return GenericBooleanResponse.builder().booleanValue(false).message(cuentaRequest.getNumero().concat(" ").concat(Constants.REGISTRO_CUENTA_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
	}
	
	@Override
	public GenericBooleanResponse actualizarCuenta(CuentaRequest cuentaRequest) {
		log.info("Iniciando actualizarCuenta CuentaRequest: {} ", cuentaRequest);
		Integer resultCuentas = cuentaRepository.actualizarCuenta(Constants.ENTIDAD_BANCO_PICHINCHA, cuentaRequest.getNumero(), cuentaRequest.getTipocuenta(), cuentaRequest.getSaldoinicial(), cuentaRequest.getEstado(), cuentaRequest.getNombre());
		if (resultCuentas==1) {
			return GenericBooleanResponse.builder().booleanValue(true).message(cuentaRequest.getNumero().concat(" ").concat(Constants.ACTUALIZACION_CUENTA_OK_MSG)).errorCode(Constants.OK_ERROR_CODE).build();
		}else {
			return GenericBooleanResponse.builder().booleanValue(false).message(cuentaRequest.getNumero().concat(" ").concat(Constants.ACTUALIZACION_CUENTA_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
	}
	
	@Override
	public GenericBooleanResponse eliminarCuentaPorNumero(String numero) {
		log.info("Iniciando eliminarCuentaPorNumero numero: {} ", numero);
		Integer resultCuentas = cuentaRepository.eliminarCuentaPorNumero(numero);
		if (resultCuentas==1) {
			return GenericBooleanResponse.builder().booleanValue(true).message(numero.concat(" ").concat(Constants.ELIMINACION_CUENTA_OK_MSG)).errorCode(Constants.OK_ERROR_CODE).build();
		}else {
			return GenericBooleanResponse.builder().booleanValue(false).message(numero.concat(" ").concat(Constants.ELIMINACION_CUENTA_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
	}
	
}
