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

CREATE TABLE issues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project VARCHAR(255) NOT NULL,
    issueType VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);