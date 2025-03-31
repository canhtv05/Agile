CREATE DATABASE agile;

USE agile;

-- Tạo bảng category
CREATE TABLE category (
                          id VARCHAR(36) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng color
CREATE TABLE color (
                       id VARCHAR(36) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng size
CREATE TABLE size (
                      id VARCHAR(36) PRIMARY KEY,
                      name VARCHAR(10) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng role
CREATE TABLE role (
                      id VARCHAR(36) PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng user
CREATE TABLE user (
                      id VARCHAR(36) PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      dob DATE NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      phone VARCHAR(20) NOT NULL UNIQUE,
                      refresh_token TEXT,
                      user_status ENUM('ACTIVE', 'INACTIVE', 'BANNED') NOT NULL,
                      gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng user_has_role (bảng trung gian giữa user và role)
CREATE TABLE user_has_role (
                               id VARCHAR(36) PRIMARY KEY,
                               user_id VARCHAR(36) NOT NULL,
                               role_id VARCHAR(36) NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                               FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- Tạo bảng product
CREATE TABLE product (
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         category_id VARCHAR(36) NOT NULL,
                         description TEXT,
                         image_url VARCHAR(255),
                         star FLOAT CHECK (star >= 0 AND star <= 5),
                         price INT NOT NULL CHECK (price >= 0),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE RESTRICT
);

-- Tạo bảng product_detail
CREATE TABLE product_detail (
                                id VARCHAR(36) PRIMARY KEY,
                                product_id VARCHAR(36) NOT NULL,
                                image_url VARCHAR(255),
                                color_id VARCHAR(36) NOT NULL,
                                size_id VARCHAR(36) NOT NULL,
                                price INT NOT NULL CHECK (price >= 0),
                                stock_quantity INT NOT NULL CHECK (stock_quantity >= 0),
                                sold_quantity INT NOT NULL CHECK (sold_quantity >= 0),
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
                                FOREIGN KEY (color_id) REFERENCES color(id) ON DELETE RESTRICT,
                                FOREIGN KEY (size_id) REFERENCES size(id) ON DELETE RESTRICT
);

-- Tạo bảng cart_item
CREATE TABLE cart_item (
                           id VARCHAR(36) PRIMARY KEY,
                           user_id VARCHAR(36) NOT NULL,
                           product_detail_id VARCHAR(36) NOT NULL,
                           quantity INT NOT NULL CHECK (quantity >= 0),
                           price INT NOT NULL CHECK (price >= 0),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                           FOREIGN KEY (product_detail_id) REFERENCES product_detail(id) ON DELETE CASCADE
);

-- Tạo bảng order
CREATE TABLE `order` (
                         id VARCHAR(36) PRIMARY KEY,
                         user_id VARCHAR(36) NOT NULL,
                         title VARCHAR(255),
                         total_price INT NOT NULL CHECK (total_price >= 0),
                         status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED') NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT
);

-- Tạo bảng order_detail
CREATE TABLE order_detail (
                              id VARCHAR(36) PRIMARY KEY,
                              order_id VARCHAR(36) NOT NULL,
                              product_detail_id VARCHAR(36) NOT NULL,
                              quantity INT NOT NULL CHECK (quantity >= 1),
                              price INT NOT NULL CHECK (price >= 0),
                              status ENUM('PENDING', 'DELIVERED', 'RETURNED') NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (order_id) REFERENCES `order`(id) ON DELETE CASCADE,
                              FOREIGN KEY (product_detail_id) REFERENCES product_detail(id) ON DELETE RESTRICT
);

INSERT INTO category (id, name) VALUES
                                    ('cat1', 'Áo thun'),
                                    ('cat2', 'Quần jeans'),
                                    ('cat3', 'Giày thể thao');

INSERT INTO product (id, name, category_id, description, image_url, star, price) VALUES
                                                                                     ('prod1', 'Áo thun nam', 'cat1', 'Áo thun nam cotton', 'http://example.com/ao1.jpg', 4.5, 200000),
                                                                                     ('prod2', 'Áo thun nữ', 'cat1', 'Áo thun nữ form rộng', 'http://example.com/ao2.jpg', 4.0, 250000),
                                                                                     ('prod3', 'Quần jeans nam', 'cat2', 'Quần jeans nam slim fit', 'http://example.com/quan1.jpg', 4.2, 500000);