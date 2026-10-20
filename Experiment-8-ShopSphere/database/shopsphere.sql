CREATE DATABASE IF NOT EXISTS shopsphere CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shopsphere;
CREATE TABLE IF NOT EXISTS users (
  user_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL,
  email VARCHAR(190) NOT NULL UNIQUE, password_hash VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER', created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS products (
  product_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(150) NOT NULL,
  description TEXT, price DECIMAL(10,2) NOT NULL, stock INT NOT NULL DEFAULT 0,
  image_url VARCHAR(500), status BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS orders (
  order_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL,
  total_amount DECIMAL(10,2) NOT NULL, order_status VARCHAR(30) NOT NULL DEFAULT 'PLACED',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE IF NOT EXISTS order_items (
  order_item_id INT PRIMARY KEY AUTO_INCREMENT, order_id INT NOT NULL,
  product_id INT NOT NULL, quantity INT NOT NULL, price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);
INSERT INTO products(name,description,price,stock,image_url) VALUES
('Everyday Backpack','A durable backpack for everyday use.',1299.00,20,'https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=700'),
('Wireless Headphones','Comfortable wireless headphones.',2499.00,15,'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=700'),
('Classic Watch','Minimal watch for daily wear.',1899.00,12,'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=700');
