start transaction;
DROP DATABASE IF EXISTS `Acme-Overflow`;
CREATE DATABASE `Acme-Overflow`;
USE 'Acme-Overflow';
CREATE USER 'acme-user'@'%'
  IDENTIFIED BY PASSWORD '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
CREATE USER 'acme-manager'@'%'
  IDENTIFIED BY PASSWORD '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';
GRANT SELECT, INSERT, UPDATE, DELETE
ON `Acme-Overflow`.* TO 'acme-user'@'%';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER,
CREATE TEMPORARY TABLES, LOCK TABLES, CREATE VIEW, CREATE ROUTINE,
ALTER ROUTINE, EXECUTE, TRIGGER, SHOW VIEW
ON `Acme-Overflow`.* TO 'acme-manager'@'%';


# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.53)
# Base de datos: Acme-Overflow
# Tiempo de Generación: 2017-05-28 11:41:17 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla Administrator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  KEY `FK_gl4ryvfr1pd7798c3kuo22hvb` (`creditCard_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`),
  CONSTRAINT `FK_gl4ryvfr1pd7798c3kuo22hvb` FOREIGN KEY (`creditCard_id`) REFERENCES `CreditCard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Administrator` WRITE;
/*!40000 ALTER TABLE `Administrator` DISABLE KEYS */;

INSERT INTO `Administrator` (`id`, `version`, `email`, `name`, `phoneNumber`, `surname`, `creditCard_id`, `userAccount_id`)
VALUES
	(202,0,'admin@gmail.com','admin','+23-6234456456','admin sur',NULL,185),
	(207,0,'admin2@gmail.com','admin2','+23-6234456456','admin2 sur',NULL,186),
	(212,0,'admin3@gmail.com','admin3','+23-6234456456','admin3 sur',NULL,187);

/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Answer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Answer`;

CREATE TABLE `Answer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `description` longtext,
  `dislikes` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  `teacher` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_10g8xii7lw9kq0kcsobgmtw72` (`question_id`),
  CONSTRAINT `FK_10g8xii7lw9kq0kcsobgmtw72` FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Answer` WRITE;
/*!40000 ALTER TABLE `Answer` DISABLE KEYS */;

INSERT INTO `Answer` (`id`, `version`, `banned`, `description`, `dislikes`, `likes`, `teacher`, `title`, `owner_id`, `question_id`)
VALUES
	(227,0,b'0','descrip1',12,112,b'0','answ1',228,248),
	(230,0,b'1','descrip5',1442,8765,b'1','answ5',231,244),
	(243,0,b'1','descrip6',1,8765,b'1','answ6',240,244),
	(250,0,b'0','descrip4',0,1222,b'1','answ4',240,248),
	(255,1,b'0','descrip2',2,1121,b'0','answ2',225,224),
	(256,1,b'0','descrip3',212,11,b'1','answ3',257,224);

/*!40000 ALTER TABLE `Answer` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Answer_pictures
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Answer_pictures`;

CREATE TABLE `Answer_pictures` (
  `Answer_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_noxm9e1pbopowsb1yqpv0ib2e` (`Answer_id`),
  CONSTRAINT `FK_noxm9e1pbopowsb1yqpv0ib2e` FOREIGN KEY (`Answer_id`) REFERENCES `Answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Answer_pictures` WRITE;
/*!40000 ALTER TABLE `Answer_pictures` DISABLE KEYS */;

INSERT INTO `Answer_pictures` (`Answer_id`, `pictures`)
VALUES
	(250,'http://www.pic.jpg'),
	(250,'http://www.pic3.jpg'),
	(255,'http://www.pic.jpg'),
	(255,'http://www.pic3.jpg'),
	(256,'http://www.pic.jpg'),
	(256,'http://www.pic3.jpg'),
	(243,'http://www.pic.jpg'),
	(243,'http://www.pic3.jpg'),
	(230,'http://www.pic.jpg'),
	(230,'http://www.pic3.jpg'),
	(227,'http://www.pic.jpg'),
	(227,'http://www.pic3.jpg');

/*!40000 ALTER TABLE `Answer_pictures` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Banner
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Banner`;

CREATE TABLE `Banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Banner` WRITE;
/*!40000 ALTER TABLE `Banner` DISABLE KEYS */;

