	-- 1. Create database.

-- Database: sistema

DROP DATABASE IF EXISTS sistema;

CREATE DATABASE sistema
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	

	-- 2. Create public schema of the database.

-- SCHEMA: public

DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;


	-- 3. Create sequences that will use the database tables.

-- SEQUENCE: public.persona_id_seq

DROP SEQUENCE IF EXISTS public.persona_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.persona_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.persona_id_seq
    OWNER TO postgres;

COMMENT ON SEQUENCE public.persona_id_seq
    IS 'Secuencia para el campo PERSONAID de la tabla PERSONAS';

GRANT ALL ON SEQUENCE public.persona_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE public.persona_id_seq TO postgres;


-- SEQUENCE: public.cliente_id_seq

DROP SEQUENCE IF EXISTS public.cliente_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.cliente_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.cliente_id_seq
    OWNER TO postgres;

COMMENT ON SEQUENCE public.cliente_id_seq
    IS 'Secuencia para el campo CLIENTEID de la tabla CLIENTES';

GRANT ALL ON SEQUENCE public.cliente_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE public.cliente_id_seq TO postgres;


-- SEQUENCE: public.entidad_id_seq

DROP SEQUENCE IF EXISTS public.entidad_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.entidad_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.entidad_id_seq
    OWNER TO postgres;

COMMENT ON SEQUENCE public.entidad_id_seq
    IS 'Secuencia para el campo ENTIDADID de la tabla ENTIDADES';

GRANT ALL ON SEQUENCE public.entidad_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE public.entidad_id_seq TO postgres;



-- SEQUENCE: public.cuenta_id_seq
 
DROP SEQUENCE IF EXISTS public.cuenta_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.cuenta_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.cuenta_id_seq
    OWNER TO postgres;

COMMENT ON SEQUENCE public.cuenta_id_seq
    IS 'Secuencia para el campo CUENTAID de la tabla CUENTAS';

GRANT ALL ON SEQUENCE public.cuenta_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE public.cuenta_id_seq TO postgres;


-- SEQUENCE: public.movimiento_id_seq

DROP SEQUENCE IF EXISTS public.movimiento_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.movimiento_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.movimiento_id_seq
    OWNER TO postgres;

COMMENT ON SEQUENCE public.movimiento_id_seq
    IS 'Secuencia para el campo MOVIMIENTOID de la tabla MOVIMIENTOS';

GRANT ALL ON SEQUENCE public.movimiento_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE public.movimiento_id_seq TO postgres;


	-- 3. Creation of tables that will use the database tables.
	
-- Table: public.personas

DROP TABLE IF EXISTS public.personas;

