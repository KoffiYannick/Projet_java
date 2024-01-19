-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.2.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for gestion_salle
CREATE DATABASE IF NOT EXISTS `gestion_salle` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `gestion_salle`;

-- Dumping structure for table gestion_salle.batiment
CREATE TABLE IF NOT EXISTS `batiment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_batiment` varchar(50) NOT NULL DEFAULT '',
  `nombre_etages` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table gestion_salle.reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_salle` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `heure` time DEFAULT NULL,
  `heure-fin` time DEFAULT NULL,
  `promo` varchar(255) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_salle` (`id_salle`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table gestion_salle.salle
CREATE TABLE IF NOT EXISTS `salle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batiment` text DEFAULT NULL,
  `etage` int(11) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
