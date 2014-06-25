--// Bootstrap.sql

-- This is the only SQL script file that is NOT
-- a valid migration and will not be run or tracked
-- in the changelog.  There is no @UNDO section.

--// Do I need this file?

-- New projects likely won't need this file.
-- Existing projects will likely need this file.
-- It's unlikely that this bootstrap should be run
-- in the production environment.

--// Purpose

-- The purpose of this file is to provide a facility
-- to initialize the database to a state before MyBatis
-- SQL migrations were applied.  If you already have
-- a database in production, then you probably have
-- a script that you run on your developer machine
-- to initialize the database.  That script can now
-- be put in this bootstrap file (but does not have
-- to be if you are comfortable with your current process.

--// Running

-- The bootstrap SQL is run with the "migrate bootstrap"
-- command.  It must be run manually, it's never run as
-- part of the regular migration process and will never
-- be undone. Variables (e.g. ${variable}) are still
-- parsed in the bootstrap SQL.

-- After the boostrap SQL has been run, you can then
-- use the migrations and the changelog for all future
-- database change management.

-- MySQL dump 10.13  Distrib 5.5.25a, for Win64 (x86)
--
-- Host: localhost    Database: developers
-- ------------------------------------------------------
-- Server version	5.5.25a

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info` (
  `user` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `photo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_info_user1` (`user`),
  CONSTRAINT `fk_info_user1` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` VALUES (1,'a@a.a','584photo.png'),(2,'j@k.k','user_no_avatar.png'),(3,'d@s.s','user_no_avatar.png'),(4,'Romanmal@ukr.net','450images1tt.jpg'),(6,'postullat@i.ua','155FD_e_kT.jpg'),(7,'Tiote@ukr.net','user_no_avatar.png'),(8,'olko@i.ua','user_no_avatar.png'),(9,'test@test.com','user_no_avatar.png'),(10,'k@j.j','user_no_avatar.png'),(18,'p@i.ua','user_no_avatar.png'),(20,'Roman@hhjd.fgf','user_no_avatar.png'),(21,'dsd@fdf','user_no_avatar.png'),(22,'yy@yy','user_no_avatar.png'),(26,'Volodymyr_Bondarchuk@epam.com','user_no_avatar.png'),(27,'ooo@oo.oo','user_no_avatar.png'),(28,'qwe@ii.ua','user_no_avatar.png'),(29,'qq@qq','user_no_avatar.png'),(30,'redirect@redirect','user_no_avatar.png'),(31,'postullat@rrrr.ua','user_no_avatar.png'),(32,'www@ww.ua','user_no_avatar.png');
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `user_id` varchar(20) NOT NULL,
  `dated` varchar(100) NOT NULL,
  `logger` varchar(100) NOT NULL,
  `level` varchar(100) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES ('','2014-06-11 15:43:52,720','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-11 15:43:52,937','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-11 15:52:37,228','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-11 15:52:37,394','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-11 15:53:47,283','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-11 15:53:47,287','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-11 15:54:15,735','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-11 15:54:15,736','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-11 16:01:08,331','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-11 16:01:08,534','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 10:50:05,138','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 10:50:05,335','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 10:50:14,232','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-12 10:53:26,768','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 10:53:26,945','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 10:53:28,347','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-12 10:53:48,977','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 10:53:48,982','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 10:53:53,415','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-12 13:55:50,296','com.epam.lab.developers.servlet.Register','DEBUG','Such email doesn t correct qq!qq'),('','2014-06-12 13:56:15,228','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 13:56:15,250','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 13:56:43,067','com.epam.lab.developers.servlet.CreateGame','DEBUG','qqq has created the game'),('','2014-06-12 13:57:32,466','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 13:57:32,471','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 13:57:39,123','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg join to game:game 1'),('','2014-06-12 16:05:31,730','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 16:05:31,962','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 16:05:35,567','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe has created the game'),('','2014-06-12 16:06:36,811','com.epam.lab.developers.servlet.Login','DEBUG','Hello world debug'),('','2014-06-12 16:06:36,813','com.epam.lab.developers.servlet.Login','INFO','Hello world info'),('','2014-06-12 16:06:37,907','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg join to game:game 1'),('','2014-06-13 17:18:41,454','com.epam.lab.developers.servlet.Register','DEBUG','Such user with nicknameomg is already registered in system.\nPlease try to input another nickname'),('','2014-06-13 17:18:41,480','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:18:41,488','com.epam.lab.developers.servlet.Register','DEBUG','Maximum length: 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"), must begin and end with a letter or digit'),('','2014-06-13 17:19:09,323','com.epam.lab.developers.servlet.Register','DEBUG','Such user with nicknameomg is already registered in system.\nPlease try to input another nickname'),('','2014-06-13 17:19:09,361','com.epam.lab.developers.servlet.Register','DEBUG','Passwords must be at least 3 character and less than 25'),('','2014-06-13 17:19:09,393','com.epam.lab.developers.servlet.Register','DEBUG','Maximum length: 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"), must begin and end with a letter or digit'),('','2014-06-13 17:22:15,543','com.epam.lab.developers.servlet.Register','DEBUG','<br>Such user with nickname omg is already registered in system.<br>Please try to input another nickname'),('','2014-06-13 17:22:15,584','com.epam.lab.developers.servlet.Register','DEBUG','<br>Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),<br>must begin and end with a letter or digit'),('','2014-06-13 17:22:54,024','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:22:54,071','com.epam.lab.developers.servlet.Register','DEBUG','<br>Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),<br>must begin and end with a letter or digit'),('','2014-06-13 17:25:20,047','com.epam.lab.developers.servlet.Register','DEBUG','<br>Such user with nickname omg is already registered in system.<br>Please try to input another nickname'),('','2014-06-13 17:25:20,066','com.epam.lab.developers.servlet.Register','DEBUG','<br>Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),<br>must begin and end with a letter or digit'),('','2014-06-13 17:27:28,024','com.epam.lab.developers.servlet.Register','DEBUG','<br>Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),<br>must begin and end with a letter or digit'),('','2014-06-13 17:29:08,313','com.epam.lab.developers.servlet.Register','DEBUG','<br>Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),<br>must begin and end with a letter or digit'),('','2014-06-13 17:30:24,168','com.epam.lab.developers.servlet.Register','DEBUG','<br>Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),<br>must begin and end with a letter or digit'),('','2014-06-13 17:34:46,977','com.epam.lab.developers.servlet.Register','DEBUG','\nEmail max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit'),('','2014-06-13 17:36:56,371','com.epam.lab.developers.servlet.Register','DEBUG','\nThe password can'),('','2014-06-13 17:36:56,396','com.epam.lab.developers.servlet.Register','DEBUG','\nEmail max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit'),('','2014-06-13 17:40:10,722','com.epam.lab.developers.servlet.Register','DEBUG','The password can not be empty.'),('','2014-06-13 17:40:10,740','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:41:12,571','com.epam.lab.developers.servlet.Register','DEBUG','The password can not be empty.'),('','2014-06-13 17:41:12,615','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:43:56,301','com.epam.lab.developers.servlet.Register','DEBUG','The password can not be empty.'),('','2014-06-13 17:43:56,328','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:44:08,272','com.epam.lab.developers.servlet.Register','DEBUG','The passwords you entered do not match.'),('','2014-06-13 17:44:08,317','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:44:14,573','com.epam.lab.developers.servlet.Register','DEBUG','Passwords must be at least 3 character and less than 25.'),('','2014-06-13 17:44:14,574','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:44:20,151','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:44:20,152','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:44:30,001','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:44:30,002','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:44:38,862','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:44:38,864','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:44:47,181','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:45:44,945','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:45:55,386','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:48:35,313','com.epam.lab.developers.servlet.Register','DEBUG','The password can not be empty.'),('','2014-06-13 17:48:35,329','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:49:04,814','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:50:28,143','com.epam.lab.developers.servlet.Register','DEBUG',''),('','2014-06-13 17:56:14,593','com.epam.lab.developers.servlet.Register','DEBUG','The password can not be empty.'),('','2014-06-13 17:56:14,608','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-13 17:56:20,183','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-16 11:19:34,512','com.epam.lab.developers.servlet.Login','DEBUG','User omg is already logged in system.'),('','2014-06-16 11:26:32,686','com.epam.lab.developers.servlet.Login','DEBUG','User PASSWORD can not be EMPTY.'),('','2014-06-16 11:26:41,580','com.epam.lab.developers.servlet.Login','DEBUG','User NAME can not be EMPTY.'),('','2014-06-16 17:09:49,375','com.epam.lab.developers.servlet.CreateGame','DEBUG','yy has created the game'),('','2014-06-16 17:10:04,450','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg join to game:game 1'),('','2014-06-16 17:20:09,219','com.epam.lab.developers.data.DataHolder','DEBUG','yy exit the gamegame 1 date of create Mon Jun 16 17:09:39 EEST 2014 creator:com.epam.lab.developers.entity.User@510a97fb'),('','2014-06-16 17:20:17,928','com.epam.lab.developers.data.DataHolder','DEBUG','omg exit the gamegame 1 date of create Mon Jun 16 17:09:39 EEST 2014 creator:com.epam.lab.developers.entity.User@510a97fb'),('','2014-06-16 17:20:19,315','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-16 17:20:22,333','com.epam.lab.developers.servlet.CreateGame','DEBUG','yy join to game:game 1'),('','2014-06-16 17:49:52,252','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-16 17:50:18,883','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-20 12:16:13,457','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 14:54:23,805','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 14:55:25,849','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-20 15:01:57,097','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 15:06:47,892','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 15:07:20,775','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-20 15:10:54,285','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 15:11:13,931','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-20 15:16:26,840','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 15:16:57,733','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-20 15:20:31,937','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-20 15:20:56,498','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-20 15:22:35,795','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 11:58:43,571','com.epam.lab.developers.servlet.Login','DEBUG','User NAME can not be EMPTY.'),('','2014-06-23 11:58:46,417','com.epam.lab.developers.servlet.Login','DEBUG','User NAME can not be EMPTY.'),('','2014-06-23 11:58:47,851','com.epam.lab.developers.servlet.Login','DEBUG','User NAME can not be EMPTY.'),('','2014-06-23 11:59:38,584','com.epam.lab.developers.servlet.Login','DEBUG','User PASSWORD can not be EMPTY.'),('','2014-06-23 12:59:48,053','com.epam.lab.developers.servlet.Login','DEBUG','User PASSWORD can not be EMPTY.'),('','2014-06-23 13:00:11,095','com.epam.lab.developers.servlet.Register','DEBUG','User name should be more than 3 and less than 10 characters.'),('','2014-06-23 13:00:13,090','com.epam.lab.developers.servlet.Register','DEBUG','User name should be more than 3 and less than 10 characters.'),('','2014-06-23 13:00:15,606','com.epam.lab.developers.servlet.Register','DEBUG','User name should be more than 3 and less than 10 characters.'),('','2014-06-23 13:00:21,262','com.epam.lab.developers.servlet.Register','DEBUG','The passwords you entered do not match.'),('','2014-06-23 13:00:27,278','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-23 13:01:32,174','com.epam.lab.developers.servlet.Register','DEBUG','The password can not be empty.'),('','2014-06-23 13:01:36,147','com.epam.lab.developers.servlet.Register','DEBUG','Email max letght is 20 characters (\"a-z\", \"A-Z\", \"0-9\", \"-\", \"_\", \".\"),\nmust begin and end with a letter or digit.'),('','2014-06-23 14:42:11,947','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 14:48:15,689','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 14:48:44,358','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg join to game:game 1'),('','2014-06-23 14:54:40,650','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:47:35,718','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:48:20,195','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:51:06,960','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:54:02,206','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:54:54,253','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:55:32,755','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 15:57:51,275','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:01:21,716','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:05:18,242','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:12:53,680','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:13:56,265','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:14:16,373','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:23:16,606','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:23:35,495','com.epam.lab.developers.data.DataHolder','DEBUG','omg exit the gamegame 1 date of create Mon Jun 23 16:23:16 EEST 2014 creator:com.epam.lab.developers.entity.User@47d64706'),('','2014-06-23 16:35:48,545','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:36:04,070','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-23 16:37:39,240','com.epam.lab.developers.data.DataHolder','DEBUG','qwe exit the gamegame 1 date of create Mon Jun 23 16:35:48 EEST 2014 creator:com.epam.lab.developers.entity.User@108a7345'),('','2014-06-23 16:41:41,283','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:41:47,193','com.epam.lab.developers.data.DataHolder','DEBUG','omg exit the gamegame 1 date of create Mon Jun 23 16:41:41 EEST 2014 creator:com.epam.lab.developers.entity.User@7d951ea3'),('','2014-06-23 16:42:00,281','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:42:26,922','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg join to game:game 1'),('','2014-06-23 16:43:22,355','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe has created the game'),('','2014-06-23 16:43:27,699','com.epam.lab.developers.data.DataHolder','DEBUG','omg exit the gamegame 1 date of create Mon Jun 23 16:42:00 EEST 2014 creator:com.epam.lab.developers.entity.User@730d87c7'),('','2014-06-23 16:43:37,425','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg join to game:game 2'),('','2014-06-23 16:55:44,527','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe has created the game'),('','2014-06-23 16:57:50,509','com.epam.lab.developers.servlet.CreateGame','DEBUG','postullat join to game:game 1'),('','2014-06-23 16:59:47,751','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 16:59:56,634','com.epam.lab.developers.data.DataHolder','DEBUG','omg exit the gamegame 1 date of create Mon Jun 23 16:59:47 EEST 2014 creator:com.epam.lab.developers.entity.User@37f28e66'),('','2014-06-23 17:00:09,497','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:00:25,730','com.epam.lab.developers.data.DataHolder','DEBUG','omg exit the gamegame 1 date of create Mon Jun 23 17:00:09 EEST 2014 creator:com.epam.lab.developers.entity.User@37f28e66'),('','2014-06-23 17:13:34,762','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:16:28,330','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:18:09,740','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:18:27,906','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-23 17:21:05,134','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:21:14,106','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-23 17:23:11,151','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:23:23,386','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-23 17:28:32,493','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:29:00,148','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-23 17:33:29,985','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-23 17:33:39,582','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-25 16:57:02,507','com.epam.lab.developers.servlet.CreateGame','DEBUG','omg has created the game'),('','2014-06-25 16:58:35,775','com.epam.lab.developers.servlet.CreateGame','DEBUG','qwe join to game:game 1'),('','2014-06-25 16:58:48,622','com.epam.lab.developers.data.DataHolder','DEBUG','qwe exit the gamegame 1 date of create Wed Jun 25 16:57:02 EEST 2014 creator:com.epam.lab.developers.entity.User@4506ffbb');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `map`
--

DROP TABLE IF EXISTS `map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `map` (
  `id` int(11) NOT NULL,
  `rows` int(11) DEFAULT NULL,
  `columns` varchar(45) DEFAULT NULL,
  `frame_width` int(11) DEFAULT NULL,
  `frame_height` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `player_count` int(11) DEFAULT NULL,
  `floor_texture` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map`
--

LOCK TABLES `map` WRITE;
/*!40000 ALTER TABLE `map` DISABLE KEYS */;
INSERT INTO `map` VALUES (0,20,'15',50,50,'test',2,'resources/img/texture/floor/floor_grey.png');
/*!40000 ALTER TABLE `map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `object` (
  `id` int(11) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` VALUES (0,'resources/img/texture/floor/floor_grey.png','floor_grey'),(1,'resources/img/texture/walls/left_wall_grey.png','left_wall_grey'),(2,'resources/img/texture/walls/left_up_corner_grey.png','left_up_corner_grey'),(3,'resources/img/texture/objects/cofee_mashine_desk_red.png','cofee_mashine_desk_red'),(4,'resources/img/texture/objects/desk.png','desk'),(5,'resources/img/texture/objects/desk_grey.png','desk_grey'),(6,'resources/img/texture/objects/notebook_grey.png','notebook_grey'),(7,'resources/img/texture/objects/printer_1_grey.png','printer_1_grey'),(9,'resources/img/texture/objects/sink_grey.png','sink_grey'),(10,'resources/img/texture/objects/toilet_grey.png','toilet_grey'),(11,'resources/img/texture/objects/cookstove.png','cookstove'),(12,'resources/img/texture/objects/refrigerator_grey.png','refrigerator_grey'),(13,'resources/img/texture/objects/server_grey.png','server_grey'),(14,'resources/img/texture/objects/coffe_mashine_desk_grey.png','coffe_mashine_desk_grey'),(15,'resources/img/texture/objects/coffe_desk_grey.png','coffe_desk_grey');
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passive_object`
--

DROP TABLE IF EXISTS `passive_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passive_object` (
  `id` int(11) NOT NULL,
  `id_map` int(11) NOT NULL,
  `id_object` int(11) NOT NULL,
  `i` int(11) DEFAULT NULL,
  `j` int(11) DEFAULT NULL,
  `rotation_angle` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_passive_object_map1` (`id_map`),
  KEY `fk_passive_object_object1` (`id_object`),
  CONSTRAINT `fk_passive_object_map1` FOREIGN KEY (`id_map`) REFERENCES `map` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_passive_object_object1` FOREIGN KEY (`id_object`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passive_object`
--

LOCK TABLES `passive_object` WRITE;
/*!40000 ALTER TABLE `passive_object` DISABLE KEYS */;
INSERT INTO `passive_object` VALUES (0,0,2,0,0,0),(1,0,1,1,0,90),(2,0,1,2,0,90),(3,0,1,3,0,90),(4,0,1,4,0,90),(5,0,1,5,0,90),(6,0,2,6,0,90),(7,0,1,7,0,90),(8,0,1,8,0,90),(9,0,1,9,0,90),(10,0,2,10,0,90),(11,0,1,11,0,90),(12,0,1,12,0,90),(13,0,1,13,0,90),(14,0,1,14,0,90),(15,0,2,15,0,90),(16,0,1,16,0,90),(17,0,1,17,0,90),(18,0,1,18,0,90),(19,0,2,19,0,90),(20,0,1,0,1,0),(21,0,1,6,1,180),(22,0,1,10,1,180),(23,0,1,15,1,180),(24,0,1,19,1,180),(25,0,1,0,2,0),(26,0,1,6,2,180),(27,0,1,10,2,180),(28,0,1,15,2,180),(29,0,1,19,2,180),(30,0,1,0,3,0),(31,0,1,6,3,180),(32,0,1,10,3,180),(33,0,1,15,3,180),(34,0,1,19,3,180),(35,0,1,0,4,0),(36,0,1,6,4,180),(37,0,1,10,4,180),(38,0,1,15,4,180),(39,0,1,19,4,180),(40,0,2,0,5,270),(41,0,1,1,5,270),(42,0,1,2,5,270),(43,0,1,3,5,270),(44,0,1,4,5,270),(45,0,1,17,5,270),(46,0,1,18,5,270),(47,0,2,19,5,180),(48,0,1,0,6,0),(49,0,1,19,6,180),(50,0,1,0,7,0),(51,0,1,4,7,180),(52,0,1,19,7,180),(53,0,2,0,8,270),(54,0,1,1,8,270),(55,0,1,2,8,270),(56,0,1,3,8,270),(57,0,2,4,8,180),(58,0,1,19,8,180),(59,0,1,0,9,0),(60,0,1,15,9,90),(61,0,1,16,9,90),(62,0,1,17,9,90),(63,0,1,18,9,90),(64,0,2,19,9,90),(65,0,1,0,10,0),(66,0,1,6,10,180),(67,0,1,7,10,90),(68,0,1,9,10,90),(69,0,1,10,10,90),(70,0,1,11,10,90),(71,0,1,12,10,90),(72,0,1,13,10,0),(73,0,1,19,10,180),(74,0,1,0,11,0),(75,0,1,6,11,180),(76,0,1,13,11,0),(77,0,1,19,11,180),(78,0,1,0,12,0),(79,0,1,6,12,180),(80,0,1,13,12,0),(81,0,1,19,12,180),(82,0,1,0,13,0),(83,0,1,6,13,180),(84,0,1,13,13,0),(85,0,1,19,13,180),(86,0,2,0,14,270),(87,0,1,1,14,270),(88,0,1,2,14,270),(89,0,1,3,14,270),(90,0,1,4,14,270),(91,0,1,5,14,270),(92,0,2,6,14,180),(93,0,1,7,14,270),(94,0,1,8,14,270),(95,0,1,9,14,270),(96,0,1,10,14,270),(97,0,1,11,14,270),(98,0,1,12,14,270),(99,0,2,13,14,270),(100,0,1,14,14,270),(101,0,1,15,14,270),(102,0,1,16,14,270),(103,0,1,17,14,270),(104,0,1,18,14,270),(105,0,2,19,14,180),(106,0,5,1,1,0),(107,0,5,18,13,0),(108,0,15,1,11,270),(109,0,15,7,12,270);
/*!40000 ALTER TABLE `passive_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stats` (
  `user` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `winnings` int(11) DEFAULT NULL,
  `losings` int(11) DEFAULT NULL,
  PRIMARY KEY (`user`),
  KEY `fk_stats_user1` (`user`),
  CONSTRAINT `fk_stats_user1` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `id_map` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_map` (`id_map`),
  CONSTRAINT `fk_team_map` FOREIGN KEY (`id_map`) REFERENCES `map` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (0,0),(1,0);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_object`
--

DROP TABLE IF EXISTS `team_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_object` (
  `id` int(11) NOT NULL,
  `id_object` int(11) NOT NULL,
  `i` int(11) DEFAULT NULL,
  `j` int(11) DEFAULT NULL,
  `rotation_angle` int(11) DEFAULT NULL,
  `id_team` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_object_object1` (`id_object`),
  KEY `fk_team_object_team1` (`id_team`),
  CONSTRAINT `fk_team_object_object1` FOREIGN KEY (`id_object`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_object_team1` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_object`
--

LOCK TABLES `team_object` WRITE;
/*!40000 ALTER TABLE `team_object` DISABLE KEYS */;
INSERT INTO `team_object` VALUES (0,6,2,1,0,0),(1,6,5,1,90,0),(2,6,5,2,90,0),(3,6,1,3,270,0),(4,6,1,4,270,0),(5,6,18,11,90,1),(6,6,18,10,90,1),(7,6,17,13,180,1),(8,6,14,13,270,1),(9,6,14,12,270,1),(10,10,1,6,0,0),(11,10,16,1,270,1),(12,9,2,6,0,0),(13,9,16,3,270,1),(14,11,2,13,180,0),(15,11,9,13,180,1),(16,12,4,13,180,0),(17,12,11,13,180,1),(18,13,8,1,180,0),(19,13,18,7,270,1),(20,7,9,1,0,0),(21,7,18,8,90,1),(23,14,1,10,270,1),(25,14,7,11,270,1),(26,5,3,10,0,0),(27,5,4,10,0,0),(28,5,10,11,0,1),(29,5,11,11,0,1);
/*!40000 ALTER TABLE `team_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_unit`
--

DROP TABLE IF EXISTS `team_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_unit` (
  `id` int(11) NOT NULL,
  `id_team` int(11) NOT NULL,
  `id_unit` int(11) NOT NULL,
  `i` int(11) DEFAULT NULL,
  `j` int(11) DEFAULT NULL,
  `rotation_angle` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_unit_unit1` (`id_unit`),
  KEY `fk_team_unit_team1` (`id_team`),
  CONSTRAINT `fk_team_unit_team1` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_unit_unit1` FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_unit`
--

LOCK TABLES `team_unit` WRITE;
/*!40000 ALTER TABLE `team_unit` DISABLE KEYS */;
INSERT INTO `team_unit` VALUES (1,0,0,2,2,180),(2,0,0,2,3,90),(3,0,1,2,4,90),(4,0,1,4,1,270),(5,0,2,4,2,270),(6,1,3,17,12,0),(7,1,3,17,11,270),(8,1,4,17,10,270),(9,1,4,15,13,90),(10,1,5,15,12,90);
/*!40000 ALTER TABLE `team_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `stand_texture` varchar(100) DEFAULT NULL,
  `texture1` varchar(100) DEFAULT NULL,
  `texture2` varchar(100) DEFAULT NULL,
  `texture3` varchar(100) DEFAULT NULL,
  `texture4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (0,'Junior Developer','resources/img/texture/unit/greeen_team/stand_junior_green.png','resources/img/texture/unit/greeen_team/walkin_1_junior_green.png','resources/img/texture/unit/greeen_team/walkin_2_junior_green.png','resources/img/texture/unit/greeen_team/walkin_3_junior_green.png','resources/img/texture/unit/greeen_team/walkin_4_junior_green.png'),(1,'Middle Developer','resources/img/texture/unit/greeen_team/stand_mid_green.png','resources/img/texture/unit/greeen_team/walkin_1_mid_green.png','resources/img/texture/unit/greeen_team/walkin_2_mid_green.png','resources/img/texture/unit/greeen_team/walkin_3_mid_green.png','resources/img/texture/unit/greeen_team/walkin_4_mid_green.png'),(2,'Senior Developer','resources/img/texture/unit/greeen_team/stand_senior_green.png','resources/img/texture/unit/greeen_team/walkin_1_senior_green.png','resources/img/texture/unit/greeen_team/walkin_2_senior_green.png','resources/img/texture/unit/greeen_team/walkin_3_senior_green.png','resources/img/texture/unit/greeen_team/walkin_4_senior_green.png'),(3,'Junior Developer','resources/img/texture/unit/grey_team/stand_junior_grey.png','resources/img/texture/unit/grey_team/walkin_1_junior_grey.png','resources/img/texture/unit/grey_team/walkin_2_junior_grey.png','resources/img/texture/unit/grey_team/walkin_3_junior_grey.png','resources/img/texture/unit/grey_team/walkin_4_junior_grey.png'),(4,'Middle Developer','resources/img/texture/unit/grey_team/stand_mid_grey.png','resources/img/texture/unit/grey_team/walkin_1_mid_grey.png','resources/img/texture/unit/grey_team/walkin_2_mid_grey.png','resources/img/texture/unit/grey_team/walkin_3_mid_grey.png','resources/img/texture/unit/grey_team/walkin_4_mid_grey.png'),(5,'Senior Developer','resources/img/texture/unit/grey_team/stand_senior_grey.png','resources/img/texture/unit/grey_team/walkin_1_senior_grey.png','resources/img/texture/unit/grey_team/walkin_2_senior_grey.png','resources/img/texture/unit/grey_team/walkin_3_senior_grey.png','resources/img/texture/unit/grey_team/walkin_4_senior_grey.png');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ostap','202cb962ac59075b964b07152d234b70'),(2,'omg','202cb962ac59075b964b07152d234b70'),(3,'omg1','202cb962ac59075b964b07152d234b70'),(4,'Roman','b59c67bf196a4758191e42f76670ceba'),(6,'postullat','827ccb0eea8a706c4c34a16891f84e7b'),(7,'Tiote','b59c67bf196a4758191e42f76670ceba'),(8,'olko','202cb962ac59075b964b07152d234b70'),(9,'test','202cb962ac59075b964b07152d234b70'),(10,'<>','698d51a19d8a121ce581499d7b701668'),(11,'!','c4ca4238a0b923820dcc509a6f75849b'),(12,'+-=','d41d8cd98f00b204e9800998ecf8427e'),(15,'===','7ce4f5cca0e937c88b050a8bba83218e'),(16,'==','2063849717d32dd19e534b77cabac517'),(18,'123','202cb962ac59075b964b07152d234b70'),(19,'','d41d8cd98f00b204e9800998ecf8427e'),(20,'Roman12','b59c67bf196a4758191e42f76670ceba'),(21,'Roman11','b59c67bf196a4758191e42f76670ceba'),(22,'yy','2fb1c5cf58867b5bbc9a1b145a86f3a0'),(26,'Volodymyr_Bondarchuk','827ccb0eea8a706c4c34a16891f84e7b'),(27,'ooo','7f94dd413148ff9ac9e9e4b6ff2b6ca9'),(28,'qwe','202cb962ac59075b964b07152d234b70'),(29,'qqq','202cb962ac59075b964b07152d234b70'),(30,'redirect','f17ca2c829680ada2fec9fc87bc5f606'),(31,'validated','202cb962ac59075b964b07152d234b70'),(32,'www','4eae35f1b35977a00ebd8086c259d4c9');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'developers'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-25 18:16:40
