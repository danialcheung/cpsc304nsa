drop table if exists police;
drop table if exists CIA;
drop table if exists User;
drop table if exists Transaction;
drop table if exists Photo;
drop table if exists Text;
drop table if exists `Call`;
drop table if exists CommLog;
drop table if exists Data;
drop table if exists Device;
drop table if exists Location;

CREATE TABLE Location(
  lat FLOAT NOT NULL,
  lng FLOAT NOT NULL,
  country VARCHAR(20),
  PRIMARY KEY (lat, lng)
);

CREATE TABLE Device (
  device_id INT UNSIGNED NOT NULL ,
  owner VARCHAR(20) NULL ,
  model VARCHAR(20) NULL ,
  lat FLOAT NULL ,
  lng FLOAT NULL ,
  device_type VARCHAR(20),
  PRIMARY KEY (device_id),
  FOREIGN KEY (lat, lng) REFERENCES Location(lat, lng) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Data (
  data_id    INT UNSIGNED     NOT NULL,
  date       DATETIME NULL,
  suspicious BIT(1)  NOT NULL,
  lat        FLOAT    NULL,
  lng        FLOAT    NULL,
  device_id  INT UNSIGNED     NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (lat, lng) REFERENCES Location(lat, lng) ON DELETE SET NULL,
  FOREIGN KEY (device_id) REFERENCES Device (device_id) ON DELETE SET NULL
);

CREATE TABLE CommLog(
  data_id INT UNSIGNED NULL,
  sender INT UNSIGNED NULL,
  reciever INT UNSIGNED NULL,
  FOREIGN KEY (data_id) REFERENCES Data(data_id) ON DELETE cascade ON UPDATE CASCADE
);

CREATE TABLE `Call`(
  data_id INT UNSIGNED NOT NULL,
  beginning DATETIME NULL,
  end DATETIME NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES CommLog(data_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Text (
  data_id INT UNSIGNED NOT NULL,
  length INT UNSIGNED NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES CommLog(data_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Photo (
  data_id INT UNSIGNED NOT NULL,
  size INT UNSIGNED NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES Data(data_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Transaction (
  data_id INT UNSIGNED NOT NULL,
  amount FLOAT NULL,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES Data(data_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE User(
  user_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE CIA(
  user_id INT UNSIGNED NOT NULL ,
  country VARCHAR(20) NULL ,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);

CREATE TABLE Police(
  user_id INT UNSIGNED NOT NULL ,
  state CHAR(2) NULL ,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);


INSERT INTO Location (lat, lng, country) VALUES (37.09024, -95.712891, 'United States');
INSERT INTO Location (lat, lng, country) VALUES (51.165691, 10.451526, 'Germany');
INSERT INTO Location (lat, lng, country) VALUES (56.130366, -106.346771, 'Canada');
INSERT INTO Location (lat, lng, country) VALUES (-25.274398, 133.775136, 'Australia');
INSERT INTO Location (lat, lng, country) VALUES (55.378051, -3.435973, 'United Kingdom');

INSERT INTO Location (lat, lng, country) VALUES (10.861810, 159.784584, 'Poland');
INSERT INTO Location (lat, lng, country) VALUES (-69.067717, -174.507704, 'Germany');
INSERT INTO Location (lat, lng, country) VALUES (-2.026138, -102.952643, 'Poland');
INSERT INTO Location (lat, lng, country) VALUES (7.773322, 128.304261, 'Ukraine');
INSERT INTO Location (lat, lng, country) VALUES (-24.691678, 77.188033, 'United Kingdom');

INSERT INTO Location (lat, lng, country) VALUES (58.214496, 18.949128, 'Germany');
INSERT INTO Location (lat, lng, country) VALUES (-1.075264, 42.7196933, 'United Kingdom');
INSERT INTO Location (lat, lng, country) VALUES (5.25875225, 128.0870244, 'Canada');
INSERT INTO Location (lat, lng, country) VALUES (-40.34222295, 12.81877222, 'Australia');
INSERT INTO Location (lat, lng, country) VALUES (9.24914798, -126.90281711, 'Uinted States');

INSERT INTO Location (lat, lng, country) VALUES (-10.14852188, 155.72264639, 'United States');
INSERT INTO Location (lat, lng, country) VALUES (-69.80025379, -148.88810459, 'Cuba');
INSERT INTO Location (lat, lng, country) VALUES (-56.31474676, 88.78286033, 'Mongolia');
INSERT INTO Location (lat, lng, country) VALUES (0.5616872, 117.57850121, 'Costa Rica');
INSERT INTO Location (lat, lng, country) VALUES (47.54224876, 86.56017423, 'United States');


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

INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (0247094, '2014-03-25', false, 58.214496, 18.949128, 2231);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (4251462, '2016-12-25', true, -69.067717, -174.507704, 6261);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (3956189, '2011-08-11', false, -2.026138, -102.952643,8823);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (8320176, '1998-02-11', false, 7.773322, 128.304261, 1404);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (9184461, '2013-02-02', true, -24.691678, 77.188033, 7723);

INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (5446015, '1991-06-27', true, 58.214496, 18.949128, 2231);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (0249428, '1992-05-29', false, -1.075264, 42.7196933, 6261);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (6053689, '1993-07-06', true, 5.25875225, 128.0870244,8823);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (7532096, '1993-07-23', true, -40.34222295, 12.81877222, 1404);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (1824705, '1994-03-11', false, 9.24914798, -126.90281711, 7723);

INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (7479524, '1996-10-05', true, 58.214496, 18.949128, 2231);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (6461270, '2004-02-07', false, -1.075264, 42.7196933, 6261);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (0502723, '2005-03-28', true, 5.25875225, 128.0870244,8823);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (6202385, '2012-04-25', true, -40.34222295, 12.81877222, 1404);
INSERT INTO Data (data_id, date, suspicious, lat, lng, device_id) VALUES (2945311, '2013-09-06', false, 9.24914798, -126.90281711, 7723);


INSERT INTO CommLog (data_id, sender, reciever) VALUES (2212629, 4422, 8402);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (7712323, 7210, 2302);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (9923472, 6720, 8911);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (2234029, 8210, 4610);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (7563947, 0374, 0001);

INSERT INTO CommLog (data_id, sender, reciever) VALUES (0247094, 4422, 8402);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (4251462, 7210, 2302);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (3956189, 6720, 8911);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (8320176, 8210, 4610);
INSERT INTO CommLog (data_id, sender, reciever) VALUES (9184461, 0374, 0001);

INSERT INTO `Call` (data_id, beginning, end) VALUES (2212629, '2004-05-12 11:11:11', '2004-05-12 11:43:10');
INSERT INTO `Call` (data_id, beginning, end) VALUES (7712323, '2016-02-22 13:01:22', '2016-02-22 13:12:34');
INSERT INTO `Call` (data_id, beginning, end) VALUES (9923472, '2001-09-11 09:11:11', '2001-09-11 09:44:21');
INSERT INTO `Call` (data_id, beginning, end) VALUES (2234029, '1995-10-01 10:00:01', '1995-10-01 11:12:13');
INSERT INTO `Call` (data_id, beginning, end) VALUES (7563947, '2014-01-01 20:02:17', '2014-01-01 21:08:42');

INSERT INTO Text (data_id, length) VALUES (0247094, 142);
INSERT INTO Text (data_id, length) VALUES (4251462, 32);
INSERT INTO Text (data_id, length) VALUES (3956189, 97);
INSERT INTO Text (data_id, length) VALUES (8320176, 2);
INSERT INTO Text (data_id, length) VALUES (9184461, 54);

INSERT INTO Photo (data_id, size) VALUES (5446015, 200);
INSERT INTO Photo (data_id, size) VALUES (0249428, 450);
INSERT INTO Photo (data_id, size) VALUES (6053689, 442);
INSERT INTO Photo (data_id, size) VALUES (7532096, 629);
INSERT INTO Photo (data_id, size) VALUES (1824705, 1337);

INSERT INTO Transaction (data_id, amount) VALUES (7479524, 2000);
INSERT INTO Transaction (data_id, amount) VALUES (6461270, 8827);
INSERT INTO Transaction (data_id, amount) VALUES (0502723, 92);
INSERT INTO Transaction (data_id, amount) VALUES (6202385, 1000000);
INSERT INTO Transaction (data_id, amount) VALUES (2945311, 9992);

INSERT INTO User (user_id) VALUES (43210);
INSERT INTO User (user_id) VALUES (12380);
INSERT INTO User (user_id) VALUES (88122);
INSERT INTO User (user_id) VALUES (92932);
INSERT INTO User (user_id) VALUES (34212);
INSERT INTO User (user_id) VALUES (54325);
INSERT INTO User (user_id) VALUES (85677);
INSERT INTO User (user_id) VALUES (54326);
INSERT INTO User (user_id) VALUES (88435);
INSERT INTO User (user_id) VALUES (54634);

INSERT INTO CIA (user_id, country) VALUES (43210, 'China');
INSERT INTO CIA (user_id, country) VALUES (12380, 'Russia');
INSERT INTO CIA (user_id, country) VALUES (34212, 'India');
INSERT INTO CIA (user_id, country) VALUES (54325, 'Tibet');
INSERT INTO CIA (user_id, country) VALUES (85677, 'Australia');

INSERT INTO Police (user_id, state) VALUES (88122, 'TX');
INSERT INTO Police (user_id, state) VALUES (92932, 'NA');
INSERT INTO Police (user_id, state) VALUES (54326, 'WA');
INSERT INTO Police (user_id, state) VALUES (88435, 'CA');
INSERT INTO Police (user_id, state) VALUES (54634, 'OH');