INSERT INTO `Banner` (`id`, `version`, `url`)
VALUES
	(315,0,'http://i.imgur.com/UY1x8KM.png'),
	(316,0,'http://i.imgur.com/LtQYadW.png'),
	(317,0,'http://i.imgur.com/eViE38q.png'),
	(318,0,'http://i.imgur.com/IDRtMzG.png');

/*!40000 ALTER TABLE `Banner` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Bill`;

CREATE TABLE `Bill` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `webinar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_811tlcybqsn1kbxia2uo6c8gs` (`webinar_id`),
  CONSTRAINT `FK_811tlcybqsn1kbxia2uo6c8gs` FOREIGN KEY (`webinar_id`) REFERENCES `Webinar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;

INSERT INTO `Bill` (`id`, `version`, `number`, `value`, `owner_id`, `webinar_id`)
VALUES
	(365,0,'2334sf43',20,228,289),
	(366,0,'4334sf43',20,225,289),
	(367,0,'6734sf43',120,219,222),
	(368,0,'7834sf43',1,253,273);

/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Category`;

CREATE TABLE `Category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;

INSERT INTO `Category` (`id`, `version`, `description`, `name`)
VALUES
	(223,0,'Lorem ipsum dolor sit amet.','info'),
	(245,0,'Lorem ipsum dolor sit amet.','hardware'),
	(247,0,'Lorem ipsum dolor sit amet.','software');

/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Comment`;

CREATE TABLE `Comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationDate` date DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `webinar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bop53f9h6lbfllx1bx0btytx5` (`webinar_id`),
  CONSTRAINT `FK_bop53f9h6lbfllx1bx0btytx5` FOREIGN KEY (`webinar_id`) REFERENCES `Webinar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;

INSERT INTO `Comment` (`id`, `version`, `creationDate`, `text`, `title`, `owner_id`, `webinar_id`)
VALUES
	(221,0,'2017-04-22','insulso','psss',219,222),
	(274,0,'2017-04-14','corre a la parfección','que guay, va estupendo',253,273),
	(275,0,'2017-05-22','woooooooohh','chachi',253,273),
	(290,0,'2017-04-22','works 4 me!','maravilloso',225,289),
	(291,0,'2017-05-26','he probado, pero no funciona','no se como hacerlo',228,289),
	(292,0,'2017-04-22','works fine','gr8!',225,289),
	(293,0,'2017-04-22','works 4 me pretty well!','awesome',219,289);

/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla CreditCard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CreditCard`;

CREATE TABLE `CreditCard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `CVV` varchar(255) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `month` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `CreditCard` WRITE;
/*!40000 ALTER TABLE `CreditCard` DISABLE KEYS */;

INSERT INTO `CreditCard` (`id`, `version`, `CVV`, `holderName`, `month`, `number`, `type`, `year`)
VALUES
	(220,0,'432','user3',2,'54275498043695577',4,2018),
	(226,0,'432','user2',2,'54275498043695577',3,2018),
	(229,0,'432','user1',2,'54275498043695577',0,2018),
	(232,0,'432','teacher2',2,'54275498043695577',2,2018),
	(241,0,'432','teacher1',2,'54275498043695577',0,2018),
	(254,0,'432','user4',2,'54275498043695577',1,2018),
	(258,0,'432','teacher4',2,'54275498043695577',1,2018),
	(267,0,'432','teacher3',2,'54275498043695577',4,2018);

/*!40000 ALTER TABLE `CreditCard` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Curricula
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Curricula`;

CREATE TABLE `Curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `approbed` bit(1) NOT NULL,
  `educationSection` varchar(255) DEFAULT NULL,
  `experienceSection` varchar(255) DEFAULT NULL,
  `hobbiesSection` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i5neviuimxshteqql4abmfbqr` (`owner_id`),
  CONSTRAINT `FK_i5neviuimxshteqql4abmfbqr` FOREIGN KEY (`owner_id`) REFERENCES `Teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Curricula` WRITE;
/*!40000 ALTER TABLE `Curricula` DISABLE KEYS */;

