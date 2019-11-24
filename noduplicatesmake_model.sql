-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2019 at 06:11 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hraoof`
--

-- --------------------------------------------------------

--
-- Table structure for table `noduplicatesmake_model`
--

CREATE TABLE `noduplicatesmake_model` (
  `Accident_Index` bigint(13) DEFAULT NULL,
  `Vehicle_Type` int(2) DEFAULT NULL,
  `Vehicle_Manoeuvre` int(2) DEFAULT NULL,
  `Sex_of_Driver` int(1) DEFAULT NULL,
  `Age_Band_of_Driver` int(2) DEFAULT NULL,
  `Engine_Capacity_(CC)` int(5) DEFAULT NULL,
  `Age_of_Vehicle` int(2) DEFAULT NULL,
  `make` varchar(17) CHARACTER SET utf8 DEFAULT NULL,
  `model` varchar(30) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `noduplicatesmake_model`
--

INSERT INTO `noduplicatesmake_model` (`Accident_Index`, `Vehicle_Type`, `Vehicle_Manoeuvre`, `Sex_of_Driver`, `Age_Band_of_Driver`, `Engine_Capacity_(CC)`, `Age_of_Vehicle`, `make`, `model`) VALUES
(1, 1, 1, 1, 1, 1000, NULL, 'FORD', 'FOCUS'),
(2, 2, 2, 2, 2, 1000, NULL, 'FORD', 'FOCUS');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
