 -- INSERTS 

-- DELETE FROM personas; 
insert into personas(
	nombre, direccion, telefono)
	values ('Jose Lema', 'Otavalo sn y principal', '098254785'); 
 
insert into personas(
	nombre, direccion, telefono)
	values ('Marianela Montalvo', 'Amazonas y NNUU', '097548965');
	
insert into personas(
	nombre, direccion, telefono)
	values ('Juan Osorio', '13 junio y Equinoccial ', '098874587');



-- DELETE FROM clientes; 
insert into clientes(
	personaid, contrasenia, estado)
	values (5, 1234, true);
	
insert into clientes(
	personaid, contrasenia, estado)
	values (6, 5678, true);

insert into clientes(
	personaid, contrasenia, estado)
	values (7, 1245, true);
	


-- DELETE FROM cuentas; 	
insert into cuentas(
	clienteid, tipo, numero, saldoinicial, estado)
	values (1, 'Ahorros', '478758', 2000, true);
	
insert into cuentas(
	clienteid, tipo, numero, saldoinicial, estado)
	values (2, 'Corriente', '225487', 100, true);
	
insert into cuentas(
	clienteid, tipo, numero, saldoinicial, estado)
	values (3, 'Ahorros', '495878', 0, true);

insert into cuentas(
	clienteid, tipo, numero, saldoinicial, estado)
	values (2, 'Ahorros', '496825', 540, true);
	
insert into cuentas(
	clienteid, tipo, numero, saldoinicial, estado)
	values (1, 'Corriente', '585545', 1000, true);



-- DELETE FROM movimientos; 	
insert into movimientos(
	entidadid, cuentaid, tipo, valor, saldoinicial)
	values ((select entidadid from cuentas c where c.numero = '478758'), (select cuentaid from cuentas c where c.numero = '478758'), 'Retiro', 575, (select saldoinicial from cuentas c where c.numero = '478758'));
	
UPDATE cuentas
	SET saldoinicial=((select saldoinicial from cuentas c where c.numero = '478758')-575) 
	WHERE cuentaid=(select cuentaid from cuentas c where c.numero = '478758');	



INSERT INTO movimientos(
	entidadid, cuentaid, tipo, valor, saldoinicial)
	VALUES ((select entidadid from cuentas c where c.numero = '225487'), (select cuentaid from cuentas c where c.numero = '225487'), 'Deposito', 600, (select saldoinicial from cuentas c where c.numero = '225487'));
	
UPDATE cuentas
	SET saldoinicial=((select saldoinicial from cuentas c where c.numero = '225487')+600) 
	WHERE cuentaid=(select cuentaid from cuentas c where c.numero = '225487');	



INSERT INTO movimientos(
	entidadid, cuentaid, tipo, valor, saldoinicial)
	VALUES ((select entidadid from cuentas c where c.numero = '495878'), (select cuentaid from cuentas c where numero = '495878'), 'Deposito', 150, (select saldoinicial from cuentas c where c.numero = '495878'));

UPDATE cuentas
	SET saldoinicial=((select saldoinicial from cuentas c where c.numero = '495878')+150) 
	WHERE cuentaid=(select cuentaid from cuentas c where c.numero = '495878');	
	
	

INSERT INTO movimientos(
	entidadid, cuentaid, tipo, valor, saldoinicial)
	VALUES ((select entidadid from cuentas c where c.numero = '496825'), (select cuentaid from cuentas c where c.numero = '496825'), 'Retiro', 540, (select saldoinicial from cuentas c where c.numero = '496825'));

UPDATE cuentas
	SET saldoinicial=((select saldoinicial from cuentas c where c.numero = '496825')-540) 
	WHERE cuentaid=(select cuentaid from cuentas c where c.numero = '496825');	