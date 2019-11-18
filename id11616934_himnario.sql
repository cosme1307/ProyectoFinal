-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 18-11-2019 a las 19:31:50
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id11616934_himnario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alabanza`
--

CREATE TABLE `alabanza` (
  `id` int(10) NOT NULL,
  `titulo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `autor` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `letra` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `alabanza`
--

INSERT INTO `alabanza` (`id`, `titulo`, `autor`, `letra`) VALUES
(10, 'alabare ', 'anonimo', 'alabare alabare a labare a mi señor'),
(12, 'jesus mi buen amigo', 'anonimo', 'jesus mi buen amigo'),
(16, 'carlitos', 'carlos', 'sjsjsjjss'),
(17, 'carlitos', 'carlos', 'sjsjsjjss'),
(18, 'carlitos', 'carlos', 'sjsjsjjss'),
(19, 'carlitos', 'carlos', 'sjsjsjjss'),
(20, 'carlitos', 'carlos', 'sjsjsjjss'),
(26, 'a', 'a', 'a');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `corosAle`
--

CREATE TABLE `corosAle` (
  `id` int(10) NOT NULL,
  `titulo` text COLLATE utf8_unicode_ci NOT NULL,
  `autor` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `letra` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `corosAle`
--

INSERT INTO `corosAle` (`id`, `titulo`, `autor`, `letra`) VALUES
(4, 'hola', 'crespin', 'hola hola hola hola hola hola hola hola hola '),
(11, 'profe gámez ', 'manuelito', 'iakwbkqoquhqnwnskns'),
(12, 'jon', 'jajajja', 'bsbbsbsb'),
(13, 'carlos', 'clon', 'iakakakkaka'),
(14, 'carlos', 'clon', 'iakakakkaka'),
(15, 'carlos', 'clon', 'iakakakkaka'),
(16, 'carlos', 'clon', 'iakakakkaka'),
(17, 'carlos', 'clon', 'iakakakkaka'),
(18, 'carlos', 'clon', 'iakakakkaka');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alabanza`
--
ALTER TABLE `alabanza`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `corosAle`
--
ALTER TABLE `corosAle`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alabanza`
--
ALTER TABLE `alabanza`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `corosAle`
--
ALTER TABLE `corosAle`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
