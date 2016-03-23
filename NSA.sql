DROP TABLE Location;
DROP TABLE Data_1;
DROP TABLE Data_2;
DROP TABLE Data_3;
DROP TABLE Data_4;
DROP TABLE Data_5;
DROP TABLE CommLog_1;
DROP TABLE CommLog_2;
DROP TABLE Call;
DROP TABLE Text;
DROP TABLE Photo;
DROP TABLE Transaction;
DROP TABLE Device_1;
DROP TABLE Device_2;
DROP TABLE Device_3;
DROP TABLE Device_4;
DROP TABLE Device_5;
DROP TABLE CIA;
DROP TABLE Police;

CREATE TABLE Location(
  lat NUMBER(*,9) NOT NULL,
  lng NUMBER(*,9) NOT NULL,
  country VARCHAR(20) NOT NULL,
  PRIMARY KEY (lat, lng));

CREATE TABLE Data_1(
  data_id INTEGER NOT NULL ,
  "date" DATE NOT NULL,
  PRIMARY KEY (data_id));

CREATE TABLE Data_2(
  data_id INTEGER NOT NULL,
  suspicious NUMBER(1) NOT NULL,
  PRIMARY KEY (data_id));

CREATE TABLE Data_3(
  data_id INTEGER NOT NULL ,
  lat NUMBER(*,9) NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Data_4(
  data_id INTEGER NOT NULL ,
  lng NUMBER(*,9) NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Data_5(
  data_id INTEGER NOT NULL ,
  device_id INTEGER NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE CommLog_1(
  data_id INTEGER NOT NULL ,
  sender INTEGER NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE CommLog_2(
  data_id INTEGER NOT NULL ,
  receiver INTEGER NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Call(
  data_id INTEGER NOT NULL ,
  duration INTEGER NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Text(
  data_id INTEGER NOT NULL ,
  length INTEGER NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Photo(
  data_id INTEGER NOT NULL ,
  "size" INTEGER NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Transaction(
  data_id INTEGER NOT NULL ,
  amount NUMBER(*,9) NOT NULL ,
  PRIMARY KEY (data_id));

CREATE TABLE Device_1(
  device_id INTEGER NOT NULL ,
  owner VARCHAR(20) NOT NULL ,
  PRIMARY KEY (device_id));

CREATE TABLE Device_2(
  device_id INTEGER NOT NULL ,
  model VARCHAR(20) NOT NULL ,
  PRIMARY KEY (device_id));

CREATE TABLE Device_3(
  device_id INTEGER NOT NULL ,
  lat NUMBER(*,9) NOT NULL ,
  PRIMARY KEY (device_id));

CREATE TABLE Device_4(
  device_id INTEGER NOT NULL ,
  lng NUMBER(*,9) NOT NULL ,
  PRIMARY KEY (device_id));

CREATE TABLE Device_5(
  model VARCHAR(20) NOT NULL ,
  device_type VARCHAR(20) NOT NULL ,
  PRIMARY KEY (model));

CREATE TABLE CIA(
  user_id INTEGER NOT NULL ,
  country VARCHAR(20) NOT NULL ,
  PRIMARY KEY (user_id));

CREATE TABLE Police(
  user_id INTEGER NOT NULL ,
  state VARCHAR(20) NOT NULL ,
  PRIMARY KEY (user_id));

INSERT INTO Location (lat, lng, country) VALUES (37.09024, -95.712891, 'United States');
INSERT INTO Location (lat, lng, country) VALUES (51.165691, 10.451526, 'Germany');
INSERT INTO Location (lat, lng, country) VALUES (56.130366, -106.346771, 'Canada');


