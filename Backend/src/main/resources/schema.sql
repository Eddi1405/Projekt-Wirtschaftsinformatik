CREATE TABLE IF NOT EXISTS users
(
    id           integer      NOT NULL AUTO_INCREMENT,
    username     varchar(50)  NOT NULL,
    password     varchar(100) NOT NULL,
    email        varchar(50)  NOT NULL,
    role         varchar(50)  NOT NULL,
    learningtype varchar(50)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS thread
(
    id       integer NOT NULL AUTO_INCREMENT,
    authorID integer NOT NULL,
    header   varchar NOT NULL,
    content  varchar NOT NULL,
    date     date    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorID) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comment
(
    id       integer NOT NULL AUTO_INCREMENT,
    authorID integer NOT NULL,
    content  varchar NOT NULL,
    date     date    NOT NULL,
    replyID  integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorID) REFERENCES users (id),
    FOREIGN KEY (replyID) REFERENCES comment (id)
);

CREATE TABLE IF NOT EXISTS tag
(
    id          integer     NOT NULL AUTO_INCREMENT,
    description varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS thread_comment
(
    id        integer NOT NULL AUTO_INCREMENT,
    threadID  integer NOT NULL,
    commentID integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (threadID) REFERENCES thread (id),
    FOREIGN KEY (commentID) REFERENCES comment (id)
);

CREATE TABLE IF NOT EXISTS thread_tag
(
    id       integer NOT NULL AUTO_INCREMENT,
    threadID integer NOT NULL,
    tagID    integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (threadID) REFERENCES thread (id),
    FOREIGN KEY (tagID) REFERENCES tag (id)
);



