DROP DATABASE  IF EXISTS `pshop`;

CREATE DATABASE  IF NOT EXISTS `pshop`;
USE `pshop`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE images (
	i_id int PRIMARY KEY AUTO_INCREMENT,
    i_upload_date DATETIME,
    i_path VARCHAR(80),
    i_turn int(11)
    
);

CREATE TABLE categories (
	c_id int PRIMARY KEY AUTO_INCREMENT,
    c_category VARCHAR(20),
    c_description VARCHAR(120),
    c_image_id int,
    
    CONSTRAINT FK_I_IMAGE_ID FOREIGN KEY (c_image_id) REFERENCES images(i_id)
    
    
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE products (
	p_id int PRIMARY KEY AUTO_INCREMENT,
    p_title VARCHAR(30),
    p_description VARCHAR(120),
    p_price DECIMAL(6,2),
    p_date DATETIME
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE products_categories (
	p_c_product_id int,
    p_c_category_id int,
    
    CONSTRAINT FK_P_C_PRODUCT_ID FOREIGN KEY (p_c_product_id) REFERENCES products(p_id),
    CONSTRAINT FK_P_C_CATEGORY_ID FOREIGN KEY (p_c_category_id) REFERENCES categories(c_id)
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE orders_types (
	o_t_id int PRIMARY KEY AUTO_INCREMENT,
    o_t_name VARCHAR(10)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE orders_status (
	o_s_id int PRIMARY KEY AUTO_INCREMENT,
    o_s_name VARCHAR(10)
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE orders (
	o_id INT PRIMARY KEY AUTO_INCREMENT,
    o_phone_number VARCHAR(15) UNIQUE KEY,
    o_email VARCHAR(30),
    o_first_name VARCHAR(15),
    o_last_name VARCHAR(15),
    o_city VARCHAR(10),
    o_post_code VARCHAR(8),
    o_address VARCHAR(25),
    o_date DATETIME,
    
    o_status_id int,
    o_users_id VARCHAR(50),
    o_type_id int,
    
    CONSTRAINT FK_O_STATUS_ID FOREIGN KEY(o_status_id) REFERENCES orders_status(o_s_id),
    CONSTRAINT FK_O_TYPE_ID FOREIGN KEY(o_type_id) REFERENCES orders_types(o_t_id),
    CONSTRAINT FK_O_USERS_ID FOREIGN KEY(o_users_id) REFERENCES users(username)
    
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE orders_products (
	o_p_order_id int,
    o_p_product_id int,
    
    CONSTRAINT FK_O_P_ORDER_ID FOREIGN KEY(o_p_order_id) REFERENCES orders(o_id),
    CONSTRAINT FK_O_P_PRODUCT_ID FOREIGN KEY(o_p_product_id) REFERENCES products(p_id)
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product_statistics (
	p_s_id int PRIMARY KEY AUTO_INCREMENT,
    p_s_product_id int,
    clicks int
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE images_products (
	i_p_product_id int,
    i_p_image_id int,
    
    CONSTRAINT FK_I_P_PRODUCT_ID foreign key (i_p_product_id) references products(p_id),
    CONSTRAINT FK_I_P_IMAGE_ID foreign key (i_p_image_id) references images(i_id)
    
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE users_details (
	 u_d_id int PRIMARY KEY AUTO_INCREMENT,
     u_d_username VARCHAR(50),
	 u_d_address VARCHAR(50) NOT NULL,
     u_d_email VARCHAR(30) NOT NULL UNIQUE,
     u_d_first_name VARCHAR(15),
     u_d_last_name VARCHAR(15),
     u_d_phone_number VARCHAR(15) NOT NULL UNIQUE,
     
     FOREIGN KEY (u_d_username) REFERENCES users(username)
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE verification_token(
	v_t_id INT AUTO_INCREMENT PRIMARY KEY,
    v_t_token VARCHAR(36) UNIQUE,
    v_t_expiration DATETIME,
    v_t_username VARCHAR(50),
    
    CONSTRAINT FK_USERNAME FOREIGN KEY(v_t_username) REFERENCES users(username)
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE password_reset_token (
	p_r_t_id INT AUTO_INCREMENT PRIMARY KEY,
	p_r_t_token VARCHAR(36) UNIQUE,
    p_r_t_expiration DATETIME,
    p_r_t_username VARCHAR(50),
    
    CONSTRAINT FK_P_R_USERNAME FOREIGN KEY(p_r_t_username) REFERENCES users(username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

