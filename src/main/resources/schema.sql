create table zara;

use zara;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

insert into users(username, password, role)
values ('root', 'root', 'admin');
