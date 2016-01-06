DROP TABLE IF EXISTS `user_board_status`;
CREATE TABLE user_board_status (
  id         VARCHAR(36)  NOT NULL,
  name       VARCHAR(36)  NOT NULL,
  status     VARCHAR(128) NOT NULL,
  created_at TIMESTAMP,
  CONSTRAINT user_board_status_pk PRIMARY KEY (id)
);
