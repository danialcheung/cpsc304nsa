DROP TABLE Location;
DROP TABLE Device;
DROP TABLE Data;
DROP TABLE CommLog;
DROP TABLE `Call`;
DROP TABLE Text;
DROP TABLE Photo;
DROP TABLE Transaction;
DROP TABLE User;
DROP TABLE CIA;
DROP TABLE Police;

CREATE TABLE Location(
  lat FLOAT NOT NULL,
  lng FLOAT NOT NULL,
  country VARCHAR(20),
  PRIMARY KEY (lat, lng)
);

CREATE TABLE Device (
  device_id INT NOT NULL ,
  owner VARCHAR(20) NULL ,
  model VARCHAR(20) NULL ,
  lat FLOAT NOT NULL ,
  lng FLOAT NOT NULL ,
  device_type VARCHAR(20),
  PRIMARY KEY (device_id),
  FOREIGN KEY (lat, lng) REFERENCES Location(lat, lng)
);

CREATE TABLE Data (
  data_id    INT      NOT NULL,
  date       DATETIME NULL,
  suspicious BOOLEAN  NULL,
  lat        FLOAT    NOT NULL,
  lng        FLOAT    NOT NULL,
  device_id  INT      NOT NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (lat, lng) REFERENCES Location(lat, lng),
  FOREIGN KEY (device_id) REFERENCES Device (device_id)
);

CREATE TABLE CommLog(
  data_id INT NOT NULL,
  sender INT NULL,
  reciever INT NULL,
  FOREIGN KEY (data_id) REFERENCES Data(data_id)
);

CREATE TABLE `Call`(
  data_id INT NOT NULL,
  beginning DATETIME NULL,
  end DATETIME NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES CommLog(data_id)
);

CREATE TABLE Text (
  data_id INT NOT NULL,
  length INT NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES CommLog(data_id)
);

CREATE TABLE Photo (
  data_id INT NOT NULL,
  size INT NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES Data(data_id)
);

CREATE TABLE Transaction (
  data_id INT NOT NULL,
  amount FLOAT NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES Data(data_id)
);


CREATE TABLE User(
  user_id INT NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE CIA(
  user_id INT NOT NULL ,
  country VARCHAR(20) NULL ,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Police(
  user_id INT NOT NULL ,
  state CHAR(2) NULL ,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);

INSERT INTO Location (lat, lng, country) VALUES (37.09024, -95.712891, 'United States');
INSERT INTO Location (lat, lng, country) VALUES (51.165691, 10.451526, 'Germany');
INSERT INTO Location (lat, lng, country) VALUES (56.130366, -106.346771, 'Canada');
INSERT INTO Location (lat, lng, country) VALUES (-25.274398, 133.775136, 'Australia');
INSERT INTO Location (lat, lng, country) VALUES (55.378051, -3.435973, 'United Kingdom');

INSERT INTO Device (device_id, owner, model, lat, lng, device_type) VALUES (2231, 'Jelena Djovik', 'Alcatel Onetouch S5', 37.09024, -95.712891,'laptop');
INSERT INTO Device (device_id, owner, model, lat, lng, device_type) VALUES (6261, 'Omir Jinari', 'Alienware X51', 51.165691, 10.451526, 'desktop');
INSERT INTO Device (device_id, owner, model, lat, lng, device_type) VALUES (8823, 'Mark Ruffalo', 'iPad 2', 56.130366, -106.346771, 'tablet');
INSERT INTO Device (device_id, owner, model, lat, lng, device_type) VALUES (1404, 'Jia Liang', 'Samsung Galaxy S5', -25.274398, 133.775136, 'cell phone');
INSERT INTO Device (device_id, owner, model, lat, lng, device_type) VALUES (7723, 'Leonid Pavel', 'Motorola Moto G', 55.378051, -3.435973, 'cell phone');

INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (2212629, '2004-05-12', true, 37.09024, -95.712891, 2231);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (7712323, '2016-02-22', false, 51.165691, 10.451526, 6261);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (9923472, '2001-09-11', true, 56.130366, -106.346771,8823);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (2234029, '1995-10-01', true, -25.274398, 133.775136, 1404);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (7563947, '2014-01-01', false, 55.378051, -3.435973, 7723);

INSERT INTO CommLog (data_id, sender, reciever) VALUES (2212629, 4422, 8402);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (7712323, 7210, 2302);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (9923472, 6720, 8911);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (2234029, 8210, 4610);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (7563947, 0374, 0001);

INSERT INTO `Call` (data_id, beginning, end) VALUES (2212629, '2004-05-12 11:11:11', '2004-05-12 11:43:10');
INSERT INTO `Call` (data_id, beginning, end) VALUES (7712323, '2016-02-22 13:01:22', '2016-02-22 13:12:34');
INSERT INTO `Call` (data_id, beginning, end) VALUES (9923472, '2001-09-11 09:11:11', '2001-09-11 09:44:21');
INSERT INTO `Call` (data_id, beginning, end) VALUES (2234029, '1995-10-01 10:00:01', '1995-10-01 11:12:13');
INSERT INTO `Call` (data_id, beginning, end) VALUES (7563947, '2014-01-01 20:02:17', '2014-01-01 21:08:42');

INSERT INTO Text (data_id, length) VALUES (2212629, 142);
INSERT INTO Text (data_id, length) VALUES (7712323, 32);
INSERT INTO Text (data_id, length) VALUES (9923472, 97);
INSERT INTO Text (data_id, length) VALUES (2234029, 2);
INSERT INTO Text (data_id, length) VALUES (7563947, 54);

INSERT INTO Photo (data_id, size) VALUES (2212629, 200);
INSERT INTO Photo (data_id, size) VALUES (7712323, 450);
INSERT INTO Photo (data_id, size) VALUES (9923472, 442);
INSERT INTO Photo (data_id, size) VALUES (2234029, 629);
INSERT INTO Photo (data_id, size) VALUES (7563947, 1337);

INSERT INTO Transaction (data_id, amount) VALUES (2212629, 2000);
INSERT INTO Transaction (data_id, amount) VALUES (7712323, 8827);
INSERT INTO Transaction (data_id, amount) VALUES (9923472, 92);
INSERT INTO Transaction (data_id, amount) VALUES (2234029, 1000000);
INSERT INTO Transaction (data_id, amount) VALUES (7563947, 9992);

INSERT INTO User (user_id) VALUES (43210);
INSERT INTO User (user_id) VALUES (12380);
INSERT INTO User (user_id) VALUES (88122);
INSERT INTO User (user_id) VALUES (92932);
INSERT INTO User (user_id) VALUES (34212);

INSERT INTO CIA (user_id, country) VALUES (43210, 'China');
INSERT INTO CIA (user_id, country) VALUES (12380, 'Russia');

INSERT INTO Police (user_id, state) VALUES (88122, 'TX');
INSERT INTO Police (user_id, state) VALUES (92932, 'NA');

