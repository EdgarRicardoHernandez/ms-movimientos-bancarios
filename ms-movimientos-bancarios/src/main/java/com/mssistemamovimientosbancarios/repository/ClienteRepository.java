package com.mssistemamovimientosbancarios.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mssistemamovimientosbancarios.entity.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(nativeQuery = true, value = "SELECT clienteid, personaid, contrasenia, estado\n"
			+ "	FROM clientes")
	public List<Cliente> getClientes();
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO personas \n"
			+ "                            (nombre, genero, edad, identificacion, direccion, telefono) \n"
			+ "       VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
	public int registrarPersona(String nombre, String genero, String edad, String identificacion, String direccion, String telefono);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO clientes \n"
			+ "                            (personaid, contrasenia, estado) \n"
			+ "       VALUES ((select max(personaid) from personas), ?1, ?2)")
	public int registrarCliente(String contrasenia, Boolean edad);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE personas \n"
			+ "       SET genero=?2, edad=?3, identificacion=?4, direccion=?5, telefono=?6 \n"
			+ "       WHERE nombre = ?1")
	public int actualizarPersona(String nombre, String genero, String edad, String identificacion, String direccion, String telefono);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE clientes \n"
			+ "       SET contrasenia = ?1, estado = ?2  \n"
			+ "       WHERE personaid = (select personaid from personas where nombre = ?3) ")
	public int actualizarCliente(String contrasenia, Boolean edad, String nombre);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM clientes \n"
			+ "       WHERE clienteid = ?1 ")
	public int eliminarUsuarioPorClienteid(Integer clienteid);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM personas \n"
			+ "       WHERE personaid = ?1 ")
	public int eliminarUsuarioPorPersonaid(Integer personaid);
}
