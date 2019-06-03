-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: auction2019_05_22
-- ------------------------------------------------------
-- Server version	5.6.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `category` varchar(45) NOT NULL DEFAULT 'book',
  `winner_id` int(11) DEFAULT NULL,
  `max_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `price` decimal(10,2) DEFAULT NULL,
  `introduce` varchar(200) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '1：表示未卖出\\n2：已卖出',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` VALUES (1,1,'book',1,3200.00,2999.00,'Leloup,Roger - Dessin original(1983)','img/book/book1.jpg','2019-05-15 23:42:21',2),(2,1,'book',0,0.00,2300.00,'Blake&Mortimer T4(2017)','img/book/book2.jpg','2019-06-04 06:34:41',1),(3,2,'book',0,0.00,5423.00,'Barbe Rouge T2 - Le Roi des septs mers(1962)','img/book/book3.jpg','2019-06-05 06:34:41',1),(4,2,'book',1,3702.00,3700.00,'Asterix T2 - La Serpe(1962)','img/book/book4.jpg','2019-05-16 06:34:41',2),(5,1,'book',NULL,0.00,587.00,'Evelyn Paul, D.G. Rossetti - Stories from Dante - 1910','img/book/book_susan_1.PNG','2019-06-01 22:19:15',1),(6,2,'book',NULL,0.00,373.00,'Tintin T23 - Tintin et les Picaros - Tirage Cocktail','img/book/book_tintin_1.PNG','2019-06-02 21:20:46',1);
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `describe` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
INSERT INTO `t_category` VALUES (1,'book','书籍漫画'),(2,'watch','手表'),(3,'stamp','邮票及钱币'),(4,'wine','葡萄酒及威士忌');
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `winner_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (1,1,3200.00,1,1,'2019-05-15 18:46:20','book'),(7,4,3702.00,1,2,'2019-05-17 21:08:35','book');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_stamp`
--

DROP TABLE IF EXISTS `t_stamp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_stamp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `category` varchar(45) NOT NULL DEFAULT 'stamp',
  `winner_id` int(11) DEFAULT NULL,
  `max_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `price` decimal(10,2) DEFAULT NULL,
  `introduce` varchar(200) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '1：表示未卖出\\n2：已卖出',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_stamp`
--

LOCK TABLES `t_stamp` WRITE;
/*!40000 ALTER TABLE `t_stamp` DISABLE KEYS */;
INSERT INTO `t_stamp` VALUES (1,1,'stamp',NULL,0.00,3560.00,'比利时 1869/1883 - Leopold II issue 1869 - OBP 37','img/stamp/stamp1.PNG','2019-06-04 00:58:41',1),(2,2,'stamp',NULL,0.00,605.00,'比利时 1849 - Leopold I epaulettes - OBP 2','img/stamp/stamp2.PNG','2019-06-02 01:34:09',1),(3,2,'stamp',NULL,0.00,85.00,'同盟国军事占领德国(苏维埃区域） 1946 - West Saxony','img/stamp/stamp3.PNG','2019-06-03 01:36:31',1),(4,1,'stamp',NULL,0.00,56.00,'东德 1950/1955 - Blockausgaben, seven pieces - Michel Blok 7','img/stamp/stamp4.PNG','2019-06-04 01:37:32',1),(5,1,'stamp',NULL,0.00,47.00,'撒丁岛 1858 - 10 cents umber - Sassone N. 14','img/stamp/stamp5.PNG','2019-06-01 01:39:07',1);
/*!40000 ALTER TABLE `t_stamp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `bought_number` int(100) NOT NULL DEFAULT '0',
  `auction_number` int(100) NOT NULL DEFAULT '0',
  `admin` int(11) NOT NULL DEFAULT '1' COMMENT '0为管理员，1为普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'xudawu','123','18681698888','绵阳市涪城区西南科技大学',2,8,0),(2,'aimer','123','18681690000','四川省绵阳市',0,6,0),(3,'rhythm','123',NULL,NULL,0,2,1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_watch`
--

DROP TABLE IF EXISTS `t_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_watch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1',
  `user_id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL DEFAULT 'watch',
  `winner_id` int(11) DEFAULT NULL,
  `max_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `price` decimal(10,2) DEFAULT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '1：表示未卖出\\\\n2：表示卖出，默认为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_watch`
--

LOCK TABLES `t_watch` WRITE;
/*!40000 ALTER TABLE `t_watch` DISABLE KEYS */;
INSERT INTO `t_watch` VALUES (1,1,'watch',0,0.00,13000.00,'IWC - Da Vinci Rattrapante Perpetual Calender LIMITED - 375401 - X','img/watch/watch_iwc_1.PNG','2019-06-02 15:30:29',1),(2,1,'watch',0,0.00,2300.00,'OysterDate Precision - 6694- 1960-1969','img/watch/watch_rolex_1.PNG','2019-06-04 15:30:29',1),(3,3,'watch',0,0.00,459.00,'Eterna - Artena Lady - 2510.41.45.0273 - 女士 - 2011至现在','img/watch/watch_eterna_1.PNG','2019-06-05 15:30:29',1),(4,3,'watch',0,0.00,2068.00,'Rapport London - Paramount Nine Watch Winder - W409 - 中性 - 2011至现在','img/watch/watch_rapport_1.PNG','2019-06-01 15:30:29',1),(5,2,'watch',0,0.00,2681.00,'Chopard - St Moritz Gold/Steel - 8024 - 女士 - 2000-2010','img/watch/watch_chopard_1.PNG','2019-06-02 15:30:29',1);
/*!40000 ALTER TABLE `t_watch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wine`
--

DROP TABLE IF EXISTS `t_wine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_wine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `category` varchar(45) NOT NULL DEFAULT 'wine',
  `winner_id` int(11) DEFAULT NULL,
  `max_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `price` decimal(10,2) DEFAULT NULL,
  `introduce` varchar(200) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '1：表示未卖出\\n2：已卖出',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wine`
--

LOCK TABLES `t_wine` WRITE;
/*!40000 ALTER TABLE `t_wine` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_wine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'auction2019_05_22'
--

--
-- Dumping routines for database 'auction2019_05_22'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-26 11:23:55
