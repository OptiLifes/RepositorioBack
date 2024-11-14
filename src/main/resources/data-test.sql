INSERT INTO Usuario (nombre, email, contraseña, tipo_usuario, suscripcion_id)
VALUES
    ( 'Juan Pérez', 'juan.perez@mail.com', 'password123', 'premium', 1),
    ( 'María Gómez', 'maria.gomez@mail.com', 'password456', 'free', 6),
    ( 'Carlos Sanchez', 'carlos.sanchez@mail.com', 'password789', 'premium', 2);
INSERT INTO Perfil (id_perfil, foto_perfil, usuario_id)
VALUES
    (1, 'foto1.jpg', 1),
    (2, 'foto2.jpg', 2),
    (3, 'foto3.jpg', 3);
INSERT INTO Suscripcion (id_suscripcion, fecha_inicio, fecha_fin, usuario_id, pago_id)
VALUES
    (1, '2024-01-01', '2024-02-01', 1, 1),
    (2, '2024-01-15', '2024-02-15', 3, 2);
INSERT INTO Pago (id_pago, metodo_pago, monto, usuario_id)
VALUES
    (1, 'tarjeta de crédito', 9.99, 1),
    (2, 'paypal', 9.99, 3);
INSERT INTO Recursos (id_recurso, descripcion, usuario_id, tipo_recurso, id_suscripcion)
VALUES
    (1, 'Video de ejercicios avanzados', 1, 'video', 1),
    (2, 'Guía de recetas saludables', 3, 'documento', 2);
INSERT INTO Notificacion (id_notificacion, mensaje, usuario_id)
VALUES
    (1, 'Tu suscripción vencerá en 5 días.', 1),
    (2, 'Nuevo recurso disponible: Recetas saludables.', 3);
INSERT INTO Meta (id_meta, tipo_meta, descripcion_meta, perfil_id)
VALUES
    (1, 'Meta de ejercicio', 'Realizar 10,000 pasos diarios', 1),
    (2, 'Meta de alimentación', 'Ingerir 2000 calorías al día', 2),
    (3, 'Meta de hidratación', 'Beber 2 litros de agua al día', 3);
INSERT INTO Salud (id_salud, perfil_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);
INSERT INTO Alimento (id_alimento, nombre, calorías, salud_id)
VALUES
    (1, 'Manzana', 52, 1),
    (2, 'Plátano', 89, 2),
    (3, 'Pechuga de pollo', 165, 3);
INSERT INTO Hidratacion (id_hidratacion, cantidad_agua, salud_id)
VALUES
    (1, 1.5, 1),
    (2, 2.0, 2),
    (3, 1.8, 3);
INSERT INTO Sueno (id_sueno, horas_dormidas, salud_id)
VALUES
    (1, 7.5, 1),
    (2, 8.0, 2),
    (3, 6.5, 3);
