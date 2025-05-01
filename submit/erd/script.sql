-- user 테이블 생성
CREATE TABLE `users` (
    `user_id` varchar(50) NOT NULL COMMENT '아이디',
    `user_name` varchar(50) NOT NULL COMMENT '이름',
    `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
    `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
    `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
    `user_point` int NOT NULL COMMENT 'default : 1000000',
    `created_at` datetime NOT NULL COMMENT '가입 일자',
    `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

-- category 테이블
CREATE TABLE categories(
     category_id int AUTO_INCREMENT NOT NULL COMMENT '카테고리 구분키',
     category_name varchar(50) NOT NULL COMMENT '카테고리 명',
     PRIMARY KEY (category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리';

-- product 테이블
CREATE TABLE products(
     product_id int AUTO_INCREMENT NOT NULL COMMENT '상품번호',
     category_id int NOT NULL COMMENT '카테고리 ID',
     product_name varchar(100) NOT NULL COMMENT '상품명',
     product_price int NOT NULL COMMENT '상품가격',
     product_created_at datetime NOT NULL COMMENT '상품등록일',
     product_image_path varchar(255) COMMENT '상품 이미지 경로',
     product_explain text NOT NULL COMMENT '상품설명',
     PRIMARY KEY(product_id),
     CONSTRAINT fk_products_category FOREIGN KEY(category_id) REFERENCES categories(category_id)
     ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';