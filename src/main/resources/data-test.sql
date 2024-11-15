INSERT INTO Usuario (id_usuario, nombre, email, contraseña, tipo_usuario)
VALUES
    ( 1, 'Juan Pérez', 'juan.perez@mail.com', 'password123', 'premium'),
    ( 2, 'maria.gomez@mail.com', 'password456', 'free', 6),
    ( 3, 'Carlos Sanchez', 'carlos.sanchez@mail.com', 'password789', 'premium');

INSERT INTO Suscripcion (id_suscripcion, fecha_inicio, fecha_fin, id_usuario, id_pago)
VALUES
    (11, '2024-01-01', '2024-02-01', 1, 1),
    (22, '2024-01-15', '2024-02-15', 3, 2);

INSERT INTO Perfil (id_perfil, foto_perfil, id_usuario)
VALUES
    (1, 'foto1.jpg', 1),
    (2, 'foto2.jpg', 2),
    (3, 'foto3.jpg', 3);

INSERT INTO Pago (id_pago, metodo_pago, monto, id_usuario)
VALUES
    (1, 'tarjeta de crédito', 9.99, 1),
    (2, 'paypal', 9.99, 3);

INSERT INTO Receta (id_receta, nombre_receta, descripcion, tipo_receta, categoria, id_usuario, id_suscripcion)
VALUES
    (1, 'Ensalada de frutas', 'Receta de ensalada de frutas', 'desayuno', 'frutas', 1, 11),
    (2, 'Ensalada de pollo', 'Receta de ensalada de pollo', 'almuerzo', 'pollo', 3, 22);

INSERT INTO Recurso (id_recurso, descripcion, tipo_recurso, categoria, titulo, id_suscripcion, id_usuario)
VALUES
    (1, 'Video de ejercicios avanzados', 'multimedia', 'video', 'Cómo adelgazar consumiendo chia en 10 pasos', 11, 1),
    (2, 'Guía de recetas saludables', 'imagen', 'documento', 'Cuántos km trotar para una buena rutina', 22, 3);

INSERT INTO Notificacion (id_notificacion, mensaje, id_usuario)
VALUES
    (1, 'Tu suscripción vencerá en 5 días.', 1),
    (2, 'Nuevo recurso disponible: Recetas saludables.', 3);

INSERT INTO Meta (id_meta, tipo_meta, descripcion_meta, objetivo_total, litros_agua, progreso_diario, horas_sueño, calorias, proteinas, grasas, carbohidratos, id_perfil)
VALUES
    (1, 'Hidratación', 'Consumir 2 litros de agua diarios', 2000, 2.0, 1.5, 8, 0, 0, 0, 0, 1),
    (2, 'Actividad física', 'Realizar 30 minutos de caminata diaria', 30, 0.5, 0.3, 7, 1500, 80, 40, 200, 2),
    (3, 'Dieta', 'Mantener una ingesta calórica diaria de 1800 calorías', 1800, 1.0, 0.5, 8, 1800, 100, 60, 220, 3);
INSERT INTO Salud (id_salud, id_perfil)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);
INSERT INTO Alimento (id_alimento, nombre_alimento, calorías, id_salud)
VALUES
    (1, 'Manzana', 52, 1),
    (2, 'Plátano', 89, 2),
    (3, 'Pechuga de pollo', 165, 3);
INSERT INTO Hidratacion (id_hidratacion, cantidad_agua, id_salud)
VALUES
    (1, 1.5, 1),
    (2, 2.0, 2),
    (3, 1.8, 3);
INSERT INTO Sueño (id_sueno, horas_dormidas, id_salud)
VALUES
    (1, 7.5, 1),
    (2, 8.0, 2),
    (3, 6.5, 3);

INSERT INTO Video (id_video, titulo, descripcion, url, id_recurso)
VALUES
    (1, 'Ejercicios de cardio', 'Rutina de ejercicios de cardio', 'https://www.youtube.com/watch?v=123', 1),
    (2, 'Ejercicios de fuerza', 'Rutina de ejercicios de fuerza', 'https://www.youtube.com/watch?v=456', 1);