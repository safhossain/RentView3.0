-- *************** TABLE + DATABASE CREATION *****************
-- Optimal deletion order: MEMBER, MOVIE, REVIEW, Database
-- ***********************************************************

USE RENTVIEWREVIEWSERVICE;

DROP TABLE IF EXISTS REVIEW;
DROP TABLE IF EXISTS MOVIE;
DROP TABLE IF EXISTS MEMBER;

-- IF NEEDED:
-- DROP DATABASE IF EXISTS RENTVIEWREVIEWSERVICE;