INSERT INTO `Curricula` (`id`, `version`, `approbed`, `educationSection`, `experienceSection`, `hobbiesSection`, `photo`, `owner_id`)
VALUES
	(233,1,b'1','INGENIERO','RAE','raspis','http://wwww.pic.jpg',231),
	(242,1,b'1','INGENIERO','INDRA','raspis','http://wwww.pic.jpg',240),
	(259,1,b'1','INGENIERO','INDIEGOGO','raspis','http://wwww.pic.jpg',257),
	(268,1,b'1','INGENIERO','RAE','raspis','http://wwww.pic.jpg',266);

/*!40000 ALTER TABLE `Curricula` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Curricula_referencias
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Curricula_referencias`;

CREATE TABLE `Curricula_referencias` (
  `Curricula_id` int(11) NOT NULL,
  `referencias` varchar(255) DEFAULT NULL,
  KEY `FK_hrcuivwon28jfpcdlqrr5ggc9` (`Curricula_id`),
  CONSTRAINT `FK_hrcuivwon28jfpcdlqrr5ggc9` FOREIGN KEY (`Curricula_id`) REFERENCES `Curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Curricula_referencias` WRITE;
/*!40000 ALTER TABLE `Curricula_referencias` DISABLE KEYS */;

INSERT INTO `Curricula_referencias` (`Curricula_id`, `referencias`)
VALUES
	(233,'sdfsdfsd'),
	(242,'sdfsdfsd'),
	(259,'sdfsdfsd'),
	(268,'sdfsdfsd');

/*!40000 ALTER TABLE `Curricula_referencias` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Duty
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Duty`;

CREATE TABLE `Duty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `dutyValue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Duty` WRITE;
/*!40000 ALTER TABLE `Duty` DISABLE KEYS */;

INSERT INTO `Duty` (`id`, `version`, `dutyValue`)
VALUES
	(201,0,40);

/*!40000 ALTER TABLE `Duty` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Evaluation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Evaluation`;

CREATE TABLE `Evaluation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `webinar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fqvr8xn9pv9g4h1uhlwxs2vr7` (`webinar_id`),
  CONSTRAINT `FK_fqvr8xn9pv9g4h1uhlwxs2vr7` FOREIGN KEY (`webinar_id`) REFERENCES `Webinar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Evaluation` WRITE;
/*!40000 ALTER TABLE `Evaluation` DISABLE KEYS */;

INSERT INTO `Evaluation` (`id`, `version`, `webinar_id`)
VALUES
	(294,0,289),
	(362,0,NULL);

/*!40000 ALTER TABLE `Evaluation` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla EvaluationQuestion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `EvaluationQuestion`;

CREATE TABLE `EvaluationQuestion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `evaluation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gci0ks31rpajtb9835mp1rp69` (`evaluation_id`),
  CONSTRAINT `FK_gci0ks31rpajtb9835mp1rp69` FOREIGN KEY (`evaluation_id`) REFERENCES `Evaluation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `EvaluationQuestion` WRITE;
/*!40000 ALTER TABLE `EvaluationQuestion` DISABLE KEYS */;

INSERT INTO `EvaluationQuestion` (`id`, `version`, `answer`, `title`, `evaluation_id`)
VALUES
	(295,0,'SIII','¿Te ha gustado?',294),
	(296,0,'NOOO','¿El profe es guapo?',294),
	(363,0,'SIII','¿Te ha gustado?',362),
	(364,0,'NOOO','¿El profe es guapo?',362);

/*!40000 ALTER TABLE `EvaluationQuestion` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Folder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Folder`;

CREATE TABLE `Folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Folder` WRITE;
/*!40000 ALTER TABLE `Folder` DISABLE KEYS */;

