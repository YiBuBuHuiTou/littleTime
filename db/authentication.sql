-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.4.10-MariaDB - mariadb.org binary distribution
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for authentication
CREATE DATABASE IF NOT EXISTS `authentication` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `authentication`;

-- Dumping structure for table authentication.user_entry
CREATE TABLE IF NOT EXISTS `user_entry` (
  `id` int(32) unsigned NOT NULL COMMENT '数据库id、递增',
  `Auth_ID` int(32) unsigned NOT NULL COMMENT '用户ID',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '用户查询类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息入口表、用于判断用户登录时的方式：密码登录、无密码登录';

-- Data exporting was unselected.

-- Dumping structure for table authentication.user_info_encrypted
CREATE TABLE IF NOT EXISTS `user_info_encrypted` (
  `id` int(32) unsigned NOT NULL COMMENT '数据库id、递增',
  `auth_id` int(32) NOT NULL COMMENT '用户ID',
  `nick_name` varchar(18) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_name` varchar(18) DEFAULT '' COMMENT '用户名',
  `password` varchar(31) DEFAULT '' COMMENT '密码',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱',
  `phone_num` varchar(11) DEFAULT '' COMMENT '电话号码',
  `permission` int(1) unsigned NOT NULL COMMENT '权限',
  `security_key` varchar(32) DEFAULT '' COMMENT '加密key'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='未加密用户信息';

-- Data exporting was unselected.

-- Dumping structure for table authentication.user_info_unencrypted
CREATE TABLE IF NOT EXISTS `user_info_unencrypted` (
  `id` int(32) unsigned NOT NULL COMMENT '数据库id、递增',
  `auth_id` int(32) NOT NULL COMMENT '用户ID',
  `nick_name` varchar(128) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_name` varchar(128) DEFAULT '' COMMENT '用户名',
  `password` varchar(128) DEFAULT '' COMMENT '密码',
  `email` varchar(128) DEFAULT '' COMMENT '邮箱',
  `phone_num` varchar(128) DEFAULT '' COMMENT '电话号码',
  `permission` int(1) unsigned NOT NULL COMMENT '权限',
  `security_key` varchar(32) DEFAULT '' COMMENT '加密key'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='未加密用户信息';

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
