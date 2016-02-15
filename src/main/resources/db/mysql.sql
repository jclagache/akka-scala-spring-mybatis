CREATE TABLE goods (
  ref varchar(32) NOT NULL,
  type varchar(64) NOT NULL,
  containerRef varchar(256) NOT NULL,
  quantity Int,
  PRIMARY KEY (ref)
);

CREATE TABLE containers (
  ref varchar(32) NOT NULL,
  cargoRef varchar(256) NOT NULL,
  PRIMARY KEY (ref)
);

CREATE TABLE cargoes (
  ref varchar(32) NOT NULL,
  arrival DATETIME,
  departure DATETIME,
  PRIMARY KEY (ref)
);