INSERT INTO `Folder` (`id`, `version`, `name`, `owner_id`)
VALUES
	(203,0,'Outbox',202),
	(204,0,'Trashbox',202),
	(205,0,'Inbox',202),
	(206,0,'Spambox',202),
	(208,0,'Outbox',207),
	(209,0,'Trashbox',207),
	(210,0,'Inbox',207),
	(211,0,'Spambox',207),
	(213,0,'Outbox',212),
	(214,0,'Trashbox',212),
	(215,0,'Inbox',212),
	(216,0,'Spambox',212),
	(218,0,'Inbox',219),
	(234,0,'Outbox',231),
	(235,0,'Trashbox',231),
	(236,0,'Inbox',231),
	(237,0,'Spambox',231),
	(239,0,'Inbox',240),
	(260,0,'Outbox',257),
	(261,0,'Trashbox',257),
	(262,0,'Inbox',257),
	(263,0,'Spambox',257),
	(265,0,'Inbox',266),
	(269,0,'Outbox',266),
	(270,0,'Trashbox',266),
	(271,0,'Spambox',266),
	(278,0,'Outbox',253),
	(279,0,'Trashbox',253),
	(280,0,'Inbox',253),
	(281,0,'Spambox',253),
	(284,0,'Outbox',240),
	(285,0,'Trashbox',240),
	(286,0,'Spambox',240),
	(299,0,'Outbox',228),
	(300,0,'Trashbox',228),
	(301,0,'Inbox',228),
	(302,0,'Spambox',228),
	(305,0,'Inbox',225),
	(306,0,'Outbox',225),
	(307,0,'Trashbox',225),
	(308,0,'Spambox',225),
	(312,0,'Outbox',219),
	(313,0,'Trashbox',219),
	(314,0,'Spambox',219),
	(324,0,'Outbox',NULL),
	(325,0,'Trashbox',NULL),
	(326,0,'Inbox',NULL),
	(327,0,'Spambox',NULL),
	(328,0,'Outbox',NULL),
	(329,0,'Trashbox',NULL),
	(330,0,'Inbox',NULL),
	(331,0,'Spambox',NULL),
	(332,0,'Outbox',NULL),
	(333,0,'Trashbox',NULL),
	(334,0,'Inbox',NULL),
	(335,0,'Spambox',NULL),
	(336,0,'Outbox',NULL),
	(337,0,'Trashbox',NULL),
	(338,0,'Inbox',NULL),
	(339,0,'Spambox',NULL),
	(340,0,'Outbox',NULL),
	(341,0,'Trashbox',NULL),
	(342,0,'Inbox',NULL),
	(343,0,'Spambox',NULL),
	(344,0,'Outbox',NULL),
	(345,0,'Trashbox',NULL),
	(346,0,'Inbox',NULL),
	(347,0,'Spambox',NULL),
	(348,0,'Outbox',NULL),
	(349,0,'Trashbox',NULL),
	(350,0,'Inbox',NULL),
	(351,0,'Spambox',NULL),
	(352,0,'Outbox',353),
	(354,0,'Inbox',353),
	(355,0,'Trashbox',353),
	(356,0,'Spambox',353),
	(357,0,'Outbox',358),
	(359,0,'Inbox',358),
	(360,0,'Trashbox',358),
	(361,0,'Spambox',358);

/*!40000 ALTER TABLE `Folder` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla hibernate_sequences
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequences`;

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;

INSERT INTO `hibernate_sequences` (`sequence_name`, `sequence_next_hi_value`)
VALUES
	('DomainEntity',1);

/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla LearningMaterial
# ------------------------------------------------------------

DROP TABLE IF EXISTS `LearningMaterial`;

CREATE TABLE `LearningMaterial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachmentsURLs` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `LearningMaterial` WRITE;
/*!40000 ALTER TABLE `LearningMaterial` DISABLE KEYS */;

INSERT INTO `LearningMaterial` (`id`, `version`, `attachmentsURLs`, `title`, `type`)
VALUES
	(277,0,'http://wwww.perri.com/pictures/pic4.png','libro4',1),
	(298,0,'http://wwww.perri.com/pictures/pic1.png','libro',1),
	(310,0,'http://wwww.perri.com/pictures/pic3.png','libro3',1),
	(311,0,'http://wwww.perri.com/pictures/pic2.png','libro',2);

/*!40000 ALTER TABLE `LearningMaterial` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Mezzage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Mezzage`;

CREATE TABLE `Mezzage` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `receiverEmail` varchar(255) DEFAULT NULL,
  `sendDate` datetime DEFAULT NULL,
  `senderEmail` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `webinar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gughpct86iqnkatriwn09umui` (`folder_id`),
  KEY `FK_pgfseytnhpneykol8a9sxn9mi` (`webinar_id`),
  CONSTRAINT `FK_pgfseytnhpneykol8a9sxn9mi` FOREIGN KEY (`webinar_id`) REFERENCES `Webinar` (`id`),
  CONSTRAINT `FK_gughpct86iqnkatriwn09umui` FOREIGN KEY (`folder_id`) REFERENCES `Folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Mezzage` WRITE;
/*!40000 ALTER TABLE `Mezzage` DISABLE KEYS */;

