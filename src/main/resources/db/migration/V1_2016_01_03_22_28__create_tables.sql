DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
  id        VARCHAR(36) NOT NULL,
  create_at DATETIME,
  CONSTRAINT user PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `user_board_status`;
CREATE TABLE user_board_status (
  id      VARCHAR(36)  NOT NULL,
  user_id VARCHAR(36)  NOT NULL,
  status  VARCHAR(128) NOT NULL,
  CONSTRAINT user_board_status_pk PRIMARY KEY (id),
  CONSTRAINT user_board_status_fk_1 FOREIGN KEY (user_id) REFERENCES user (id)
);
