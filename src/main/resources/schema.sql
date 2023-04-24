CREATE TABLE seller (
   id INT PRIMARY KEY AUTO_INCREMENT,
   sales_amount INTEGER DEFAULT 0 NOT NULL,
   name VARCHAR(255) NOT NULL
);

CREATE TABLE sale (
   id INT PRIMARY KEY AUTO_INCREMENT,
   sale_date DATE NOT NULL,
   sale_value NUMERIC(20, 2) NOT NULL,
   seller_id INT,
   foreign key (seller_id) references seller(id)
);