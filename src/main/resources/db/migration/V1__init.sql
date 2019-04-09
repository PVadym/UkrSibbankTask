DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS transaction CASCADE;
DROP SEQUENCE IF EXISTS client_seq;
DROP SEQUENCE IF EXISTS trans_seq;

create sequence client_seq start 1 increment 1;
create sequence trans_seq start 1 increment 1;


create table client (
  id BIGINT NOT NULL PRIMARY KEY ,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  middle_name VARCHAR(255),
  inn INTEGER NOT NULL
);

create table transaction (
  id BIGINT NOT NULL PRIMARY KEY,
  place VARCHAR(255),
  amount DOUBLE PRECISION,
  currency VARCHAR(255),
  card VARCHAR(255),
  client_id BIGINT,
  CONSTRAINT transaction_client_fk FOREIGN KEY (client_id)
  REFERENCES client (id)
);