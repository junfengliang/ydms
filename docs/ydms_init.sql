# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.37)
# Database: ydms
# Generation Time: 2020-06-03 11:53:37 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `pid` int(20) DEFAULT NULL COMMENT 'parent id',
  `url` varchar(200) DEFAULT NULL COMMENT 'menu URL',
  `name` varchar(200) DEFAULT NULL COMMENT 'menu name',
  `permission` varchar(500) DEFAULT NULL COMMENT 'permissions',
  `type` int(11) DEFAULT NULL COMMENT '1=menu, 2=button',
  `icon` varchar(50) DEFAULT NULL COMMENT 'menu icon',
  `order_num` int(11) DEFAULT NULL COMMENT 'order',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

INSERT INTO `sys_menu` (`id`, `pid`, `url`, `name`, `permission`, `type`, `icon`, `order_num`, `create_time`, `update_time`)
VALUES
	(10,0,'','系统管理','user',1,'setting',2,'2020-04-05 15:23:13','2020-04-05 15:23:13'),
	(13,10,'/sys/user','用户管理','user',1,'user',1,'2020-04-05 18:21:11','2020-04-05 18:21:11'),
	(15,10,'/sys/menu','菜单管理','menu',1,'list',3,'2020-04-05 15:15:46','2020-04-05 15:15:46'),
	(16,10,'/sys/role','角色管理','role',1,'component',2,'2020-04-18 10:17:11','2020-04-18 10:17:11');

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT 'role name',
  `remark` varchar(100) DEFAULT NULL COMMENT 'remark',
  `create_time` datetime DEFAULT NULL COMMENT 'create time',
  `update_time` datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `role_name`, `remark`, `create_time`, `update_time`)
VALUES
	(1,'Admin','后台管理 ',NULL,'2020-05-24 07:50:58'),
	(6,'Editor','用户管理 ','2020-04-03 22:36:30','2020-05-24 10:55:30');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;

INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`)
VALUES
	(29,6,13,'2020-04-04 17:14:41','2020-04-04 17:14:41'),
	(30,1,10,'2020-05-24 07:50:58','2020-05-24 07:50:58'),
	(31,1,13,'2020-05-24 07:50:58','2020-05-24 07:50:58'),
	(32,1,16,'2020-05-24 07:50:58','2020-05-24 07:50:58'),
	(33,1,15,'2020-05-24 07:50:58','2020-05-24 07:50:58'),
	(34,6,10,'2020-05-24 10:55:30','2020-05-24 10:55:30');

/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_time`, `last_login_time`, `update_time`)
VALUES
	(2,'admin','c51dbd73e48709d45f323e00a272068af537f773','04bcbe1972f78a57e5801f3f6748cd27','admin@admin.com',NULL,NULL,'2020-04-04 20:42:20',NULL,'2020-03-26 21:49:47'),
	(28,'ydms','1620a02a2d75d1ba6d36056302ad7f1cfddef3b4','c73324f7-345f-4870-9992-323783f01bdd','18552208@qq.com',NULL,NULL,'2020-04-18 10:17:42',NULL,'2020-05-30 16:03:02');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`)
VALUES
	(10,2,1,'2020-04-04 20:55:05','2020-04-04 20:55:05'),
	(11,2,6,'2020-04-04 20:55:05','2020-04-04 20:55:05'),
	(14,28,6,'2020-04-18 10:17:42','2020-04-18 10:17:42'),
	(15,28,1,'2020-05-24 10:57:41','2020-05-24 10:57:41');

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