INSERT INTO `Mezzage` (`id`, `version`, `body`, `priority`, `receiverEmail`, `sendDate`, `senderEmail`, `subject`, `folder_id`, `receiver_id`, `sender_id`, `webinar_id`)
VALUES
	(217,0,'Lorem ipsum dolor sit amet,',0,'user3mail@gmail.com','2017-04-23 23:12:00','user4mail@gmail.com','meeting',218,219,253,NULL),
	(238,0,'Lorem ipsum dolor sit amet,',0,'teacher1mail@gmail.com','2017-04-24 23:12:00','teacher2mail@gmail.com','meeting',239,240,231,NULL),
	(264,0,'Lorem ipsum dolor sit amet,',2,'teacher3mail@gmail.com','2017-04-23 23:12:00','teacher4mail@gmail.com','meeting',265,266,257,273),
	(272,0,'Lorem ipsum dolor sit amet,',0,'teacher4mail@gmail.com','2017-02-23 23:12:00','teacher3mail@gmail.com','meeting',262,257,266,NULL),
	(283,0,'Lorem ipsum dolor sit amet,',2,'user4mail@gmail.com','2017-02-23 23:12:00','user3mail@gmail.com','meeting',280,253,219,NULL),
	(287,0,'Lorem ipsum dolor sit amet,',1,'teacher2mail@gmail.com','2017-04-23 23:12:00','teacher1mail@gmail.com','meeting',236,231,240,NULL),
	(303,0,'Lorem ipsum dolor sit amet,',1,'user1mail@gmail.com','2017-04-24 23:12:00','user2mail@gmail.com','meeting',301,228,225,NULL),
	(304,0,'Lorem ipsum dolor sit amet,',0,'user2mail@gmail.com','2017-04-23 23:12:00','user1mail@gmail.com','meeting',305,225,228,NULL);

/*!40000 ALTER TABLE `Mezzage` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Moderator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Moderator`;

CREATE TABLE `Moderator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dvoe3msx5ofgj5qm5tyj6hnm9` (`userAccount_id`),
  KEY `FK_h3ntid5bmrpi90vrfl99ro9mt` (`creditCard_id`),
  CONSTRAINT `FK_dvoe3msx5ofgj5qm5tyj6hnm9` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`),
  CONSTRAINT `FK_h3ntid5bmrpi90vrfl99ro9mt` FOREIGN KEY (`creditCard_id`) REFERENCES `CreditCard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Moderator` WRITE;
/*!40000 ALTER TABLE `Moderator` DISABLE KEYS */;

INSERT INTO `Moderator` (`id`, `version`, `email`, `name`, `phoneNumber`, `surname`, `creditCard_id`, `userAccount_id`, `banned`)
VALUES
	(353,0,'moderator2@gmail.com','moderator2','+23-6234456456','moderator2sourname',NULL,199,b'0'),
	(358,0,'moderator1@gmail.com','moderator1','+23-6234456456','moderator1sourname',NULL,198,b'0');

/*!40000 ALTER TABLE `Moderator` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Module`;

CREATE TABLE `Module` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `webinar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5funtoujom2fu3ck8nfp69pl2` (`webinar_id`),
  CONSTRAINT `FK_5funtoujom2fu3ck8nfp69pl2` FOREIGN KEY (`webinar_id`) REFERENCES `Webinar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Module` WRITE;
/*!40000 ALTER TABLE `Module` DISABLE KEYS */;

INSERT INTO `Module` (`id`, `version`, `description`, `title`, `webinar_id`)
VALUES
	(276,0,'module3','titulo del modulo 3',NULL),
	(297,0,'module1','titulo del modulo 1',NULL),
	(309,0,'module2','titulo del modulo 2',NULL);

