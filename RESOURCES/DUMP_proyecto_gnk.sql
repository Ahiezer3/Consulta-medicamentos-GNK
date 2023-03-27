
--
-- Table structure for table `tb_inventory`
--
CREATE DATABASE proyecto_gnk;

USE proyecto_gnk;

--
-- Table structure for table `tb_users`
--

CREATE TABLE `tb_users` (
  `id` bigint NOT NULL,
  `name` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `lastname` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `password` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `date_register` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;


--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES (1,'admin','administrador','1990-01-01 00:00:00','admin',NULL);
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `tb_medicines`
--

CREATE TABLE `tb_medicines` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `storage` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;


--
-- Dumping data for table `tb_medicines`
--

LOCK TABLES `tb_medicines` WRITE;
/*!40000 ALTER TABLE `tb_medicines` DISABLE KEYS */;
INSERT INTO `tb_medicines` VALUES (49,'Simvastatina','X12-S123-SAD4','2026-10-21 00:00:00','2023-03-26 14:30:05'),(50,'Aspirina','DS-54SA','2025-10-29 00:00:00','2023-03-26 14:30:36'),(51,'Omeprazol','456DF','2030-01-15 00:00:00','2023-03-26 14:31:17'),(52,'Lexotiroxina','Lexo-46421A','2025-02-02 00:00:00','2023-03-26 14:31:47'),(53,'Ramipril','45621A','2023-08-04 00:00:00','2023-03-26 14:32:32'),(54,'Amlodipina','F46SA-C','2025-07-26 00:00:00','2023-03-26 14:32:51'),(55,'Paracetamol','DF465','2023-11-30 00:00:00','2023-03-26 14:33:17'),(56,'Atorvastatina','84456A','2027-02-10 00:00:00','2023-03-26 14:33:40'),(57,'Salbutamol','498DSV','2028-04-13 00:00:00','2023-03-26 14:33:56'),(58,'Lansoprazol','897S','2032-08-26 00:00:00','2023-03-26 14:34:20'),(59,'Desoxifedrina','ASD','2023-04-01 00:00:00','2023-03-26 15:06:54'),(60,'Sedal','SD456','2023-03-30 00:00:00','2023-03-26 16:04:55');
/*!40000 ALTER TABLE `tb_medicines` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `tb_inventory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_medicine` bigint DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id_medicine`),
  CONSTRAINT `id` FOREIGN KEY (`id_medicine`) REFERENCES `tb_medicines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;



--
-- Dumping data for table `tb_inventory`
--

LOCK TABLES `tb_inventory` WRITE;
/*!40000 ALTER TABLE `tb_inventory` DISABLE KEYS */;
INSERT INTO `tb_inventory` VALUES (27,49,825),(28,50,600),(29,51,498),(30,52,800),(31,53,0),(32,54,250),(33,55,1000),(34,56,800),(35,57,520),(36,58,124),(37,59,54),(38,60,10);
/*!40000 ALTER TABLE `tb_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_inventory_orders`
--

CREATE TABLE `tb_inventory_orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint DEFAULT NULL,
  `id_inventory` bigint DEFAULT NULL,
  `type_order` varchar(20) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  `summary` bigint DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_inventory_order_idx` (`id_inventory`),
  KEY `id_user_order_idx` (`id_user`),
  CONSTRAINT `id_inventory_order` FOREIGN KEY (`id_inventory`) REFERENCES `tb_inventory` (`id`),
  CONSTRAINT `id_user_order` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;


--
-- Dumping data for table `tb_inventory_orders`
--

LOCK TABLES `tb_inventory_orders` WRITE;
/*!40000 ALTER TABLE `tb_inventory_orders` DISABLE KEYS */;
INSERT INTO `tb_inventory_orders` VALUES (14,1,27,'IN',100,100,'Se agregan 100 piezas','2023-03-26 14:35:39'),(15,1,27,'IN',150,250,'Sumar 150 al inventario','2023-03-26 14:36:42'),(16,1,27,'IN',500,750,'Llegada de 500','2023-03-26 14:37:36'),(17,1,27,'OUT',-50,700,'Salida de 50','2023-03-26 14:37:48'),(18,1,27,'IN',200,900,'Llegada de 200','2023-03-26 14:38:01'),(19,1,27,'OUT',-100,800,'Salida de 100','2023-03-26 14:38:13'),(20,1,27,'IN',25,825,'Salida de 25','2023-03-26 14:38:22'),(21,1,28,'IN',100,100,'Llegada de 100','2023-03-26 14:39:31'),(22,1,28,'IN',500,600,'Entrada de 500','2023-03-26 14:39:46'),(23,1,29,'IN',520,520,'Entrada de 520','2023-03-26 14:40:02'),(24,1,29,'IN',200,720,'Entrada de 200','2023-03-26 14:40:14'),(25,1,29,'IN',28,748,'Llegada de 28','2023-03-26 14:40:28'),(26,1,29,'OUT',-250,498,'Salida 250','2023-03-26 14:40:39'),(27,1,30,'IN',800,800,'Llegada a almacÃ©n de 800 piezas','2023-03-26 14:40:56'),(28,1,31,'OUT',-100,-100,'Salida de 100','2023-03-26 14:41:45'),(29,1,31,'IN',100,0,'Entrada de 100, correcciÃ³n de movimiento','2023-03-26 14:42:04'),(30,1,32,'IN',500,500,'Entrada de 500','2023-03-26 14:42:15'),(31,1,32,'OUT',-250,250,'Salen 250','2023-03-26 14:42:27'),(32,1,33,'IN',1000,1000,'Entrada de 1000','2023-03-26 14:42:39'),(33,1,34,'IN',850,850,'Entrada de 850','2023-03-26 14:42:53'),(34,1,34,'OUT',-50,800,'Salida de 50','2023-03-26 14:43:04'),(35,1,35,'IN',520,520,'Entrada de 520','2023-03-26 14:43:16'),(36,1,36,'IN',99,99,'Entrada de 99','2023-03-26 14:43:37'),(37,1,36,'IN',25,124,'25 piezas de entrada','2023-03-26 15:07:20'),(38,1,37,'IN',54,54,'Llegada 54 piezas','2023-03-26 15:10:17'),(39,1,38,'IN',10,10,'Lleagada de 10','2023-03-26 16:05:16');
/*!40000 ALTER TABLE `tb_inventory_orders` ENABLE KEYS */;
UNLOCK TABLES;
