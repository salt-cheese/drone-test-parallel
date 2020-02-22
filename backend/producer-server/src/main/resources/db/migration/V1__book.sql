CREATE TABLE IF NOT EXISTS `books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(30) NOT NULL,
  `category` varchar(255) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_isbn` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