CREATE TABLE IF NOT EXISTS public.personas
(
    personaid integer NOT NULL DEFAULT nextval('persona_id_seq'::regclass),
    primernombre character varying COLLATE pg_catalog."default" NOT NULL,
    segundonombre character varying COLLATE pg_catalog."default",
    primerapellido character varying COLLATE pg_catalog."default" NOT NULL,
    segundoapellido character varying COLLATE pg_catalog."default",
    genero character varying COLLATE pg_catalog."default" NOT NULL DEFAULT 'Dato no conocido'::character varying,
    edad character varying COLLATE pg_catalog."default" NOT NULL DEFAULT 'Dato no conocido'::character varying,
    identificacion character varying COLLATE pg_catalog."default" NOT NULL DEFAULT 'Dato no conocido'::character varying,
    direccion character varying COLLATE pg_catalog."default" NOT NULL DEFAULT 'Dato no conocido'::character varying,
    telefono character varying COLLATE pg_catalog."default" NOT NULL DEFAULT 'Dato no conocido'::character varying,
    CONSTRAINT "PERSONAS_pkey" PRIMARY KEY (personaid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.personas
    OWNER to postgres;

GRANT ALL ON TABLE public.personas TO postgres;

GRANT ALL ON TABLE public.personas TO PUBLIC;

COMMENT ON TABLE public.personas
    IS 'La tabla "personas" almacena los registros de los usuarios o clientes del ejercicio practico del Banco Pichincha.';
	
	
	
-- Table: public.clientes

DROP TABLE IF EXISTS public.clientes;

CREATE TABLE IF NOT EXISTS public.clientes
(
    clienteid integer NOT NULL DEFAULT nextval('cliente_id_seq'::regclass),
    personaid integer NOT NULL,
    contrasenia character varying COLLATE pg_catalog."default" NOT NULL,
    estado boolean NOT NULL DEFAULT true,
    CONSTRAINT "CLIENTES_pkey" PRIMARY KEY (clienteid),
    CONSTRAINT "CLIENTES_PERSONA_personaid" FOREIGN KEY (personaid)
        REFERENCES public.personas (personaid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.clientes
    OWNER to postgres;

GRANT ALL ON TABLE public.clientes TO postgres;

GRANT ALL ON TABLE public.clientes TO PUBLIC;

COMMENT ON TABLE public.clientes
    IS 'La tabla "clientes" almacena los registros de las credenciales y estado de cliente del ejercicio practico del Banco Pichincha.';
	
	
	
-- Table: public.entidades

DROP TABLE IF EXISTS public.entidades;

CREATE TABLE IF NOT EXISTS public.entidades
(
    entidadid integer NOT NULL DEFAULT nextval('entidad_id_seq'::regclass),
    nombre character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ENTIDADES_pkey" PRIMARY KEY (entidadid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.entidades
    OWNER to postgres;

GRANT ALL ON TABLE public.entidades TO postgres;

GRANT ALL ON TABLE public.entidades TO PUBLIC;

COMMENT ON TABLE public.entidades
    IS 'La tabla "entidades" almacena los registros de las entidades donde los usuarios o clientes tienen las cuentas del ejercicio practico del Banco Pichincha.';	


	
-- Table: public.cuentas

DROP TABLE IF EXISTS public.cuentas;

CREATE TABLE IF NOT EXISTS public.cuentas
(
    cuentaid integer NOT NULL DEFAULT nextval('cuenta_id_seq'::regclass),
    entidadid integer NOT NULL DEFAULT 1,
    clienteid integer NOT NULL,
    tipo character varying COLLATE pg_catalog."default" NOT NULL,
    numero character varying COLLATE pg_catalog."default" NOT NULL,
    saldoinicial integer NOT NULL,
    estado boolean NOT NULL DEFAULT true,
    CONSTRAINT "CUENTAS_pkey" PRIMARY KEY (cuentaid),
    CONSTRAINT "CUENTAS_CLIENTES_clienteid" FOREIGN KEY (clienteid)
        REFERENCES public.clientes (clienteid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "CUENTAS_ENTIDADES_entidadid" FOREIGN KEY (entidadid)
        REFERENCES public.entidades (entidadid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cuentas
    OWNER to postgres;

GRANT ALL ON TABLE public.cuentas TO postgres;

GRANT ALL ON TABLE public.cuentas TO PUBLIC;

COMMENT ON TABLE public.cuentas
    IS 'La tabla "cuentas" almacena los registros de las cuentas de los usuarios o clientes del ejercicio practico del Banco Pichincha.';	
	

-- Table: public.movimientos

DROP TABLE IF EXISTS public.movimientos;

CREATE TABLE IF NOT EXISTS public.movimientos
(
    movimientoid integer NOT NULL DEFAULT nextval('movimiento_id_seq'::regclass),
    entidadid integer NOT NULL,
    cuentaid integer NOT NULL,
    tipo character varying COLLATE pg_catalog."default" NOT NULL,
    fecha timestamp with time zone NOT NULL,
    valor integer NOT NULL,
    saldoinicial integer NOT NULL,
    CONSTRAINT "MOVIMIENTOS_pkey" PRIMARY KEY (movimientoid),
    CONSTRAINT "MOVIMIENTOS_CUENTAS_cuentaid" FOREIGN KEY (cuentaid)
        REFERENCES public.cuentas (cuentaid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "MOVIMIENTOS_ENTIDADES_entidadid" FOREIGN KEY (entidadid)
        REFERENCES public.entidades (entidadid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movimientos
    OWNER to postgres;

GRANT ALL ON TABLE public.movimientos TO postgres;

GRANT ALL ON TABLE public.movimientos TO PUBLIC;

COMMENT ON TABLE public.movimientos
    IS 'La tabla "movimientos" almacena los registros de los movimientos que se realizan a las cuentas de los usuarios o clientes del ejercicio practico del Banco Pichincha.';


		-- 4 Inserts iniciales 
		
-- ENTIDADES 	
INSERT INTO ENTIDADES (
	ENTIDADID, NOMBRE)
	VALUES (1, 'Banco Pichincha');
	
INSERT INTO ENTIDADES (
	ENTIDADID, NOMBRE)
	VALUES (2, 'Banco Sudamericano');
	