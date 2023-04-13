package com.mssistemamovimientosbancarios.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.request.MovimientoRequest;
import com.mssistemamovimientosbancarios.entity.Movimiento;
import com.mssistemamovimientosbancarios.repository.CuentaRepository;
import com.mssistemamovimientosbancarios.repository.MovimientoRepository;
import com.mssistemamovimientosbancarios.service.MovimientoService;
import com.mssistemamovimientosbancarios.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public List<Movimiento> getMovimientos() {
		return movimientoRepository.getMovimientos();
	}

	@Override
	public GenericBooleanResponse registrarMovimiento(MovimientoRequest movimientoRequest) {
		log.info("Iniciando registrarMovimiento MovimientoRequest: {} ", movimientoRequest);
		Integer valor = movimientoRequest.getValor();
		Integer saldo = cuentaRepository.getSaldoCuenta(movimientoRequest.getNumerocuenta());
		if (movimientoRequest.getTipomovimiento().equalsIgnoreCase(Constants.TIPO_MOVIMIENTO_RETIRO)) {
			valor = movimientoRequest.getValor() * -1; 
			log.info("registrarMovimiento  saldo: {} ", saldo);
			if (saldo.equals(0)) {
				return GenericBooleanResponse.builder().booleanValue(false).message(movimientoRequest.getNumerocuenta().concat(" ").concat(Constants.SALDO_NO_DISPONIBLE)).errorCode(Constants.KO_ERROR_CODE).build();
			} else if (saldo < movimientoRequest.getValor()) {
				return GenericBooleanResponse.builder().booleanValue(false).message(movimientoRequest.getNumerocuenta().concat(" ").concat(Constants.SALDO_INSUFIENTE)).errorCode(Constants.KO_ERROR_CODE).build();
			}
		}
		Integer resultMovimientos = movimientoRepository.registrarMovimiento(movimientoRequest.getNumerocuenta(),Constants.ENTIDAD_BANCO_PICHINCHA, movimientoRequest.getTipomovimiento(), valor);
		if (resultMovimientos==1) {
			Integer saldoActual = saldo + valor;
			Integer resultActualizarSaldo = cuentaRepository.actualizarSaldoCuenta(movimientoRequest.getNumerocuenta(), saldoActual);
			return GenericBooleanResponse.builder().booleanValue(true).message(movimientoRequest.getNumerocuenta().concat(" ").concat(Constants.REGISTRO_MOVIMIENTO_OK_MSG)).errorCode(Constants.OK_ERROR_CODE).build();
		}else {
			return GenericBooleanResponse.builder().booleanValue(false).message(movimientoRequest.getNumerocuenta().concat(" ").concat(Constants.REGISTRO_MOVIMIENTO_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
		
	}

}
