package com.mssistemamovimientosbancarios.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.request.ClienteRequest;
import com.mssistemamovimientosbancarios.entity.Cliente;
import com.mssistemamovimientosbancarios.repository.ClienteRepository;
import com.mssistemamovimientosbancarios.service.ClienteService;
import com.mssistemamovimientosbancarios.util.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> getClientes() {
		return clienteRepository.getClientes();
	}

	@Override
	public GenericBooleanResponse registrarUsuario(ClienteRequest clienteRequest) {
		log.info("Iniciando registrarUsuario ClienteRequest: {} ", clienteRequest);
		Integer resultPersonas = clienteRepository.registrarPersona(clienteRequest.getNombre(), clienteRequest.getGenero(), clienteRequest.getEdad(), clienteRequest.getIdentificacion(), clienteRequest.getDireccion(), clienteRequest.getTelefono());
		if(resultPersonas==1) {
			Integer resultClientes = clienteRepository.registrarCliente(clienteRequest.getContrasenia(), clienteRequest.getEstado());
			if(resultClientes==1) {
				return GenericBooleanResponse.builder().booleanValue(true).message(clienteRequest.getNombre().concat(" ").concat(Constants.REGISTRO_USUARIO_OK_MSG)).errorCode(Constants.OK_ERROR_CODE).build();
			} else {
				return GenericBooleanResponse.builder().booleanValue(false).message(clienteRequest.getNombre().concat(" ").concat(Constants.REGISTRO_USUARIO_CLIENTES_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
			}
		} else {
			return GenericBooleanResponse.builder().booleanValue(false).message(clienteRequest.getNombre().concat(" ").concat(Constants.REGISTRO_USUARIO_PERSONAS_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
	}
	
	@Override
	public GenericBooleanResponse actualizarUsuario(ClienteRequest clienteRequest) {
		log.info("Iniciando actualizarUsuario ClienteRequest: {} ", clienteRequest);
		Integer resultPersonas = clienteRepository.actualizarPersona(clienteRequest.getNombre(), clienteRequest.getGenero(), clienteRequest.getEdad(), clienteRequest.getIdentificacion(), clienteRequest.getDireccion(), clienteRequest.getTelefono());
		if(resultPersonas==1) {
			Integer resultClientes = clienteRepository.actualizarCliente(clienteRequest.getContrasenia(), clienteRequest.getEstado(), clienteRequest.getNombre());
			if(resultClientes==1) {
				return GenericBooleanResponse.builder().booleanValue(true).message(clienteRequest.getNombre().concat(" ").concat(Constants.ACTUALIZACION_USUARIO_OK_MSG)).errorCode(Constants.OK_ERROR_CODE).build();
			} else {
				return GenericBooleanResponse.builder().booleanValue(false).message(clienteRequest.getNombre().concat(" ").concat(Constants.ACTUALIZACION_USUARIO_CLIENTES_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
			}
		} else {
			return GenericBooleanResponse.builder().booleanValue(false).message(clienteRequest.getNombre().concat(" ").concat(Constants.ACTUALIZACION_USUARIO_PERSONAS_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
	}
	
	@Override
	public GenericBooleanResponse eliminarUsuarioPorClienteid(Integer clienteid, Integer personaid) {
		log.info("Iniciando eliminarUsuarioPorClienteid clienteid: {}, personaid = {} ", clienteid, personaid);
		Integer resultClientes = clienteRepository.eliminarUsuarioPorClienteid(clienteid);
		if(resultClientes==1) {
			Integer resultPersonas = clienteRepository.eliminarUsuarioPorPersonaid(personaid);
			if(resultPersonas==1) {
				return GenericBooleanResponse.builder().booleanValue(true).message(clienteid.toString().concat(" ").concat(personaid.toString().concat(" ").concat(Constants.ELIMINACION_USUARIO_OK_MSG))).errorCode(Constants.OK_ERROR_CODE).build();
			} else {
				return GenericBooleanResponse.builder().booleanValue(false).message(personaid.toString().concat(" ").concat(Constants.ELIMINACION_USUARIO_PERSONAS_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
			}
		} else {
			return GenericBooleanResponse.builder().booleanValue(false).message(clienteid.toString().concat(" ").concat(Constants.ELIMINACION_USUARIO_CLIENTES_KO_MSG)).errorCode(Constants.KO_ERROR_CODE).build();
		}	
	}
}
