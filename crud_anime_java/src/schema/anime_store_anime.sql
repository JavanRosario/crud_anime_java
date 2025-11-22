-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: anime_store
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anime`
--

DROP TABLE IF EXISTS `anime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anime` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `episodes` int NOT NULL,
  `producer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producer_id` (`producer_id`),
  CONSTRAINT `anime_ibfk_1` FOREIGN KEY (`producer_id`) REFERENCES `producer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anime`
--

LOCK TABLES `anime` WRITE;
/*!40000 ALTER TABLE `anime` DISABLE KEYS */;
INSERT INTO `anime` VALUES (33,'Erased',12,1),(34,'No Game No Life',12,2),(35,'Mob Psycho 100 (S1)',12,3),(36,'Chainsaw Man',12,4),(37,'The Ancient Magus Bride',24,5),(38,'Your Lie in April',22,6),(39,'Cowboy Bebop',26,7),(40,'Violet Evergarden',13,8),(41,'K-On! (S1)',13,8),(42,'Toradora!',25,9),(43,'Eden of the East',11,10),(44,'Bunny Girl Senpai',13,11),(45,'Little Witch Academia',13,12),(46,'Blue Exorcist (S1)',25,13),(47,'Fate/Zero (S1)',13,14),(48,'Masamune-kun no Revenge',12,15),(49,'Konosuba (S1)',10,16),(50,'Charlotte',13,17),(51,'Tokyo Revengers (S1)',12,18),(52,'Beastars',12,19),(53,'Durarara!!',24,20),(54,'Monogatari Series: Bakemonogatari',15,22),(55,'Sword Art Online: Alicization (Part 1)',12,6),(56,'Fire Force (S1)',24,24),(57,'Dr. Stone (S1)',24,25),(58,'Re:Zero (S1 Part 1)',13,26),(59,'Oregairu (S1)',13,27),(60,'Made in Abyss (S1)',13,28),(61,'Assassination Classroom (S1)',22,29),(62,'Hellsing (2001)',13,30),(63,'Erased',12,1),(64,'No Game No Life',12,2),(65,'Mob Psycho 100 (S1)',12,3),(66,'Chainsaw Man',12,4),(67,'The Ancient Magus Bride',24,5),(68,'Your Lie in April',22,6),(69,'Cowboy Bebop',26,7),(70,'Violet Evergarden',13,8),(71,'K-On! (S1)',13,8),(72,'Toradora!',25,9),(73,'Eden of the East',11,10),(74,'Bunny Girl Senpai',13,11),(75,'Little Witch Academia',13,12),(76,'Blue Exorcist (S1)',25,13),(77,'Fate/Zero (S1)',13,14),(78,'Masamune-kun no Revenge',12,15),(79,'Konosuba (S1)',10,16),(80,'Charlotte',13,17),(81,'Tokyo Revengers (S1)',12,18),(82,'Beastars',12,19),(83,'Durarara!!',24,20),(84,'Monogatari Series: Bakemonogatari',15,22),(85,'Sword Art Online: Alicization (Part 1)',12,6),(86,'Fire Force (S1)',24,24),(87,'Dr. Stone (S1)',24,25),(88,'Re:Zero (S1 Part 1)',13,26),(89,'Oregairu (S1)',13,27),(90,'Made in Abyss (S1)',13,28),(91,'Assassination Classroom (S1)',22,29),(92,'Hellsing (2001)',13,30),(93,'Erased',12,1),(94,'No Game No Life',12,2),(95,'Mob Psycho 100 (S1)',12,3),(96,'Chainsaw Man',12,4),(97,'The Ancient Magus Bride',24,5),(98,'Your Lie in April',22,6),(99,'Cowboy Bebop',26,7),(100,'Violet Evergarden',13,8),(101,'K-On! (S1)',13,8),(102,'Toradora!',25,9),(103,'Eden of the East',11,10),(104,'Bunny Girl Senpai',13,11),(105,'Little Witch Academia',13,12),(106,'Blue Exorcist (S1)',25,13),(107,'Fate/Zero (S1)',13,14),(108,'Masamune-kun no Revenge',12,15),(109,'Konosuba (S1)',10,16),(110,'Charlotte',13,17),(111,'Tokyo Revengers (S1)',12,18),(112,'Beastars',12,19),(113,'Durarara!!',24,20),(114,'Monogatari Series: Bakemonogatari',15,22),(115,'Sword Art Online: Alicization (Part 1)',12,6),(116,'Fire Force (S1)',24,24),(117,'Dr. Stone (S1)',24,25),(118,'Re:Zero (S1 Part 1)',13,26),(119,'Oregairu (S1)',13,27),(120,'Made in Abyss (S1)',13,28),(121,'Assassination Classroom (S1)',22,29),(122,'Hellsing (2001)',13,30);
/*!40000 ALTER TABLE `anime` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-21 17:12:18
