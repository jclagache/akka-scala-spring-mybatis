CREATE TABLE goods (
  ref varchar(256) NOT NULL,
  type varchar(64) NOT NULL,
  containerRef varchar(256) NOT NULL,
  quantity Int,
  PRIMARY KEY (ref)
);

CREATE TABLE containers (
  ref varchar(256) NOT NULL,
  cargoRef varchar(256) NOT NULL,
  PRIMARY KEY (ref)
);

CREATE TABLE cargoes (
  ref varchar(100) NOT NULL,
  arrival TIMESTAMP WITHOUT TIME ZONE,
  departure TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY (ref)
);