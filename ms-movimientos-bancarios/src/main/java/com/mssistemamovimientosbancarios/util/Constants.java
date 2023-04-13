package com.mssistemamovimientosbancarios.util;

public class Constants {

	private Constants(){
	}
	
	public static final String LOG_PATTERN_MSG = "ControllerAdvice: {}";
	public static final String REQUIRED_FIELD_PATTERN_MSG = "Campo requerido %s no encontrado";
	public static final String INTERNAL_BD_ERROR = "Error inesperado en la base de datos. Por favor, intente en otro momento";
	public static final String UNIQUE_CONSTRAINT_VIOLATION_BD_ERROR = "Error en base de datos. Llave primaria duplicada";
	public static final int INTERNAL_BD_ERROR_CODE = 1001;
	public static final String INTERNAL_SERVER_ERROR = "Error inesperado de servidor. Por favor, intente en otro momento";
	public static final int INTERNAL_SERVER_ERROR_CODE = 2001;
	public static final int ILLEGAL_ARGUMENT_CODE = 2002;
	public static final int TRANSACTION_ERROR_CODE = 2003;
	public static final int UPDATE_ERROR_CODE = 2004;
	public static final int INSERT_ERROR_CODE = 2005;
	public static final int DELETE_ERROR_CODE = 2006;
	//General
	public static final Integer ENTIDAD_BANCO_PICHINCHA = 1;
	public static final String TIPO_MOVIMIENTO_RETIRO="Retiro";
	public static final String TIPO_MOVIMIENTO_DEPOSITO="Deposito";
	public static final String SALDO_NO_DISPONIBLE="Saldo no disponible.";
	public static final String SALDO_INSUFIENTE="Saldo insuficiente.";
	
	//MSG
	public static final String CONSULTA_OK="Consulta realizada con exito.";
	public static final String REGISTRO_USUARIO_OK_MSG="Registro de usuario realizado con exito.";
	public static final String REGISTRO_USUARIO_PERSONAS_KO_MSG="Registro de usuario en tabla personas realizado sin exito.";
	public static final String REGISTRO_USUARIO_CLIENTES_KO_MSG="Registro de usuario en tabla clientes realizado sin exito.";
	public static final String REGISTRO_CUENTA_OK_MSG="Registro de cuenta realizado con exito.";
	public static final String REGISTRO_CUENTA_KO_MSG="Registro de cuenta en tabla cuentas realizado sin exito.";
	public static final String REGISTRO_MOVIMIENTO_OK_MSG="Registro de movimiento realizado con exito.";
	public static final String REGISTRO_MOVIMIENTO_KO_MSG="Registro de movimiento en tabla movimientos realizado sin exito.";
	public static final String ACTUALIZACION_USUARIO_OK_MSG="Actualizacion de usuario realizado con exito.";
	public static final String ACTUALIZACION_USUARIO_PERSONAS_KO_MSG="Actualizacion de usuario en tabla personas realizado sin exito.";
	public static final String ACTUALIZACION_USUARIO_CLIENTES_KO_MSG="Actualizacion de usuario en tabla clientes realizado sin exito.";
	public static final String ACTUALIZACION_CUENTA_OK_MSG="Actualizacion de cuenta realizado con exito.";
	public static final String ACTUALIZACION_CUENTA_KO_MSG="Actualizacion de cuenta en tabla cuentas realizado sin exito.";
	public static final String ELIMINACION_USUARIO_OK_MSG="Eliminacion de usuario realizado con exito.";
	public static final String ELIMINACION_USUARIO_PERSONAS_KO_MSG="Eliminacion de usuario en tabla personas realizado sin exito.";
	public static final String ELIMINACION_USUARIO_CLIENTES_KO_MSG="Eliminacion de usuario en tabla clientes realizado sin exito.";
	public static final String ELIMINACION_CUENTA_OK_MSG="Eliminacion de cuenta realizado con exito.";
	public static final String ELIMINACION_CUENTA_KO_MSG="Eliminacion de cuenta en tabla cuentas realizado sin exito.";
	//error_code
	public static final int OK_ERROR_CODE = 0;
	public static final int KO_ERROR_CODE = 1;
	//

}