/*!40000 ALTER TABLE `Module` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Module_LearningMaterial
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Module_LearningMaterial`;

CREATE TABLE `Module_LearningMaterial` (
  `Module_id` int(11) NOT NULL,
  `learningMaterials_id` int(11) NOT NULL,
  UNIQUE KEY `UK_q7hc6b80my73a3160vemuo3ec` (`learningMaterials_id`),
  KEY `FK_jhx3qfwp80nkkbbubvojcgluh` (`Module_id`),
  CONSTRAINT `FK_jhx3qfwp80nkkbbubvojcgluh` FOREIGN KEY (`Module_id`) REFERENCES `Module` (`id`),
  CONSTRAINT `FK_q7hc6b80my73a3160vemuo3ec` FOREIGN KEY (`learningMaterials_id`) REFERENCES `LearningMaterial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Module_LearningMaterial` WRITE;
/*!40000 ALTER TABLE `Module_LearningMaterial` DISABLE KEYS */;

INSERT INTO `Module_LearningMaterial` (`Module_id`, `learningMaterials_id`)
VALUES
	(276,277),
	(297,298),
	(309,310),
	(309,311);

/*!40000 ALTER TABLE `Module_LearningMaterial` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Question
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Question`;

CREATE TABLE `Question` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `categories_id` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `search_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ph84lui8l3dom1ok8f83g9326` (`categories_id`),
  KEY `FK_qq7bkt3668nfjr2q0ovrs482l` (`search_id`),
  CONSTRAINT `FK_qq7bkt3668nfjr2q0ovrs482l` FOREIGN KEY (`search_id`) REFERENCES `Search` (`id`),
  CONSTRAINT `FK_ph84lui8l3dom1ok8f83g9326` FOREIGN KEY (`categories_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;

INSERT INTO `Question` (`id`, `version`, `banned`, `createdDate`, `summary`, `title`, `categories_id`, `owner_id`, `search_id`)
VALUES
	(224,0,b'0','2017-05-22 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question2',223,225,249),
	(244,0,b'1','2017-06-22 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question3',245,219,NULL),
	(246,0,b'0','2017-01-22 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question5',247,228,NULL),
	(248,0,b'0','2017-04-22 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question1',247,228,249),
	(251,0,b'0','2017-04-12 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question4',223,228,NULL),
	(252,0,b'0','2016-12-22 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question6',245,253,NULL),
	(323,0,b'1','2016-12-22 00:00:00','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt re eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','question7',245,253,NULL);

/*!40000 ALTER TABLE `Question` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Question_pictures
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Question_pictures`;

CREATE TABLE `Question_pictures` (
  `Question_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_kwlppqjskcrgbh7rm9748tark` (`Question_id`),
  CONSTRAINT `FK_kwlppqjskcrgbh7rm9748tark` FOREIGN KEY (`Question_id`) REFERENCES `Question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Question_pictures` WRITE;
/*!40000 ALTER TABLE `Question_pictures` DISABLE KEYS */;

INSERT INTO `Question_pictures` (`Question_id`, `pictures`)
VALUES
	(248,'http://www.pic.jpg'),
	(248,'http://www.pic3.jpg'),
	(246,'http://www.pic.jpg'),
	(246,'http://www.pic3.jpg'),
	(251,'http://www.pic.jpg'),
	(251,'http://www.pic3.jpg'),
	(252,'http://www.pic.jpg'),
	(252,'http://www.pic3.jpg'),
	(244,'http://www.pic.jpg'),
	(244,'http://www.pic3.jpg'),
	(224,'http://www.pic.jpg'),
	(224,'http://www.pic3.jpg'),
	(323,'http://www.pic.jpg'),
	(323,'http://www.pic3.jpg');

/*!40000 ALTER TABLE `Question_pictures` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Search
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Search`;

CREATE TABLE `Search` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a2fl4usjpdbpujh8dr1lujm8i` (`category_id`),
  CONSTRAINT `FK_a2fl4usjpdbpujh8dr1lujm8i` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Search` WRITE;
/*!40000 ALTER TABLE `Search` DISABLE KEYS */;

INSERT INTO `Search` (`id`, `version`, `keyword`, `category_id`, `owner_id`)
VALUES
	(249,0,'java',223,228),
	(282,0,'spring',247,253),
	(288,0,'spring',245,240);

/*!40000 ALTER TABLE `Search` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla SearchCache
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SearchCache`;

CREATE TABLE `SearchCache` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cacheValue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `SearchCache` WRITE;
/*!40000 ALTER TABLE `SearchCache` DISABLE KEYS */;

