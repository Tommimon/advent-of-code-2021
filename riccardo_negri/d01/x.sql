--
-- Day 1 solution of Advent Of Code 2021 by Riccardo Negri
-- First part solution: 1226
-- Second part solution: 1252
--

START TRANSACTION;

--
-- Database aoc
--
CREATE DATABASE IF NOT EXISTS aoc;
USE aoc;

--
-- Table t1
--
DROP TABLE IF EXISTS t1;
CREATE TABLE t1 (
  distance int NOT NULL,
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY
);

--
-- Import data
--
LOAD DATA LOCAL INFILE './input.txt'
INTO TABLE t1
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(distance);

DROP TABLE IF EXISTS t2;
CREATE TABLE t2
AS
SELECT * FROM t1;

DROP TABLE IF EXISTS t3;
CREATE TABLE t3
AS
SELECT * FROM t1;

--
-- Part 1
--
SELECT count(t2.distance)
FROM t1, t2
WHERE t1.id = t2.id - 1 AND t1.distance < t2.distance;

--
-- Part 2
--
SELECT count(tA.distance)
FROM
    (SELECT t1.id, (t1.distance + t2.distance + t3.distance) AS distance
    FROM t1, t2, t3
    WHERE t1.id = t2.id -1 AND t2.id = t3.id - 1) AS tA,
    (SELECT t1.id, (t1.distance + t2.distance + t3.distance) AS distance
    FROM t1, t2, t3
    WHERE t1.id = t2.id -1 AND t2.id = t3.id - 1) AS tB
WHERE tA.id = tB.id - 1 AND tA.distance < tB.distance;

DROP DATABASE aoc;
