-- MySQL dump 10.18  Distrib 10.3.27-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: RENTVIEWREVIEWSERVICE
-- ------------------------------------------------------
-- Server version	10.3.27-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `RENTVIEWREVIEWSERVICE`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `RENTVIEWREVIEWSERVICE` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `RENTVIEWREVIEWSERVICE`;

--
-- Table structure for table `MEMBER`
--

DROP TABLE IF EXISTS `MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEMBER` (
  `member_ID` int(11) NOT NULL AUTO_INCREMENT,
  `email_address` varchar(255) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `member_type` enum('manager','user') NOT NULL DEFAULT 'user',
  `last_four_digits` char(4) DEFAULT NULL,
  `card_type` varchar(20) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  PRIMARY KEY (`member_ID`),
  UNIQUE KEY `member_ID` (`member_ID`),
  UNIQUE KEY `email_address` (`email_address`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBER`
--

LOCK TABLES `MEMBER` WRITE;
/*!40000 ALTER TABLE `MEMBER` DISABLE KEYS */;
INSERT INTO `MEMBER` VALUES (1,'safhossain338@gmail.com','98ab44a8','Safwan','Hossain','2002-10-22','manager',NULL,NULL,NULL),(2,'s2hossain@torontomu.ca','15d0cbde','admin','admin','2000-10-21','manager',NULL,NULL,NULL),(3,'alice@example.com','2f0e8570','Alice','Wonder','1990-05-15','user','1234','Visa','2025-12-31'),(4,'BobJohn@example.com','531142f','Bob','John','1985-01-30','user','5678','MasterCard','2024-11-30'),(5,'CBrown@example.com','dcbca3ba','Chris','Brown','1989-05-05','user','9012','Amex','2023-09-15'),(6,'D1Wilson@example.com','f0772152','Derek','Wilson','1992-08-24','user',NULL,NULL,NULL),(7,'ethan69@example.com','97319cf0','Ethan','Smith','2000-03-12','user',NULL,NULL,NULL);
/*!40000 ALTER TABLE `MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOVIE`
--

DROP TABLE IF EXISTS `MOVIE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MOVIE` (
  `movie_ID` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(255) NOT NULL,
  `release_year` year(4) NOT NULL,
  `rental_cost` decimal(10,2) NOT NULL,
  `movie_image_path` varchar(255) DEFAULT NULL,
  `is_movie_featured` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`movie_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOVIE`
--

LOCK TABLES `MOVIE` WRITE;
/*!40000 ALTER TABLE `MOVIE` DISABLE KEYS */;
INSERT INTO `MOVIE` VALUES (1,'Lord of the Rings Fellowship of the Ring',2001,3.99,'/resources/movie_posters/lord_of_the_rings_fellowship_of_the_ring.jpg',1),(2,'Harry Potter',2001,2.99,'/resources/movie_posters/harry_potter.jpg',1),(3,'Little Women',2019,3.49,'/resources/movie_posters/little_women.jpg',1),(4,'Home Alone',1990,1.99,'/resources/movie_posters/home_alone.jpg',0),(5,'Finding Nemo',2003,2.49,'/resources/movie_posters/finding_nemo.jpg',0),(6,'Forrest Gump',1994,2.99,'/resources/movie_posters/forrest_gump.jpg',0),(7,'Toy Story',1995,2.99,'/resources/movie_posters/toy_story.jpg',0),(8,'The Matrix',1999,3.99,'/resources/movie_posters/the_matrix.jpg',0),(9,'Inception',2010,3.99,'/resources/movie_posters/inception.jpg',0),(10,'The Lion King',1994,2.99,'/resources/movie_posters/the_lion_king.jpg',0),(11,'Oppenheimer',2023,4.99,'/resources/movie_posters/oppenheimer.jpg',1);
/*!40000 ALTER TABLE `MOVIE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEW`
--

DROP TABLE IF EXISTS `REVIEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVIEW` (
  `review_ID` int(11) NOT NULL AUTO_INCREMENT,
  `review_description` text DEFAULT NULL,
  `rating` int(11) DEFAULT NULL CHECK (`rating` >= 1 and `rating` <= 5),
  `member_ID` int(11) DEFAULT NULL,
  `movie_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_ID`),
  KEY `member_ID` (`member_ID`),
  KEY `movie_ID` (`movie_ID`),
  CONSTRAINT `REVIEW_ibfk_1` FOREIGN KEY (`member_ID`) REFERENCES `MEMBER` (`member_ID`),
  CONSTRAINT `REVIEW_ibfk_2` FOREIGN KEY (`movie_ID`) REFERENCES `MOVIE` (`movie_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEW`
--

LOCK TABLES `REVIEW` WRITE;
/*!40000 ALTER TABLE `REVIEW` DISABLE KEYS */;
INSERT INTO `REVIEW` VALUES (1,'Great movie!',5,3,1),(2,'Loved it!',4,4,2),(3,'Very entertaining',4,5,3),(4,'A classic',5,6,4),(5,'Heartwarming story',4,7,5),(6,'I like turtles',5,4,5),(7,'Dune better',3,3,1);
/*!40000 ALTER TABLE `REVIEW` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17  2:40:01
