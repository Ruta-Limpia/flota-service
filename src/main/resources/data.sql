INSERT INTO camiones (id, patente, modelo, capacidad_paradas, conductor_id, estado) VALUES
(1, 'RLAB12', 'Hyundai HD78', 20, 2, 'DISPONIBLE'),
(2, 'RLCD34', 'JAC 1061', 15, NULL, 'DISPONIBLE'),
(3, 'RLEF56', 'Hino 300', 20, NULL, 'MANTENCION')
ON DUPLICATE KEY UPDATE modelo = VALUES(modelo);

INSERT IGNORE INTO camion_tipos_residuo (camion_id, tipo_residuo) VALUES
(1, 'VIDRIO'),
(1, 'PLASTICO'),
(1, 'PAPEL_CARTON'),
(2, 'METAL'),
(2, 'ELECTRONICO'),
(3, 'VIDRIO');
