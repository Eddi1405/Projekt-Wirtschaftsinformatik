CREATE TABLE IF NOT EXISTS users
(
    id       integer      NOT NULL AUTO_INCREMENT,
    username varchar(50)  NOT NULL,
    password varchar(100) NOT NULL,
    email    varchar(50)  NOT NULL,
    role     varchar(50)  NOT NULL,
    PRIMARY KEY (id)
);