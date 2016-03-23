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
INSERT INTO Location (lat, lng, country) VALUES (-25.274398, 133.775136, 'Australia');
INSERT INTO Location (lat, lng, country) VALUES (55.378051, -3.435973, 'United Kingdom');

INSERT INTO Data_1 (data_id, "date") VALUES (59181, '31-JAN-16');
INSERT INTO Data_1 (data_id, "date") VALUES (72903, '10-OCT-15');
INSERT INTO Data_1 (data_id, "date") VALUES (82047, '17-AUG-15');
INSERT INTO Data_1 (data_id, "date") VALUES (19028, '03-JUN-14');
INSERT INTO Data_1 (data_id, "date") VALUES (30132, '14-MAY-13');

INSERT INTO Data_2 (data_id, suspicious) VALUES (59181, 1);
INSERT INTO Data_2 (data_id, suspicious) VALUES (72903, 0);
INSERT INTO Data_2 (data_id, suspicious) VALUES (82047, 0);
INSERT INTO Data_2 (data_id, suspicious) VALUES (19028, 0);
INSERT INTO Data_2 (data_id, suspicious) VALUES (30132, 1);

INSERT INTO Data_3 (data_id, lat) VALUES (59181, 37.09024);
INSERT INTO Data_3 (data_id, lat) VALUES (72903, 51.165691);
INSERT INTO Data_3 (data_id, lat) VALUES (82047, 56.130366);
INSERT INTO Data_3 (data_id, lat) VALUES (19028, -25.274398);
INSERT INTO Data_3 (data_id, lat) VALUES (30132, 55.378051);

INSERT INTO Data_4 (data_id, lng) VALUES (59181, -95.712891);
INSERT INTO Data_4 (data_id, lng) VALUES (72903, 10.451526);
INSERT INTO Data_4 (data_id, lng) VALUES (82047, -106.346771);
INSERT INTO Data_4 (data_id, lng) VALUES (19028, 133.775136);
INSERT INTO Data_4 (data_id, lng) VALUES (30132, -3.435973);

INSERT INTO Data_5 (data_id, device_id) VALUES (59181, 104);
INSERT INTO Data_5 (data_id, device_id) VALUES (72903, 398);
INSERT INTO Data_5 (data_id, device_id) VALUES (82047, 053);
INSERT INTO Data_5 (data_id, device_id) VALUES (19028, 295);
INSERT INTO Data_5 (data_id, device_id) VALUES (30132, 469);

INSERT INTO CommLog_1 (data_id, sender) VALUES (59181, 03);
INSERT INTO CommLog_1 (data_id, sender) VALUES (72903, 79);
INSERT INTO CommLog_1 (data_id, sender) VALUES (82047, 32);
INSERT INTO CommLog_1 (data_id, sender) VALUES (19028, 92);
INSERT INTO CommLog_1 (data_id, sender) VALUES (30132, 20);

INSERT INTO CommLog_2 (data_id, receiver) VALUES (59181, 63);
INSERT INTO CommLog_2 (data_id, receiver) VALUES (72903, 52);
INSERT INTO CommLog_2 (data_id, receiver) VALUES (82047, 14);
INSERT INTO CommLog_2 (data_id, receiver) VALUES (19028, 96);
INSERT INTO CommLog_2 (data_id, receiver) VALUES (30132, 41);

INSERT INTO Call (data_id, duration) VALUES (59181, 637);
INSERT INTO Call (data_id, duration) VALUES (72903, 015);
INSERT INTO Call (data_id, duration) VALUES (82047, 067);
INSERT INTO Call (data_id, duration) VALUES (19028, 490);
INSERT INTO Call (data_id, duration) VALUES (30132, 215);

INSERT INTO Text (data_id, length) VALUES (73693, 004);
INSERT INTO Text (data_id, length) VALUES (89573, 130);
INSERT INTO Text (data_id, length) VALUES (33963, 078);
INSERT INTO Text (data_id, length) VALUES (09987, 024);
INSERT INTO Text (data_id, length) VALUES (19281, 017);

