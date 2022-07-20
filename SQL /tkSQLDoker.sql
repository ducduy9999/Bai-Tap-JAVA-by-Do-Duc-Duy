SELECT host, user from mysql.user;
CREATE USER 'root'@'%' IDENTIFIED BY '123456';

GRANT ALL PRIVILEGES ON *.* To 'root'@'%'; 
 FLUSH PRIVILEGES;