INSERT INTO `SearchCache` (`id`, `version`, `cacheValue`)
VALUES
	(200,0,10);

/*!40000 ALTER TABLE `SearchCache` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Teacher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Teacher`;

CREATE TABLE `Teacher` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `IBAN` varchar(255) DEFAULT NULL,
  `curricula_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hh7bf6toh1ysrkeqxhr1077dv` (`userAccount_id`),
  KEY `FK_hk6lhly9g3k54cnrc44wow98h` (`curricula_id`),
  KEY `FK_e4o0u44m6uhemvi7yvah57ooy` (`creditCard_id`),
  CONSTRAINT `FK_hh7bf6toh1ysrkeqxhr1077dv` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`),
  CONSTRAINT `FK_e4o0u44m6uhemvi7yvah57ooy` FOREIGN KEY (`creditCard_id`) REFERENCES `CreditCard` (`id`),
  CONSTRAINT `FK_hk6lhly9g3k54cnrc44wow98h` FOREIGN KEY (`curricula_id`) REFERENCES `Curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;

INSERT INTO `Teacher` (`id`, `version`, `email`, `name`, `phoneNumber`, `surname`, `creditCard_id`, `userAccount_id`, `IBAN`, `curricula_id`)
VALUES
	(231,0,'teacher2mail@gmail.com','teacher2name','+23-6234456456','teacher2surname',232,195,'ES8023145623220000012345',233),
	(240,0,'teacher1mail@gmail.com','teacher1name','+23-6234456456','teacher1surname',241,194,'ES8023100001180000012345',242),
	(257,0,'teacher4mail@gmail.com','teacher4name','+23-6234456456','teacher4surname',258,197,'ES8023145665743879010045',259),
	(266,0,'teacher3mail@gmail.com','teacher3name','+23-6234456456','teacher3surname',267,196,'ES8023145623221845612345',268);

/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  KEY `FK_qpie77wb71iayqlvrhtc0s84y` (`creditCard_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`),
  CONSTRAINT `FK_qpie77wb71iayqlvrhtc0s84y` FOREIGN KEY (`creditCard_id`) REFERENCES `CreditCard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;

INSERT INTO `User` (`id`, `version`, `email`, `name`, `phoneNumber`, `surname`, `creditCard_id`, `userAccount_id`, `banned`)
VALUES
	(219,0,'user3mail@gmail.com','user3name','+23-6234456456','user3surname',220,191,b'0'),
	(225,0,'user2mail@gmail.com','user2name','+23-6234456456','user2surname',226,190,b'0'),
	(228,0,'user1mail@gmail.com','user1name','+23-6234456456','user1sourname',229,189,b'0'),
	(253,0,'user4mail@gmail.com','user4name','+23-6234456456','user4surname',254,192,b'1');

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla UserAccount
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserAccount`;

CREATE TABLE `UserAccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `UserAccount` WRITE;
/*!40000 ALTER TABLE `UserAccount` DISABLE KEYS */;

INSERT INTO `UserAccount` (`id`, `version`, `password`, `username`)
VALUES
	(185,0,'e00cf25ad42683b3df678c61f42c6bda','admin1'),
	(186,0,'c84258e9c39059a89ab77d846ddab909','admin2'),
	(187,0,'32cacb2f994f6b42183a1300d9a3e8d6','admin3'),
	(188,0,'fc1ebc848e31e0a68e868432225e3c82','admin4'),
	(189,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),
	(190,0,'7e58d63b60197ceb55a1c487989a3720','user2'),
	(191,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),
	(192,0,'3f02ebe3d7929b091e3d8ccfde2f3bc6','user4'),
	(193,0,'0a791842f52a0acfbb3a783378c066b8','user5'),
	(194,0,'41c8949aa55b8cb5dbec662f34b62df3','teacher1'),
	(195,0,'ccffb0bb993eeb79059b31e1611ec353','teacher2'),
	(196,0,'82470256ea4b80343b27afccbca1015b','teacher3'),
	(197,0,'93dacda950b1dd917079440788af3321','teacher4'),
	(198,0,'38caf4a470117125b995f7ce53e6e6b9','moderator1'),
	(199,0,'95d88ad73653fc7ad4fec3bc56677c3c','moderator2');

