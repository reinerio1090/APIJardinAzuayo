-- Table: public.incidencia

-- DROP TABLE public.incidencia;

CREATE TABLE public.incidencia
(
    id_incidencia bigint NOT NULL DEFAULT nextval('incidencia_id_incidencia_seq'::regclass),
    descripcion character varying(255) COLLATE pg_catalog."default",
    estado boolean NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    fecha_creacion timestamp(6) without time zone,
    id_socio bigint,
    id_usuario_soporte bigint,
    observacion character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT incidencia_pkey PRIMARY KEY (id_incidencia)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.incidencia
    OWNER to postgres;


INSERT INTO public.incidencia (descripcion, estado, fecha_actualizacion, fecha_creacion, id_socio, id_usuario_soporte, observacion)
VALUES 
('Consulta sobre cierre de cuenta', true, '2024-05-21 10:30:00', '2024-05-21 09:00:00', 2, 1, 'La cuenta fue cerrada exitosamente y se enviaron los documentos al cliente.'),
('Problema con el pago automático de facturas', true, '2024-05-22 14:20:00', '2024-05-22 12:00:00', 5, 3, 'El pago automático fue reconfigurado y se verificó el correcto funcionamiento.');

INSERT INTO public.incidencia (descripcion, fecha_creacion, estado, id_socio)
VALUES 
('Incidencia con el cajero automático en la sucursal principal', '2024-05-01 10:00:00', false, 3),
('Problema con la transferencia internacional', '2024-05-02 11:30:00', false, 7),
('Error en la actualización de datos personales', '2024-05-03 09:15:00', false, 1),
('Consulta sobre un cargo no reconocido', '2024-05-04 14:45:00', false, 10),
('Fallo en la aplicación móvil del banco', '2024-05-05 08:50:00', false, 4),
('Reclamo por el mal funcionamiento de la tarjeta de débito', '2024-05-06 13:20:00', false, 9),
('Solicitud de revisión de intereses en cuenta de ahorro', '2024-05-07 16:10:00', false, 2),
('Incidencia con la renovación de la tarjeta de crédito', '2024-05-08 10:05:00', false, 8),
('Problema al realizar un pago en línea', '2024-05-09 17:30:00', false, 5),
('Error en el estado de cuenta mensual', '2024-05-10 12:45:00', false, 6);

INSERT INTO public.incidencia (descripcion, estado, fecha_actualizacion, fecha_creacion, id_socio, id_usuario_soporte, observacion)
VALUES 
('Error en la consulta del saldo disponible', true, '2024-05-23 11:50:00', '2024-05-23 09:30:00', 8, 4, 'El error fue corregido y el saldo se muestra correctamente en la aplicación.'),
('Reclamo por duplicidad de transacciones', true, '2024-05-24 16:40:00', '2024-05-24 14:10:00', 7, 2, 'Las transacciones duplicadas fueron revertidas y el saldo fue ajustado.'),
('Solicitud de cambio de dirección de correspondencia', true, '2024-05-25 15:25:00', '2024-05-25 13:00:00', 10, 5, 'La nueva dirección fue actualizada en el sistema y confirmada con el cliente.');
