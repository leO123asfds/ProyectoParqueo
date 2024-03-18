

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";



--
-- Base de datos: `bdparqueadero`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `username` varchar(50) NOT NULL,
  `ape_us` varchar(50) NOT NULL,
  `cel_us` varchar(50) NOT NULL,
  `foto_us` longblob DEFAULT NULL,
  `id_us` int(11) NOT NULL,
  `nombre_us` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `recuperacion` varchar(50) NOT NULL,
  `tipo_us` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`username`, `ape_us`, `cel_us`, `foto_us`, `id_us`, `nombre_us`, `pass`, `recuperacion`, `tipo_us`) VALUES
('leo12', 'rios', '991796274', NULL, 2, 'leoncio', '1234', '12345678', 'servicio'),
('leo123', 'rios', '991796274', NULL, 1, 'leoncio', '1234', '12345678', 'admi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(10) NOT NULL,
  `placa` varchar(6) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `propietario` varchar(60) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `tipovehiculo` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `horaentrada` datetime NOT NULL,
  `horasalida` datetime DEFAULT NULL,
  `valorpagado` float DEFAULT NULL,
  `estado` varchar(13) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`id`, `placa`, `propietario`, `tipovehiculo`, `horaentrada`, `horasalida`, `valorpagado`, `estado`) VALUES
(1, 'DD123', 'leo', 'Automovil', '2023-05-07 19:44:30', NULL, NULL, 'Disponible'),
(2, 'MT145', 'leoncio', 'Motocicleta', '2023-05-09 00:07:22', NULL, NULL, 'Disponible'),
(3, 'MR906', 'Leoncio Rios', 'Motocicleta', '2023-05-09 09:10:04', NULL, NULL, 'Disponible'),
(4, 'QW123', 'leoncio rios', 'Automovil', '2023-05-09 10:52:26', NULL, NULL, 'Disponible'),
(5, 'QWE123', 'Leo', 'Motocicleta', '2023-05-09 11:42:54', NULL, NULL, 'Disponible'),
(6, '123ab', 'leoncio', 'Motocicleta', '2023-05-21 22:16:41', NULL, NULL, 'Disponible'),
(7, '1234', 'sebas', 'Motocicleta', '2023-05-28 00:31:47', NULL, NULL, 'Disponible'),
(8, '123', 's', 'Motocicleta', '2023-05-28 17:51:34', NULL, NULL, 'Disponible'),
(9, '123', 'juan carla', 'Motocicleta', '2023-05-28 18:35:50', NULL, NULL, 'Disponible'),
(10, '1234', 'sebas', 'Motocicleta', '2023-05-29 03:04:24', NULL, NULL, 'Disponible'),
(11, '12345', 'leoncio', 'Automovil', '2023-06-19 23:23:40', NULL, NULL, 'Disponible'),
(12, '123456', 'leo', 'Automovil', '2023-06-20 09:40:34', NULL, NULL, 'Disponible'),
(13, '1234', 'leoncio', 'Automovil', '2023-06-20 11:54:51', NULL, NULL, 'Disponible');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;
