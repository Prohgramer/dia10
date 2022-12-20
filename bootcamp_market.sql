--ejercicio 1

SELECT count(f.id),c.nombre,c.apellido,c.nro_cedula FROM cliente c join factura f on c.id = f.cliente_id GROUP BY c.nombre,c.apellido,c.nro_cedula ORDER BY count DESC;

--ejercicio 2

SELECT c.nombre, SUM(ROUND(p.precio*fd.cantidad)) AS suma 
FROM producto  p 
INNER JOIN factura_detalle fd 
ON p.id = fd.producto_id 
INNER JOIN factura f 
ON f.id = fd.factura_id 
INNER JOIN cliente c 
ON f.cliente_id = c.id GROUP BY (c.id) ORDER BY suma DESC;

--ejercicio 3

SELECT count(m.nombre),m.nombre FROM moneda m join factura f on m.id = f.moneda_id GROUP BY m.nombre ORDER BY count DESC;

--ejercicio 4

SELECT count(p.ruc),p.nombre FROM proveedor p join producto pr on p.id=pr.proveedor_id GROUP BY p.nombre ORDER BY count DESC;

--ejercicio 5

SELECT p.nombre, SUM(ROUND(fd.cantidad)) AS mas_vendido FROM producto p INNER JOIN factura_detalle fd ON p.id = fd.producto_id GROUP BY(p.id) ORDER BY mas_vendido DESC;

--ejercicio 6

SELECT p.nombre, SUM(ROUND(fd.cantidad)) AS menos_vendido FROM producto p INNER JOIN factura_detalle fd ON p.id = fd.producto_id GROUP BY(p.id) ORDER BY menos_vendido ASC;

--ejercicio 7

SELECT f.fecha_emision, c.nombre, c.apellido, p.nombre , fd.cantidad, ft.nombre 
FROM cliente c 
INNER JOIN factura f 
ON c.id = f.cliente_id 
INNER JOIN factura_detalle fd 
ON f.id = fd.factura_id 
INNER JOIN producto  p
ON fd.producto_id = p.id 
INNER JOIN factura_tipo ft
ON ft.id = f.factura_tipo_id;

--ejercicio 8

SELECT f.id, SUM(ROUND(p.precio*fd.cantidad)) AS monto 
FROM producto p
INNER JOIN factura_detalle fd 
ON p.id = fd.producto_id 
INNER JOIN factura f 
ON f.id = fd.factura_id GROUP BY (f.id) ORDER BY monto DESC;

--ejercicio 9

SELECT f.id, SUM(ROUND(p.precio*fd.cantidad*0.1)) AS iva
FROM producto p
INNER JOIN factura_detalle fd 
ON p.id = fd.producto_id 
INNER JOIN factura f 
ON f.id = fd.factura_id GROUP BY (f.id) ORDER BY iva DESC;