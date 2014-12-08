CREATE DATABASE /*!32312 IF NOT EXISTS*/`coterie` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
CREATE USER 'coterie'@'%' IDENTIFIED BY 'servicerequest';
GRANT ALL PRIVILEGES ON coterie.* TO 'coterie'@'%';
-- UPDATE mysql.user SET Password=PASSWORD('servicerequest') where USER='coterie';
FLUSH PRIVILEGES;

