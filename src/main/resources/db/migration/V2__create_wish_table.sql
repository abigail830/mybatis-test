CREATE TABLE wish_tbl (
  id int NOT NULL AUTO_INCREMENT,
  user_id varchar(100) NOT NULL,
  description varchar(255) DEFAULT NULL,
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user_tbl(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
