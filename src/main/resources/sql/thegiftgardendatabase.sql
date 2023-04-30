DROP DATABASE IF EXISTS thegiftgardendatabase;
CREATE DATABASE thegiftgardendatabase;
USE thegiftgardendatabase;

DROP TABLE IF EXISTS users;


CREATE TABLE users (
	user_id INT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_firstname VARCHAR(225) NOT NULL,
    user_lastname VARCHAR(255) NOT NULL    
);