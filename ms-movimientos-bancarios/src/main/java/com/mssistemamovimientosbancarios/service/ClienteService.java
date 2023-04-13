package com.mssistemamovimientosbancarios.service;

import java.util.List;

import com.mssistemamovimientosbancarios.domain.GenericBooleanResponse;
import com.mssistemamovimientosbancarios.domain.request.ClienteRequest;
import com.mssistemamovimientosbancarios.entity.Cliente;

public interface ClienteService {
	public List<Cliente> getClientes();
	public GenericBooleanResponse registrarUsuario(ClienteRequest clienteRequest);
	public GenericBooleanResponse actualizarUsuario(ClienteRequest clienteRequest);
	public GenericBooleanResponse eliminarUsuarioPorClienteid(Integer clienteid, Integer personaid);
}