INSERT INTO Photo (data_id, "size") VALUES (66973, 1200);
INSERT INTO Photo (data_id, "size") VALUES (20019, 9873);
INSERT INTO Photo (data_id, "size") VALUES (19863, 0976);
INSERT INTO Photo (data_id, "size") VALUES (48930, 3790);
INSERT INTO Photo (data_id, "size") VALUES (00299, 1708);

INSERT INTO Transaction (data_id, amount) VALUES (73700, 9.99);
INSERT INTO Transaction (data_id, amount) VALUES (50298, 403.37);
INSERT INTO Transaction (data_id, amount) VALUES (02994, 1200.00);
INSERT INTO Transaction (data_id, amount) VALUES (48921, 78.37);
INSERT INTO Transaction (data_id, amount) VALUES (30298, 3.79);

INSERT INTO Device_1 (device_id, owner) VALUES (59181, 'Jelena Djovik');
INSERT INTO Device_1 (device_id, owner) VALUES (72903, 'Omir Jinari');
INSERT INTO Device_1 (device_id, owner) VALUES (82047, 'Mark Ruffalo');
INSERT INTO Device_1 (device_id, owner) VALUES (19028, 'Jia Liang');
INSERT INTO Device_1 (device_id, owner) VALUES (3013, 'Leonid Pavel');

INSERT INTO Device_2 (device_id, model) VALUES (59181, 'Acer TravelMate p645');
INSERT INTO Device_2 (device_id, model) VALUES (72903, 'Alienware X51' );
INSERT INTO Device_2 (device_id, model) VALUES (82047, 'iPad 2');
INSERT INTO Device_2 (device_id, model) VALUES (19028, 'Samsung Galaxy S5');
INSERT INTO Device_2 (device_id, model) VALUES (30132, 'Motorola Moto G');

INSERT INTO Device_3 (device_id, lat) VALUES (59181, 37.09024);
INSERT INTO Device_3 (device_id, lat) VALUES (72903, 51.165691);
INSERT INTO Device_3 (device_id, lat) VALUES (82047, 56.130366);
INSERT INTO Device_3 (device_id, lat) VALUES (19028, -25.274398);
INSERT INTO Device_3 (device_id, lat) VALUES (30132, 55.378051);

INSERT INTO Device_4 (device_id, lng) VALUES (59181, -95.712891);
INSERT INTO Device_4 (device_id, lng) VALUES (72903, 10.451526);
INSERT INTO Device_4 (device_id, lng) VALUES (82047, -106.346771);
INSERT INTO Device_4 (device_id, lng) VALUES (19028, 133.775136);
INSERT INTO Device_4 (device_id, lng) VALUES (30132, -3.435973);

INSERT INTO Device_5 (model, device_type) VALUES ('Alcatel Onetouch S5', 'laptop');
INSERT INTO Device_5 (model, device_type) VALUES ('iPhone 6i', 'desktop');
INSERT INTO Device_5 (model, device_type) VALUES ('Samsung Galaxy Grand', 'tablet');
INSERT INTO Device_5 (model, device_type) VALUES ('Samsung Galaxy S5', 'cell phone');
INSERT INTO Device_5 (model, device_type) VALUES ('Motorola Moto G', 'cell phone');

INSERT INTO CIA (user_id, country) VALUES (1934, 'United States');
INSERT INTO CIA (user_id, country) VALUES (1023, 'Germany');
INSERT INTO CIA (user_id, country) VALUES (1083, 'Canada');
INSERT INTO CIA (user_id, country) VALUES (1349, 'United States');
INSERT INTO CIA (user_id, country) VALUES (1490, 'United Kingdom');

INSERT INTO Police (user_id, state) VALUES (98127, 'TX');
INSERT INTO CIA (user_id, country) VALUES (39007, 'NY');
INSERT INTO CIA (user_id, country) VALUES (02936, 'OK');
INSERT INTO CIA (user_id, country) VALUES (35037, 'CA');
INSERT INTO CIA (user_id, country) VALUES (20830, 'WA');