/*!40000 ALTER TABLE `UserAccount` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla UserAccount_authorities
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserAccount_authorities`;

CREATE TABLE `UserAccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `UserAccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `UserAccount_authorities` WRITE;
/*!40000 ALTER TABLE `UserAccount_authorities` DISABLE KEYS */;

INSERT INTO `UserAccount_authorities` (`UserAccount_id`, `authority`)
VALUES
	(185,'ADMIN'),
	(186,'ADMIN'),
	(187,'ADMIN'),
	(188,'ADMIN'),
	(189,'USER'),
	(190,'USER'),
	(191,'USER'),
	(192,'BAN'),
	(193,'USER'),
	(194,'TEACHER'),
	(195,'TEACHER'),
	(196,'TEACHER'),
	(197,'TEACHER'),
	(198,'MODERATOR'),
	(199,'MODERATOR');

/*!40000 ALTER TABLE `UserAccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Webinar
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Webinar`;

CREATE TABLE `Webinar` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `startDate` date DEFAULT NULL,
  `categories_id` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_coth653y6h7pqa1ngn3gq2mua` (`categories_id`),
  KEY `FK_fkrg7o662da25v87jigsh5wm7` (`owner_id`),
  CONSTRAINT `FK_fkrg7o662da25v87jigsh5wm7` FOREIGN KEY (`owner_id`) REFERENCES `Teacher` (`id`),
  CONSTRAINT `FK_coth653y6h7pqa1ngn3gq2mua` FOREIGN KEY (`categories_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Webinar` WRITE;
/*!40000 ALTER TABLE `Webinar` DISABLE KEYS */;

INSERT INTO `Webinar` (`id`, `version`, `URL`, `description`, `name`, `picture`, `price`, `startDate`, `categories_id`, `owner_id`)
VALUES
	(222,0,'https://www.youtube.com/embed/90MBOV6mOPM','web2','webinar2','https://pymex.pe/wp-content/uploads/2016/11/webinar.jpeg',120,'2017-07-22',223,231),
	(273,0,'https://www.youtube.com/embed/90MBOV6mOPM','web3','webinar3','http://www.synergo.es/wp-content/uploads/2012/05/webinar-big.jpg',1,'2017-06-12',247,266),
	(289,0,'https://www.youtube.com/embed/90MBOV6mOPM','web1','webinar1','https://www.ellibrepensador.com/wp-content/uploads/2016/12/webinar.jpg',20,'2017-06-22',245,240),
	(319,0,'https://www.youtube.com/embed/90MBOV6mOPM','web4','webinar4','https://elearningindustry.com/wp-content/uploads/2015/10/top-7-webinar-tips-successful-webinar-host.jpg',202,'2017-06-11',245,257),
	(320,0,'https://www.youtube.com/embed/90MBOV6mOPM','web4','webinar5','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6nOmDgD9qIKGOCWkJjgF3wd0TZjBcJW56MlKqgO4cFOqjfCV7',20,'2016-06-11',247,231),
	(321,0,'https://www.youtube.com/embed/90MBOV6mOPM','web6','webinar6','http://www.terrainfirma.co.uk/blog/wp-content/uploads/2011/02/webinar-headset-pc.jpg',2000,'2018-06-11',247,240),
	(322,0,'https://www.youtube.com/embed/90MBOV6mOPM','web6','webinar6','http://fernandorubio.es/wp-content/uploads/2016/10/webinar-marketing.png',2000,'2017-05-17',247,240);

/*!40000 ALTER TABLE `Webinar` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Webinar_User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Webinar_User`;

CREATE TABLE `Webinar_User` (
  `webinars_id` int(11) NOT NULL,
  `partakers_id` int(11) NOT NULL,
  KEY `FK_a89h8rotyi1re5d06llyio3p6` (`webinars_id`),
  CONSTRAINT `FK_a89h8rotyi1re5d06llyio3p6` FOREIGN KEY (`webinars_id`) REFERENCES `Webinar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Webinar_User` WRITE;
/*!40000 ALTER TABLE `Webinar_User` DISABLE KEYS */;

INSERT INTO `Webinar_User` (`webinars_id`, `partakers_id`)
VALUES
	(273,253),
	(289,225),
	(289,228),
	(222,219);

/*!40000 ALTER TABLE `Webinar_User` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
commit;